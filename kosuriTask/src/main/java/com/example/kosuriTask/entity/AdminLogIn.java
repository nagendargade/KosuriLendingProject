package com.example.kosuriTask.entity;

import com.example.kosuriTask.entity.enumValues.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminLogIn implements Registration{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String empId;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String location;
    private Boolean status;

    @Override
    public String getState() {
        return null;
    }

    @Override
    public String getDistrict() {
        return null;
    }

    @Override
    public String getPinCode() {
        return null;
    }

    @Override
    public UserType getUserType() {
        return UserType.SUADM;
    }

    @Override
    public Boolean status() {
        return status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + getUserType().name()));
    }

    @Override
    public String getUsername() {
        return this. email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status;
    }
}
