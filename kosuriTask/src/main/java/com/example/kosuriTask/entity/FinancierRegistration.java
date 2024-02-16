package com.example.kosuriTask.entity;

import com.example.kosuriTask.booleanConverter.BooleanToStringConverter;
import com.example.kosuriTask.entity.enumValues.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FinancierRegistration implements UserDetails,Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fUserId;
    private String fullName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;
    private String password;
    private String state;
    private String district;
    private String location;
    private String pinCode;
    @CreationTimestamp
    private Date registrationDate;
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean status;
    @Enumerated(EnumType.STRING)
    private UserType userType;

//    @OneToMany(mappedBy = "financierRegistration",fetch = FetchType.LAZY)
//    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String rolePrefix="ROLE_";
        String roleName= rolePrefix + this.userType.name();
        return Collections.singleton(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getUsername() {
        return this.email +"_"+ this.phoneNumber;
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
        return this.status != null && this.status;
    }

    @Override
    public long getId() {
        return this.fUserId;
    }

    @Override
    public Boolean status() {
        return this.status;
    }
}
