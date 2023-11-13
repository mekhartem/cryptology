package service.algoritm;

import enums.AlgorithmBits;
import service.Algorithm;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Shamir implements Algorithm {
    private final BigInteger p; // Prime number for the algorithm
    private final BigInteger a; // Coefficient for encryption
    private final BigInteger b; // Coefficient for encryption

    public Shamir(AlgorithmBits algorithmBits) {
        p = generateRandomPrime(algorithmBits.getBitLength());

        SecureRandom secureRandom = new SecureRandom();
        a = initCoefficient(p, secureRandom);
        b = initCoefficient(p, secureRandom);
    }

    private BigInteger initCoefficient(BigInteger p, SecureRandom sr){
        return new BigInteger(p.bitCount(), sr).mod(p.subtract(BigInteger.ONE)).add(BigInteger.ONE);
    }

    private BigInteger generateRandomPrime(int bits) {
        SecureRandom secureRandom = new SecureRandom();
        return BigInteger.probablePrime(bits, secureRandom);
    }

    @Override
    public String encrypt(String message) {
        BigInteger m = new BigInteger(message.getBytes());

        BigInteger ciphertext = m.add(a.multiply(b)).mod(p);
        return ciphertext.toString();
    }

    @Override
    public String decrypt(String ciphertext) {
        BigInteger c = new BigInteger(ciphertext);

        BigInteger decryptedValue = c.subtract(a.multiply(b)).mod(p);

        return new String(decryptedValue.toByteArray());
    }
}
