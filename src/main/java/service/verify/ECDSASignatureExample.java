package service.verify;

import java.security.*;

public class ECDSASignatureExample {
    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(256, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        Signature ecdsa = Signature.getInstance("SHA256withECDSA");
        ecdsa.initSign(privateKey);

        String message = "Text to sign";
        byte[] bytes = message.getBytes();
        ecdsa.update(bytes);

        byte[] signature = ecdsa.sign();

        Signature verifiedEcdsa = Signature.getInstance("SHA256withECDSA");
        verifiedEcdsa.initVerify(publicKey);
        verifiedEcdsa.update(bytes);

        boolean verified = verifiedEcdsa.verify(signature);
        System.out.println("Is verified: " + verified);
    }
}
