package service.algoritm;

public interface Algorithm {
    String encrypt(String message);

    String decrypt(String ciphertext);
}
