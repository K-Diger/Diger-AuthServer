package com.smilegate.digeruserservice.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
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
                loginId,
                password,
                nickname,
                role
        );
    }
}
