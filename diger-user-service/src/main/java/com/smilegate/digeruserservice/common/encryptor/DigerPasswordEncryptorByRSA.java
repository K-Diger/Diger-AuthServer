package com.smilegate.digeruserservice.common.encryptor;

import com.smilegate.digeruserservice.common.PasswordEncryptor;
import com.sun.jdi.InternalException;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class DigerPasswordEncryptorByRSA implements PasswordEncryptor {

    private static final String HEX = "%02x";
    private static final String SHA = "SHA-256";

    private String convertByteToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte datum : data) {
            stringBuilder.append(String.format(HEX, datum));
        }
        return stringBuilder.toString();
    }

    @Override
    public String onlyHash(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA);
            messageDigest.update((password).getBytes());
            byte[] encryptedPassword = messageDigest.digest();
            return convertByteToString(encryptedPassword);
        } catch (NoSuchAlgorithmException exception) {
            throw new InternalException("해싱 처리 중 서버 내 문제가 발생했습니다.");
        }
    }

    @Override
    public String hashWithSalt(String password, String salt) {
        // TODO 특정 개인에 대한 솔트를 추가하는 로직이 필요하다.
        // TODO 진짜 Bcrypt처럼 만들어보자..
        /*
                try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA);
            messageDigest.update((password).getBytes());
            byte[] encryptedPassword = messageDigest.digest();
            return convertByteToString(encryptedPassword);
        } catch (NoSuchAlgorithmException exception) {
            throw new InternalException("해싱 처리 중 서버 내 문제가 발생했습니다.");
        }
         */
        return "I Wanna Be BCryptPasswordEncoder";
    }

    @Override
    public Boolean matches(String rawPassword, String encryptedPassword) {
        return onlyHash(rawPassword).equals(encryptedPassword);
    }
}
