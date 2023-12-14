package com.smilegate.digeruserservice.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLoginIdAndPassword(String loginId, String password);

    Optional<UserEntity> findByLoginId(String loginId);

    Optional<UserEntity> findByNickname(String nickname);

    Optional<UserEntity> findByLoginIdOrNickname(String loginId, String nickname);

}
