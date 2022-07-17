package com.imena.sertec.security;

import com.imena.sertec.model.Person;
import com.imena.sertec.model.State;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class SecurityPerson implements UserDetails {

    Person person;

    //security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority =
                new SimpleGrantedAuthority("" + this.person.getRole());
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
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

        return true;
    }
}
