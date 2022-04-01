package com.br.schoolreyfowlogin.controller;

import com.br.schoolreyfowlogin.model.User;
import com.br.schoolreyfowlogin.model.dto.UserDto;
import com.br.schoolreyfowlogin.model.dto.UserResponseDTO;
import com.br.schoolreyfowlogin.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO create(@RequestBody UserDto request) throws ValidationException {
        return userService.create(request);
    }

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        return userService.getUser(id);
    }
}