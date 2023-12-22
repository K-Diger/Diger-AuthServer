package com.smilegate.digerpostservice.feign.user.dto;

import com.smilegate.digerpostservice.feign.user.dto.response.UserResponse;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class UserVo implements UserDetails {

    Long id;
    String loginId;
    Integer point;
    String role;

    public UserVo fromUserResponse(UserResponse userResponse) {
        this.id = userResponse.id();
        this.loginId = userResponse.loginId();
        this.point = userResponse.point();
        this.role = userResponse.role();
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
