package com.smilegate.digeruserservice.common.security.filter.userdetails;//package com.smilegate.digerauthserver.common.security.userdetails;

import com.smilegate.digeruserservice.common.exception.ExceptionType;
import com.smilegate.digeruserservice.common.exception.UserServerException;
import com.smilegate.digeruserservice.domain.UserVo;
import com.smilegate.digeruserservice.domain.persistence.UserEntity;
import com.smilegate.digeruserservice.domain.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByLoginId(username);
        if (optionalUserEntity.isEmpty()) throw new UserServerException(ExceptionType.E404);

        UserVo userVo = optionalUserEntity.get().toVo();

        return new User(
                userVo.getLoginId(),
                userVo.getPassword(),
                true,
                true,
                true,
                true,
                userVo.getAuthorities()
        );
    }
}
