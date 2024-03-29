package com.br.schoolreyfowlogin.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class UserDto implements Serializable {
    private String email;
    private String name;
    private String password;
    private String rePassword;

    public UserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
