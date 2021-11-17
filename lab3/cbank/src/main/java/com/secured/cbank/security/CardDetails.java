package com.secured.cbank.security;

import com.secured.cbank.entity.Card;
import com.secured.cbank.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CardDetails implements UserDetails {
    private Card card;

    public CardDetails(Card card){
        this.card = card;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = card.getRoles();

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for(Role role: roles)
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return card.getPin();
    }

    @Override
    public String getUsername() {
        return card.getCardNumber();
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
        return card.isValid();
    }
}
