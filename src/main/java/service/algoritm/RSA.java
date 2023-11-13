package service.algoritm;

import enums.AlgorithmBits;
import service.Algorithm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class RSA implements Algorithm {

    private final BigInteger privateKey;
    private final BigInteger publicKey;
    private final BigInteger modulus;

    public RSA(AlgorithmBits bitLength, BigInteger publicKey) {
        var random = new SecureRandom();
        var p = BigInteger.probablePrime(bitLength.getBitLength(), random);
        var q = BigInteger.probablePrime(bitLength.getBitLength(), random);

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        this.modulus = p.multiply(q);
        this.publicKey = publicKey;
        privateKey = publicKey.modInverse(phi);
    }

    @Override
    public String encrypt(String message) {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        BigInteger plaintext = new BigInteger(messageBytes);
        BigInteger ciphertext = plaintext.modPow(publicKey, modulus);
        return ciphertext.toString();
    }

    @Override
    public String decrypt(String ciphertext) {
        BigInteger encryptedMessage = new BigInteger(ciphertext);
        BigInteger decrypted = encryptedMessage.modPow(privateKey, modulus);
        byte[] decryptedBytes = decrypted.toByteArray();
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}
