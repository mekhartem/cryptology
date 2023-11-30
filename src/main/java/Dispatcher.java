import enums.AlgorithmBits;
import service.algoritm.RSA;
import java.math.BigInteger;

public class Dispatcher {

    public static final String MESSAGE = "Hello world";
    public static final String PUBLIC_KEY = "65537";

    public static void main(String[] args) {
        var algorithm = new RSA(AlgorithmBits.BITS_1024, new BigInteger(PUBLIC_KEY));

        String encryptedMessage = algorithm.encrypt(MESSAGE);
        String decryptedMessage = algorithm.decrypt(encryptedMessage);

        System.out.println("Original Message: " + MESSAGE);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
