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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping("/auth")
public record AuthenticationController(UserDetailsServiceImpl userDetailsServiceImpl,
                                       ModelDaoOMapper mapper,
                                       JwtTokenUtil jwtTokenUtil) {


    @PostMapping("login")
    public ResponseEntity<UserResponseDTO> login(Authentication auth) {
        final var user = (UserModel) auth.getPrincipal();

        return ResponseEntity.ok()
                .header(AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                .body(mapper.userToUserResponseDTO(user));

    }

    @PostMapping("register")
    public UserResponseDTO register(@RequestBody UserDto request) throws ValidationException {
        return userDetailsServiceImpl.create(request);
    }

}

