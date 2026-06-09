package vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class GenSigOrder {
    public static String signData(String hashData, String privateKeyBase64, String algorithm) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        String signAlgorithm;
        if ("RSA".equalsIgnoreCase(algorithm)) {
            signAlgorithm = "SHA1withRSA";
        } else {
            signAlgorithm = "SHA1withDSA";
        }
        Signature signature = Signature.getInstance(signAlgorithm);
        signature.initSign(privateKey);
        signature.update(hashData.getBytes("UTF-8"));
        byte[] privateSignature = signature.sign();
        return Base64.getEncoder().encodeToString(privateSignature);



    }
}