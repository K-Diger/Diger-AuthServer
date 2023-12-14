package com.smilegate.digeruserservice.domain;

import com.smilegate.digeruserservice.domain.persistence.Role;
import com.smilegate.digeruserservice.domain.persistence.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private Role role;

    public UserVo(String loginId, String password, String nickname, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public UserEntity fromVo() {
        return new UserEntity(
                id,
                loginId,
                password,
                nickname,
                role
        );
    }
}
