package com.br.schoolreyfowlogin.controller;


import com.br.schoolreyfowlogin.model.User;
import com.br.schoolreyfowlogin.model.dto.UserDto;
import com.br.schoolreyfowlogin.model.dto.UserResponseDTO;
import com.br.schoolreyfowlogin.model.mapper.ModelDaoOMapper;
import com.br.schoolreyfowlogin.service.UserService;
import com.br.schoolreyfowlogin.util.JwtTokenUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;


@RestController
@RequestMapping("/auth")
public record AuthenticationController(UserService userService, AuthenticationManager authenticationManager,
                                       JwtTokenUtil jwtTokenUtil) {


    @PostMapping("login")
    public ResponseEntity login(@RequestBody User request) {
        try {
            var authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            var user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                    .build();

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public UserResponseDTO register(@RequestBody UserDto request) throws ValidationException {
        return userService.create(request);
    }

}

