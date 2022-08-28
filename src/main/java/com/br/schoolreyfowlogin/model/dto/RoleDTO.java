package com.br.schoolreyfowlogin.model.dto;

public class RoleDTO {

    private final String authority;

    public RoleDTO(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
