package service.verify;

import java.security.*;
import java.util.Base64;

public class RSAVerifySignature {
    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();

        String dataToSign = "Hello, RSA!";

        byte[] signature = signData(dataToSign, keyPair.getPrivate());

        System.out.println("Signature: " + encodeValue(signature));
        System.out.println("Public key: " + encodeValue(keyPair.getPublic().getEncoded()));

        boolean isSignatureValid = verifySignature(dataToSign, signature, keyPair.getPublic());

        System.out.println("Is signature valid? " + isSignatureValid);
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] signData(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        return signature.sign();
    }

    public static boolean verifySignature(String data, byte[] signature, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(data.getBytes());
        return sig.verify(signature);
    }

    public static String encodeValue(byte[] encodedKey) {
        return Base64.getEncoder().encodeToString(encodedKey);
    }
}
