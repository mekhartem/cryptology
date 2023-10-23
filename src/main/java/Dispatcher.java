import enums.AlgorithmBits;
import service.algoritm.Algorithm;
import service.algoritm.Shamir;

public class Dispatcher {

    public static final String MESSAGE = "Hello world";

    public static void main(String[] args) {
        Algorithm algorithm = new Shamir(AlgorithmBits.BITS_1024);

        String encryptedMessage = algorithm.encrypt(MESSAGE);
        String decryptedMessage = algorithm.decrypt(encryptedMessage);

        System.out.println("Original Message: " + MESSAGE);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
