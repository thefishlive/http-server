package uk.co.thefishlive.http.crypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private static final String SHA_256 = "SHA-256";

    public static String calculateHash(byte[] data, String function) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(function);
        md.update(data);
        byte[] digest = md.digest();
        return String.format("%064x", new BigInteger(digest));
    }

    public static String sha256(byte[] text) {
        try {
            return calculateHash(text, SHA_256);
        } catch (NoSuchAlgorithmException ex) {
            // This is not possible
            return "";
        }
    }
}
