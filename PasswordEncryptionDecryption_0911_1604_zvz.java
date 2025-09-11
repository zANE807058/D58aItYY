// 代码生成时间: 2025-09-11 16:04:28
public class PasswordEncryptionDecryption {

    // Method to encrypt a password
    public static String encryptPassword(String password) {
        try {
            // Use a strong encryption algorithm, e.g., PBEWithMD5AndDES
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            // Convert byte array into signum representation
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception
            throw new RuntimeException("Encryption algorithm not found", e);
        }
    }

    // Method to decrypt a password
    public static String decryptPassword(String encryptedPassword) {
        try {
            // This is a simple example. In practice, decryption should be secure and not reversible.
            // Use the same algorithm and parameters as used in encryption.
            MessageDigest md = MessageDigest.getInstance("MD5");
            // For actual decryption, you would need to reverse the encryption process,
            // which is not practically feasible for MD5. This is a placeholder for demonstration.
            byte[] bytes = md.digest(encryptedPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append((char) b);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception
            throw new RuntimeException("Decryption algorithm not found", e);
        }
    }
}
