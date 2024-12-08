package com.filepackage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users",schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role; // ROLE_USER veya ROLE_ADMIN

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    //Kullancılara verilen yetki
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.name;
    }


    //Hesap süresi
    @Override
    public boolean isAccountNonExpired() {
        return true;
        //  return UserDetails.super.isAccountNonExpired();
    }
    //Hesap kilitli değildir
    @Override
    public boolean isAccountNonLocked() {
        return true;
//         return UserDetails.super.isAccountNonLocked();
    }

    //Kimlik bilgileri süresi dolmamış
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //return UserDetails.super.isCredentialsNonExpired();
    }

    //Etkin
    @Override
    public boolean isEnabled() {
        return true;
        //return UserDetails.super.isEnabled();
    }
    // Add a method to hash the password if needed
    //password hashleme metodunu burada ekleyeceğiz.
}
