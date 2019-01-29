package com.huaan.javabasic.ehcache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
//@Qualifier("userService")
//@Resource(name="userService")
public class UserService {
    // @Cacheable可以设置多个缓存，形式如：@Cacheable({"books", "isbns"})
    @Cacheable({"users"})
    public User findUser(int id) {
        System.out.println("====== findUser ======");
        return new User(id, "张三");
    }

    @Cacheable(value = "users", condition = "#user.getId() <= 2")
    public User findUserInLimit(User user) {
        System.out.println("====== findUserInLimit ======");
        return new User(user.getId(), "张三");
    }

    @CachePut(value = "users", key = "#user.getId()")
    public void updateUser(User user) {
        System.out.println("====== updateUser ======");
    }

    @CacheEvict(value = "users")
    public void removeUser(User user) {
        System.out.println("====== removeUser ======");
    }

    @CacheEvict(value = "users", allEntries = true)
    public void clear() {
        System.out.println("====== clear ======");
    }
}
