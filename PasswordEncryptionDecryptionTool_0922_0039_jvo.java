// 代码生成时间: 2025-09-22 00:39:32
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 密码加密解密工具类
 * @author 你的姓名
 * @version 1.0
 */
public class PasswordEncryptionDecryptionTool {

    // 定义密钥长度
    private static final int KEY_LENGTH = 256;

    // 定义加密解密算法
    private static final String ALGORITHM = "AES";

    private SecretKey secretKey;

    /**
     * 构造函数，生成密钥
     */
    public PasswordEncryptionDecryptionTool() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(KEY_LENGTH, SecureRandom.getInstanceStrong());
        this.secretKey = keyGenerator.generateKey();
    }

    /**
     * 加密密码
     * @param password 待加密密码
     * @return 加密后的密码
     * @throws Exception 加密过程中的异常
     */
    public String encryptPassword(String password) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 解密密码
     * @param encryptedPassword 加密后的密码
     * @return 解密后的密码
     * @throws Exception 解密过程中的异常
     */
    public String decryptPassword(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    /**
     * 主方法，用于测试密码加密解密工具
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        try {
            PasswordEncryptionDecryptionTool tool = new PasswordEncryptionDecryptionTool();
            String password = "123456";

            // 加密密码
            String encryptedPassword = tool.encryptPassword(password);
            System.out.println("加密后的密码：" + encryptedPassword);

            // 解密密码
            String decryptedPassword = tool.decryptPassword(encryptedPassword);
            System.out.println("解密后的密码：" + decryptedPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}