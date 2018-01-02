package com.huaan.javabasic.java8;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

public class Nashron {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException, FileNotFoundException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello World!');");

        engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("E:\\learning\\github\\mix-javabasic\\src\\main\\java\\com\\huaan\\javabasic\\java8\\script.js"));

        Invocable invocable = (Invocable) engine;

        Object result = invocable.invokeFunction("fun1", "Peter Parker");
        System.out.println(result);
        System.out.println(result.getClass());

        // Hi there from Javascript, Peter Parker
        // greetings from javascript
        // class java.lang.String

        invocable.invokeFunction("fun2", new Date());
        // [object java.util.Date]

        invocable.invokeFunction("fun2", LocalDateTime.now());
        // [object java.time.LocalDateTime]

        invocable.invokeFunction("fun2", new Person());
        // [object com.winterbe.java8.Person]
    }

    static String fun1(String name) {
        System.out.format("Hi there from Java, %s", name);
        return "greetings from java";
    }
}
