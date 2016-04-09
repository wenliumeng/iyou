package org.iyou.common.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.util.Random;

/**
 * 密码工具类
 * Created by seyMour on 2016/4/9.
 * ☞120465271@qq.com☜
 */
public class PasswordUtils {

    public static final Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

    public static String md5Password(String password) {
        return md5PasswordEncoder.encodePassword(password, null);
    }

    public static String randomPassword() {
        char[] pwdchs = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 'd', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        Random random = new Random();
        String randomPassword = "";
        for (int i = 0; i < 6; i++) {
            randomPassword = randomPassword + pwdchs[random.nextInt(64)];
        }
        return randomPassword;
    }
}
