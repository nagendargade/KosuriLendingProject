package com.example.kosuriTask.token;

import com.example.kosuriTask.entity.CustomerRegistration;
import com.example.kosuriTask.entity.FinancierRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    public long id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custmer_id")
    public CustomerRegistration customerRegistration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fincer_id")
    public FinancierRegistration financierRegistration;
}
