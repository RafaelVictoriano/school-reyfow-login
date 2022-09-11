package com.br.schoolreyfowlogin.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class UserResponseDTO implements Serializable {
    private final String email;
    private final String password;
    private final List<RoleDTO> roles;

    public UserResponseDTO(String email, String password, List<RoleDTO> roles) {
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

}
