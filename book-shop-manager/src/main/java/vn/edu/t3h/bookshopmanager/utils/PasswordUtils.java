package vn.edu.t3h.bookshopmanager.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtils {
    private static final String ALGORITHM = "AES";
    private static SecretKey secretKey;

    static {
        String secretKeyString = "mySuperSecretKey1234567890123456";
        byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        secretKey = new SecretKeySpec(secretKeyBytes, ALGORITHM);
    }

    public static String encryptPassword(String password) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptBytes);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decryptPassword(String password) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(cipher.DECRYPT_MODE, secretKey);
            byte[] decryptBytes = cipher.doFinal(Base64.getDecoder().decode(password));
            return new String(decryptBytes, StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("admin"));
        System.out.println(encryptPassword("user"));
    }
}
