package com.br.schoolreyfowlogin.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.br.schoolreyfowlogin.util.RolesConvertUtil;

import java.util.List;

@DynamoDBTable(tableName = "users")
public class UserModel {

    @DynamoDBHashKey(attributeName = "id")
    private String email;

    @DynamoDBAttribute()
    private String name;

    @DynamoDBAttribute()
    private String password;

    @DynamoDBAttribute()
    @DynamoDBTypeConverted(converter = RolesConvertUtil.class)
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
