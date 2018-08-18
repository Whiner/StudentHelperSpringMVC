package com.database.other;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    GUEST, USER, MODERATOR, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
