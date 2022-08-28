package com.br.schoolreyfowlogin.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {

    public static final String COORDINATOR = "ROLE_COORDINATOR";

    @Id
    private String authority;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UserModel> userModels;

    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public Set<UserModel> getUsers() {
        return userModels;
    }

    public void setUsers(Set<UserModel> userModels) {
        this.userModels = userModels;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
