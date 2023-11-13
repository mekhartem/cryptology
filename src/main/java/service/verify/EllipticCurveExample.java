package service.verify;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.ECGenParameterSpec;


public class EllipticCurveExample {

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String msg = "Hello world";
        byte[] dataToSign = msg.getBytes();
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
        signature.initSign(keyPair.getPrivate());
        signature.update(dataToSign);
        byte[] signatureBytes = signature.sign();
        System.out.println("Signature: " + bytesToHex(signatureBytes));

        System.out.println("Public key: " + keyPair.getPublic());
        signature.initVerify(keyPair.getPublic());
        signature.update(dataToSign);
        boolean isSignatureValid = signature.verify(signatureBytes);

        System.out.println("Is signature valid? " + isSignatureValid);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}
