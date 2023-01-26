//package com.iot.app;
//
//import android.util.Base64;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//
//public class CryptoHelper {
//    private final SecretKey secretKey;
//
//    public CryptoHelper() throws IOException {
//        String stringKey = new String(Files.readAllBytes(Paths.get("java/com/iot/app/key.txt")));
//        byte[] encodedKey = Base64.decode(stringKey, Base64.DEFAULT);
//        secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
//
//    }
//
//
//    public static String encrypt(String algorithm, String input, SecretKey key,
//                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
//            InvalidAlgorithmParameterException, InvalidKeyException,
//            BadPaddingException, IllegalBlockSizeException {
//
//        Cipher cipher = Cipher.getInstance(algorithm);
//        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
//        byte[] cipherText = cipher.doFinal(input.getBytes());
//        return Base64.getEncoder()
//                .encodeToString(cipherText);
//    }
//
//    public static String decrypt(String cipherText, String key) {
//        return cipherText;
//    }
//}
