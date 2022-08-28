package com.br.schoolreyfowlogin.model.dto;

import java.io.Serializable;
import java.util.Set;

public class UserResponseDTO implements Serializable {
    private final String email;
    private final String password;
    private final Set<RoleDTO> roles;

    public UserResponseDTO(String email, String password, Set<RoleDTO> roles) {
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

}
