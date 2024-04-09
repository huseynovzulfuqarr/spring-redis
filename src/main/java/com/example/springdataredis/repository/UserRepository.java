package com.example.springdataredis.repository;

import com.example.springdataredis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository  {

    public final static String HASH_KEY="User";

    @Autowired
    private  RedisTemplate redisTemplate;

    //create user
    public User saveUser(User user){
        redisTemplate.opsForHash().put(HASH_KEY,user.getId(),user);
        return user;
    }

    //findUserById
    public User getById(Long id){
        return (User) redisTemplate.opsForHash().get(HASH_KEY,id);
    }

    //find all users
    public List<User> getAllUsers(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    //delete by id
    public void deleteUser(Long id){
        redisTemplate.opsForHash().delete(HASH_KEY,id);
    }

}
