package com.meksula.chat.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @Author
 * Karol Meksuła
 * 20-07-2018
 * */

@Getter
@Setter
@Entity
@Table(name = "chat_users")
public class ChatUser implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String username;
    private String email;
    private String password;
    private boolean enable;

    @ElementCollection
    private Set<String> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        authorities.forEach(auth -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(auth);
            grantedAuthoritySet.add(grantedAuthority);
        });

        return grantedAuthoritySet;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enable;
    }

}