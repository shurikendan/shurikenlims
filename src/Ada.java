import org.jetbrains.annotations.NotNull;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Handles program security including password verification and hashing.
 */
public class Ada {
    /**
     * @param passwordInput Password to be hashed.
     * @return Hashed password.
     */
    @NotNull
    public static String main(char[] passwordInput) {
        String hash = "";
        hash = genHash(passwordInput);
        return hash;
    }

    /**
     * @param password Password to be hashed
     * @return Concatenated string containing iterations,salt and hash speperated by ":".
     */
    @NotNull
    private static String genHash(@NotNull char[] password) {
        int iterations = 1000;
        byte[] salt = new byte[0];
        String toBeStored = "";
        try {
            salt = getSalt();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, 64 * 8);
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = new byte[0];
        try {
            assert skf != null;
            hash = skf.generateSecret(spec).getEncoded();
        }
        catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(iterations + ":" + toHex(salt) + ":" + toHex(hash));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            toBeStored = iterations + ":" + toHex(salt) + ":" + toHex(hash);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return toBeStored;
    }

    //Generates salt
    @NotNull
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    //Converts a String (generated hash) to hex
    @NotNull
    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        }
        else {
            return hex;
        }
    }

    //Checks an entered password against the stored password
    public static boolean validatePassword(@NotNull char[] passwordInput, @NotNull String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
        PBEKeySpec spec = new PBEKeySpec(passwordInput, salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    //Translates hex back into a String
    @NotNull
    private static byte[] fromHex(@NotNull String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
