//package com.smilegate.digerauthserver.common.security.userdetails;
//
//import com.smilegate.digerauthserver.common.exception.ExceptionType;
//import com.smilegate.digerauthserver.common.exception.UserServerException;
//import com.smilegate.digerauthserver.domain.persistence.UserEntity;
//import com.smilegate.digerauthserver.domain.persistence.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserEntity> optionalUserEntity = userRepository.findByLoginId(username);
//        if (optionalUserEntity.isEmpty()) throw new UserServerException(ExceptionType.E404);
//
//        UserEntity userEntity = optionalUserEntity.get();
//
//        return new User(
//                userEntity.getLoginId(),
//                userEntity.getPassword(),
//                true,
//                true,
//                true,
//                true,
//                new ArrayList<>()
//        );
//    }
//}
