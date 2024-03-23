import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CreditCardEncryption {

    private static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec sKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
            
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVecto"; // 16 bytes IV
        
        String creditCardNumber = "4111111111111111";
        String encryptedCreditCardNumber = encrypt(key, initVector, creditCardNumber);
        
        System.out.println("Encrypted credit card number: " + encryptedCreditCardNumber);
    }
}
