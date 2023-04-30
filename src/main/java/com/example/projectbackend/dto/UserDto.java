package com.example.projectbackend.dto;
import com.example.projectbackend.model.Account;
import com.example.projectbackend.model.Authority;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserDto {

    public Long id;
    @NotBlank
    @Email
    public String email;
    @NotBlank
    public String username;
    @NotBlank
    @Size(min=4, max=12)
    public String password;

    private Account account;

    @JsonSerialize
    public Set<Authority> authorities;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

}
