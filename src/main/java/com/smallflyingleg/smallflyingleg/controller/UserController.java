package com.smallflyingleg.smallflyingleg.controller;


import com.smallflyingleg.smallflyingleg.pojo.User;
import com.smallflyingleg.smallflyingleg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wdx
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> user(){
        List<User> users = userService.selectList(null);
        return users;
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") Long id){
        User user = userService.selectById(id);
        return user;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") Long id){
        boolean user = userService.deleteById(id);
        return user;
    }
}

