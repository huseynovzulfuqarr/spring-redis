package com.example.springdataredis.controller;

import com.example.springdataredis.entity.User;
import com.example.springdataredis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@EnableCaching
public class UserController {

    @Autowired
    private  UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id",value = "User")
    public User getUserById(@PathVariable("id") Long id){
        return userRepository.getById(id);
    }

    @PostMapping("/save")
    @CacheEvict(key = "#id",value = "User")
    public User save(@RequestBody User user){
        return userRepository.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userRepository.deleteUser(id);
    }
}
