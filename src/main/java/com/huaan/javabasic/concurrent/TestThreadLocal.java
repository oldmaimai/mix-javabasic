package com.huaan.javabasic.concurrent;

public class TestThreadLocal {
	// ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值  
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {  
        public Integer initialValue() {  
            return 0;  
        }  
    }; 
    // ②获取下一个序列值  
    public int getNextNum() {  
        seqNum.set(seqNum.get() + 1);  
        return seqNum.get();  
    }  


    /**
     * 从结果来看，只有一个TestThreadLocal对象sn，这个对象里面有个private static ThreadLocal<Integer>变量，两个线程共享了sn
     * 如果不是ThreadLocal变量，由于是staic，变量会互相影响，但是使用了ThreadLocal，线程之间的变量互相不影响
     * 换句话说：线程之间的static线程局部变量可以互相独立，这样就免去了对seqNum的同步
     * 具体例子：ConnectionManager对象中，不同线程共享一个ConnectionManager对象，但是都可以使用自己本身线程的connection变量（该对象是
     * ConnectionManager的static变量），互相不影响
     * @param args
     */
	public static void main(String[] args) {
		TestThreadLocal sn = new TestThreadLocal();
		// ③ 3个线程共享sn，各自产生序列号  
        TestClient t1 = new TestClient(sn);  
        TestClient t2 = new TestClient(sn);  
        t1.start();  
        t2.start();  
	}
	
    private static class TestClient extends Thread {  
        private TestThreadLocal sn;  
  
        public TestClient(TestThreadLocal sn) {  
            this.sn = sn;  
        }  
  
        public void run() {  
            for (int i = 0; i < 3; i++) {  
                // ④每个线程打出3个序列值  
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["  
                         + sn.getNextNum() + "]");  
            }  
        }  
    }  

}