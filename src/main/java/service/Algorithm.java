package service;

public interface Algorithm {
    String encrypt(String message);

    String decrypt(String ciphertext);
}
