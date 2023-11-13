package service.algoritm;

import enums.AlgorithmBits;
import service.Algorithm;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamal implements Algorithm {

    private final BigInteger p; // large prime number
    private final BigInteger g; // primitive root modulo p
    private final BigInteger x; // private key
    private final BigInteger y; // public key

    public ElGamal(AlgorithmBits algorithmBits) {
        var random = new SecureRandom();
        this.p = BigInteger.probablePrime(algorithmBits.getBitLength(), random);
        this.g = BigInteger.valueOf(2);

        this.x = new BigInteger(256, random)
                .mod(p.subtract(BigInteger.ONE))
                .add(BigInteger.ONE);

        this.y = g.modPow(x, p);
    }

    @Override
    public String encrypt(String message) {
        // Generate random secret key k
        var random = new SecureRandom();
        var k = new BigInteger(p.bitLength() - 1, random)
                .mod(p.subtract(BigInteger.ONE))
                .add(BigInteger.ONE);

        // Calculate shared secret S = g^k mod p
        BigInteger S = g.modPow(k, p);

        // Calculate ciphertext pair (c1, c2)
        BigInteger plaintext = new BigInteger(message.getBytes());
        BigInteger c2 = plaintext.multiply(y.modPow(k, p)).mod(p);

        return S + "," + c2;
    }

    @Override
    public String decrypt(String ciphertext) {

        String[] parts = ciphertext.split(",");
        BigInteger c1 = new BigInteger(parts[0]);
        BigInteger c2 = new BigInteger(parts[1]);

        // Calculate the shared secret S = c1^x mod p
        BigInteger S = c1.modPow(x, p);

        // Calculate the plaintext message = c2 * (S^(-1) mod p) mod p
        BigInteger plaintext = c2.multiply(S.modInverse(p)).mod(p);

        // Convert the plaintext back to a string and return
        return new String(plaintext.toByteArray());
    }
}
