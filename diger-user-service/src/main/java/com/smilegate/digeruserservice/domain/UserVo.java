package com.smilegate.digeruserservice.domain;

import com.smilegate.digeruserservice.domain.persistence.Role;
import com.smilegate.digeruserservice.domain.persistence.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements UserDetails {
    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private Integer point;
    private Role role;

    public UserVo(String loginId, String password, String nickname, Integer point, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.point = point;
        this.role = role;
    }

    public UserEntity fromVo() {
        return new UserEntity(
                id,
                loginId,
                password,
                nickname,
                point,
                role
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.loginId;
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
