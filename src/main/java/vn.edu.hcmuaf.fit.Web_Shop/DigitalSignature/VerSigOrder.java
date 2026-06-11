package vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class VerSigOrder {

    public static PublicKey loadPublicKeyBase64(String publicKeyB64,String algorithm) throws Exception {
        byte[] encKey = Base64.getDecoder().decode(publicKeyB64);
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(pubKeySpec);
    }

    public static boolean verifyBase64(String data, String signatureB64, String publicKeyB64,String algorithm) throws Exception {
        PublicKey publicKey = loadPublicKeyBase64(publicKeyB64, algorithm);
        String signAlgorithm;
        if ("RSA".equalsIgnoreCase(algorithm)) {
            signAlgorithm = "SHA1withRSA";
        } else {
            signAlgorithm = "SHA1withDSA";
        }
        Signature sig = Signature.getInstance(signAlgorithm);
        sig.initVerify(publicKey);
        sig.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] sigToVerify = Base64.getDecoder().decode(signatureB64);
        return sig.verify(sigToVerify);
    }
}