package com.smilegate.digeruserservice.domain.persistence;

import com.smilegate.digeruserservice.domain.UserVo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", length = 20, nullable = false)
    private String loginId;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "nickname", length = 20, nullable = false)
    private String nickname;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserVo toVo() {
        return new UserVo(
                this.id,
                this.loginId,
                this.password,
                this.nickname,
                this.point,
                this.role
        );
    }

    public UserEntity updatePoint(Integer point) {
        this.point += point;
        return this;
    }
}

