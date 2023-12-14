package com.smilegate.digeruserservice.common;

public interface PasswordEncryptor {

    String onlyHash(String password);

    String hashWithSalt(String password, String salt);

    Boolean matches(String rawPassword, String encryptedPassword);
}
