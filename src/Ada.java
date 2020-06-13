import org.jetbrains.annotations.NotNull;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Handles program security including password verification and hashing
 */
public class Ada {
    /**
     * Generates password hash from char[] input. Actual hashing method is called within main method for security
     * @param passwordInput password to be hashed
     * @return hashed password
     */
    @NotNull
    public static String main(char[] passwordInput) {
        String hash = "";
        hash = genHash(passwordInput);
        return hash;
    }

    /**
     * Accesses security methods and generates hash from password
     * @param password password to be hashed
     * @return concatenated string containing iterations,salt and hash speperated by ":"
     */
    @NotNull
    private static String genHash(char[] password) {
        int iterations = 1000;
        byte[] salt;
        String toBeStored = "";
        salt = getSalt();
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, 64 * 8);
        SecretKeyFactory skf = null;
        try {
            //Sets skf's algorithm
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("[EXCEPTION] NoSuchAlgorithm exception at Ada:39");
            e.printStackTrace();
        }
        byte[] hash = new byte[0];
        try {
            assert skf != null;
            hash = skf.generateSecret(spec).getEncoded();
        }
        catch (InvalidKeySpecException e) {
            System.out.println("[EXCEPTION] InvalidKeySpec exception at Ada:48");
            e.printStackTrace();
        }
        //Concatenates the three components to be stored
        System.out.println(iterations + ":" + toHex(salt) + ":" + toHex(hash));
        toBeStored = iterations + ":" + toHex(salt) + ":" + toHex(hash);
        return toBeStored;
    }

    /**
     * Utilises the SecureRandom class to generate salt for genHash() to use
     * @return generated salt
     */
    @NotNull
    private static byte[] getSalt() {
        SecureRandom sr = null;
        try {
            /*
            The fixed declaration of the algorithm is bad practice. However, since this system is not likely to
            require extensive security, this should be OK.
             */
            sr = SecureRandom.getInstance("SHA1PRNG");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] salt = new byte[16];
        assert sr != null; //This is to catch any freaky errors that wouldn't cause a NoSuchAlgorithm exception.
        sr.nextBytes(salt);
        return salt;
    }

    /**
     * Converts denary input to hexadecimal. Uses byte array for security
     * @param array denary input
     * @return hexadecimal string conversion of input
     */
    @NotNull
    private static String toHex(byte[] array) {
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

    /**
     * Compares entered password with corresponding stored password
     * @param passwordInput user's password input
     * @param storedPassword password stored in database
     * @return true if passwords match
     */
    public static boolean validatePassword(@NotNull char[] passwordInput, @NotNull String storedPassword) {
        //Splits the encrypted password back into it's original parts
        String[] parts = storedPassword.split(":");
        //Assigns these parts to variables
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
        PBEKeySpec spec = new PBEKeySpec(passwordInput, salt, iterations, hash.length * 8);
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("[EXCEPTION] NoSuchAlgorithm exception at Ada:112");
            e.printStackTrace();
        }
        byte[] testHash = new byte[0];
        try {
            assert skf != null; //Prevents another snekay error that happened
            testHash = skf.generateSecret(spec).getEncoded();
        }
        catch (InvalidKeySpecException e) {
            System.out.println("[EXCEPTION] InvalidKeySpecException at Ada:121");
            e.printStackTrace();
        }

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    /**
     * Converts hexadecimal input to denary. Uses byte array for security
     * @param hex hexadecimal number to be converted
     * @return denary conversion of input
     */
    @NotNull
    private static byte[] fromHex(@NotNull String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
