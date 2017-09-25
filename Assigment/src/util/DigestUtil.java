package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {

    /**
     * Encrypt the password via MD5, with salt
     * @param：password The password that is supposed to be encryted
     */
    public static String encryptPWD(String password) {
        byte tempResult[] = new byte[16];
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(("www.MD5.com.cn" + password).getBytes());
            tempResult = md.digest();

            for (int i = 0; i < tempResult.length; i++) {
                if (tempResult[i] < 0) {
                    tempResult[i] += 128;
                }
                String temp = Integer.toHexString(tempResult[i]).toUpperCase();
                if (tempResult[i] < 16) {
                    temp = "0" + temp;
                }
                result += temp;
            }
        } catch (NoSuchAlgorithmException e) {
            result = "";
        }
        return result;
    }
}

