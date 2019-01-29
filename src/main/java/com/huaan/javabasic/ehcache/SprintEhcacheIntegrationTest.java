package com.huaan.javabasic.ehcache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SprintEhcacheIntegrationTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-ehcache.xml");
        UserService service = ctx.getBean("userService", UserService.class);
        service.findUser(1);
        service.findUserInLimit(new User(2));
        service.findUserInLimit(new User(2));
        service.findUserInLimit(new User(3));
        service.findUserInLimit(new User(3));
        service.updateUser(new User(4));
        service.updateUser(new User(4));
        service.removeUser(new User(2));
        service.findUserInLimit(new User(2));
    }
}
