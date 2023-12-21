package com.smilegate.digeruserservice.domain.service;

import com.smilegate.digeruserservice.common.encryptor.PasswordEncryptor;
import com.smilegate.digeruserservice.common.exception.ExceptionType;
import com.smilegate.digeruserservice.common.exception.UserServerException;
import com.smilegate.digeruserservice.domain.UserVo;
import com.smilegate.digeruserservice.domain.persistence.Role;
import com.smilegate.digeruserservice.domain.persistence.UserEntity;
import com.smilegate.digeruserservice.domain.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public UserVo create(String loginId, String password, String nickname) {
        return userRepository.save(
                new UserVo(
                        loginId,
                        passwordEncryptor.onlyHash(password),
                        nickname,
                        0,
                        Role.USER
                ).fromVo()
        ).toVo();
    }

    public UserVo loadById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) throw new UserServerException(ExceptionType.E404);
        return userEntity.get().toVo();
    }

    public UserVo loadByLoginId(String loginId) {
        Optional<UserEntity> userEntity = userRepository.findByLoginId(loginId);
        if (userEntity.isPresent()) {
            return userEntity.get().toVo();
        }
        throw new UserServerException(ExceptionType.E404);
    }

    public UserVo loadByLoginIdAndPassword(
            String loginId,
            String password
    ) {
        Optional<UserEntity> userEntity = userRepository.findByLoginId(loginId);
        if (userEntity.isEmpty() || incorrectPassword(password, userEntity.get().toVo())) {
            throw new UserServerException(ExceptionType.E400_AUTHENTICATE);
        }
        return userEntity.get().toVo();
    }

    public UserVo updatePoint(
            UserVo userVo,
            Integer point
    ) {
        return userVo.fromVo().updatePoint(point).toVo();
    }

    public boolean incorrectPassword(String password, UserVo userVo) {
        return !passwordEncryptor.matches(
                password,
                userVo.getPassword()
        );
    }

    public void validateDuplicatedLoginId(String loginId) {
        if (userRepository.findByLoginId(loginId).isPresent()) {
            throw new UserServerException(ExceptionType.E409);
        }
    }

    public void validateDuplicatedNickname(String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new UserServerException(ExceptionType.E409);
        }
    }
}
