package service.verify;

import lombok.SneakyThrows;

import java.security.*;
import java.util.Arrays;
import javax.crypto.KeyAgreement;

public class DiffieHellmanExample {

    @SneakyThrows
    private static KeyPairGenerator getKeyPair() {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(2048);

        return keyPairGenerator;
    }

    @SneakyThrows
    private static byte[] generateSecret(PublicKey publicKey, PrivateKey privateKey) {
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey, true);

        return keyAgreement.generateSecret();
    }


    public static void main(String[] args) {

        KeyPairGenerator keyPairGenerator = getKeyPair();

        KeyPair keyPairA = keyPairGenerator.generateKeyPair();
        KeyPair keyPairB = keyPairGenerator.generateKeyPair();

        byte[] secretKeyA = generateSecret(keyPairB.getPublic(), keyPairA.getPrivate());
        byte[] secretKeyB = generateSecret(keyPairA.getPublic(), keyPairB.getPrivate());

        if (Arrays.equals(secretKeyA, secretKeyB)) {
            System.out.println("Secret the same!");
            System.out.println("Secret: " + bytesToHex(secretKeyA));
        } else {
            System.out.println("Invalid secret!");
        }
    }

    private static String bytesToHex(byte[] bytes) {
        var result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}

