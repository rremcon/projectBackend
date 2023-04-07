package com.example.projectbackend.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@IdClass(AuthorityKey.class)
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String authority;

//    @ManyToMany(mappedBy = "authorities")
//    private Collection<User> users;

    public Authority() {
    }

    public Authority(String authority, String username) {
        this.authority = authority;
        this.username = username;
    }


    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
