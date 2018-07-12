package br.com.omaisfood.utils;

import br.com.omaisfood.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static String cryptPassword(User user){
        // encrypt password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(user.getPassword());
    }

    public static String getAvatarByEmail(String email){
        String hash = Utils.md5Hex(email);
        return String.format("https://s.gravatar.com/avatar/%s?s=%d&d=mm", hash, 150);
    }

    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }

    public static String md5Hex (String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
            //
        } catch (UnsupportedEncodingException e) {
            //
        }
        return null;
    }
}
