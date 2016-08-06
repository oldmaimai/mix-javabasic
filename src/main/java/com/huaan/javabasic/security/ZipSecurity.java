package com.huaan.javabasic.security;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * zip安全编码 https://www.securecoding.cert.org/confluence/display/java/IDS04-J.+Safely+extract+files+from+ZipInputStream
 * @author maihuaan
 * Date:2016年8月6日下午12:52:29 
 * Copyright (c) 2016, maihuaan@126.com All Rights Reserved.
 */
public class ZipSecurity 
{
	static final int BUFFER = 512;
	static final int TOOBIG = 0x6400000; // Max size of unzipped data, 100MB
	static final int TOOMANY = 1024;     // Max number of files
	// ...
	 
	public static void main(String[] args) throws IOException 
	{
		unzip("E:/notes/我的总结/a.zip");
	}
	
	private static String validateFilename(String filename, String intendedDir)
	      throws java.io.IOException {
	  File f = new File(filename);
	  //getCanonicalPath 拿到规范化的名称，这样就不怕名称中有../这种路径了
	  //注：file.getAbsolutePath() -> D:\workspace\test\..\src\test1.txt 
	  //    file.getCanonicalPath()-> D:\workspace\src\test1.txt  
	  //    file.getPath           -> .\test1.txt
	  String canonicalPath = f.getCanonicalPath();
	 
	  File iD = new File(intendedDir);
	  String canonicalID = iD.getCanonicalPath();
	   
	  if (canonicalPath.startsWith(canonicalID)) {
	    return canonicalPath;
	  } else {
	    throw new IllegalStateException("File is outside extraction target directory.");
	  }
	}
	 
	private static final void unzip(String filename) throws java.io.IOException {
	  FileInputStream fis = new FileInputStream(filename);
	  ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
	  ZipEntry entry;
	  int entries = 0;
	  long total = 0;
	  try {
	    while ((entry = zis.getNextEntry()) != null) {
	      System.out.println("Extracting: " + entry);
	      int count;
	      byte data[] = new byte[BUFFER];
	      // Write the files to the disk, but ensure that the filename is valid,
	      // and that the file is not insanely big
	      // 验证文件名并不会真正写文件，因为entry.getName()只有entry的名称，
	      // 所以new File(entry.getName())是在当前路径下的名称，如：E:\learning\workspace\JavabaseProj\a
	      // 所以第二个参数需要出入"."，即new File(".")->E:\learning\workspace\JavabaseProj\
	      // 这样就可以判读出entry a的路径是否含有../等特殊字符
	      String name = validateFilename(entry.getName(), ".");
	      if (entry.isDirectory()) {
	        System.out.println("Creating directory " + name);
	        new File(name).mkdir();
	        continue;
	      }
	      FileOutputStream fos = new FileOutputStream(name);
	      BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
	      while (total + BUFFER <= TOOBIG && (count = zis.read(data, 0, BUFFER)) != -1) {
	        dest.write(data, 0, count);
	        total += count;
	      }
	      dest.flush();
	      dest.close();
	      zis.closeEntry();
	      entries++;
	      if (entries > TOOMANY) {
	        throw new IllegalStateException("Too many files to unzip.");
	      }
	      if (total > TOOBIG) {
	        throw new IllegalStateException("File being unzipped is too big.");
	      }
	    }
	  } finally {
	    zis.close();
	  }
	}

}
