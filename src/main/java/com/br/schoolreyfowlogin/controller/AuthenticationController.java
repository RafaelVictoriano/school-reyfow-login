package com.br.schoolreyfowlogin.controller;


import com.br.schoolreyfowlogin.model.UserModel;
import com.br.schoolreyfowlogin.model.dto.UserDto;
import com.br.schoolreyfowlogin.model.dto.UserResponseDTO;
import com.br.schoolreyfowlogin.model.mapper.ModelDaoOMapper;
import com.br.schoolreyfowlogin.service.UserDetailsServiceImpl;
import com.br.schoolreyfowlogin.util.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;


@RestController
@RequestMapping("/auth")
public record AuthenticationController(UserDetailsServiceImpl userDetailsServiceImpl,
                                       ModelDaoOMapper mapper,
                                       JwtTokenUtil jwtTokenUtil) {


    @PostMapping("login")
    public ResponseEntity<UserDetails> login(Authentication auth) {
        final var user = (UserDetails) auth.getPrincipal();

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                .body(user);

    }

    @PostMapping("register")
    public UserModel register(@RequestBody UserDto request) throws ValidationException {
        return userDetailsServiceImpl.create(request);
    }

    public Iterable<UserModel> get() throws ValidationException {
        return userDetailsServiceImpl.getAll();
    }

}

