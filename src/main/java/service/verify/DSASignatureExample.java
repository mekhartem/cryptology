package service.verify;

import java.security.*;

public class DSASignatureExample {
    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        Signature dsa = Signature.getInstance("SHA256withDSA");
        dsa.initSign(privateKey);

        String message = "Text for sign";
        byte[] bytes = message.getBytes();
        dsa.update(bytes);

        byte[] signature = dsa.sign();

        Signature verifyDsa = Signature.getInstance("SHA256withDSA");
        verifyDsa.initVerify(publicKey);
        verifyDsa.update(bytes);

        boolean verified = verifyDsa.verify(signature);
        System.out.println("Is verified: " + verified);
    }
}
