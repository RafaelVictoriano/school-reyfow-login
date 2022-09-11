package com.br.schoolreyfowlogin.controller;

import com.br.schoolreyfowlogin.model.UserModel;
import com.br.schoolreyfowlogin.model.dto.UserDto;
import com.br.schoolreyfowlogin.service.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/user")
public record UserController(UserDetailsServiceImpl userDetailsServiceImpl) {

    @PostMapping
    public UserModel create(@RequestBody UserDto request) throws ValidationException {
        return userDetailsServiceImpl.create(request);
    }

    @GetMapping("{id}")
    public UserModel get(@PathVariable String id) {
        return userDetailsServiceImpl.getUser(id);
    }
}