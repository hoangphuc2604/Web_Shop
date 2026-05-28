package vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class GenSigOrder {

    public static PrivateKey loadPrivateKeyBase64(String privateKeyB64) throws Exception {
        byte[] encKey = Base64.getDecoder().decode(privateKeyB64);
        PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(encKey);
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
        return keyFactory.generatePrivate(priKeySpec);
    }

    public static String signToBase64(String data, String privateKeyB64) throws Exception {
        PrivateKey privateKey = loadPrivateKeyBase64(privateKeyB64);
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(privateKey);
        dsa.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] realSig = dsa.sign();
        return Base64.getEncoder().encodeToString(realSig);
    }
}