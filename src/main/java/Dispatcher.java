import enums.AlgorithmBits;
import service.algoritm.Algorithm;
import service.algoritm.ElGamal;

public class Dispatcher {

    public static final String MESSAGE = "Hello world";

    public static void main(String[] args) {
        Algorithm algorithm = new ElGamal(AlgorithmBits.BITS_256);

        String encryptedMessage = algorithm.encrypt(MESSAGE);
        String decryptedMessage = algorithm.decrypt(encryptedMessage);

        System.out.println("Original Message: " + MESSAGE);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
