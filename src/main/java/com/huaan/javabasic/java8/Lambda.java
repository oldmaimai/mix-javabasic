package com.huaan.javabasic.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lambda {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        // 1. lambda 表达式
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });

//        Collections.sort(names, (String a, String b) -> {
//            return b.compareTo(a);
//        });

//        Collections.sort(names, (String a, String b) -> b.compareTo(a));
//        Comparator<String> comparator = (a, b) -> b.compareTo(a);
        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(names);

        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123

        // 使用::来进行函数应用
        Converter<String, Integer> converter2 = Integer::valueOf;
        Integer converted2 = converter2.convert("123");
        System.out.println(converted2);   // 123


        class Something {
            String startsWith(String s) {
                return String.valueOf(s.charAt(0));
            }
        }

        Something something = new Something();
        // 引用一个对象的方法，这样可以解决多行的问题了
        Converter<String, String> converter3 = something::startsWith;
        String converted3 = converter3.convert("Java");
        System.out.println(converted3);    // "J"

        // 通过构造函数应用
        //Java编译器会自动地选择合适的构造函数来匹配PersonFactory.create函数的签名
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

        // Lambda的范围
        final int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);

        stringConverter.convert(2);     // 3

    }
}

//任意只包含一个抽象方法的接口，我们都可以用来做成lambda表达式
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}


class Person {
    String firstName;
    String lastName;

    Person() {}

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
