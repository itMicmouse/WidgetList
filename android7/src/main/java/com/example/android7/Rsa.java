package com.example.android7;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by android on 2017/5/19.
 */

public class Rsa {
    private static final String RSA_PUBLICE ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMeFYOUcucRHdrqP\n" +
            "zmkgnkEj0PQTQBZ9HtKDpQzZaU9Wy4buE4T9Kpw8KvpRj6q4NyY0lrDFfWWCjIVB\n" +
            "468MBFiapSQJs0kxb4WcCWDzalohOAe9UxrmebfBYZm/LXPvMCEPb9tl91Tc3t0v\n" +
            "N9jSVHRr8cQRhgOSb9Ov6EKYgqWVAgMBAAECgYAQYP6nUEhfuI4impOTRb44Eryg\n" +
            "jMQCFJtnJK9z8pUWkkV6+ihYDoBk+vt8OR3WIfFFDaQhNtUodCMc/V+3iwEAL7Ds\n" +
            "yHBo70pR30feyVym+Uo1S5WFzigSVYpcjbvuQXD5ZDM1bFe0hZB6T/xt7+6gYFn3\n" +
            "lys2d03Ri+q7914ksQJBAP8UuVvhBGmmSgOeBkSYXFp7yeKRPySDqnB2hIGb1maL\n" +
            "nqKMMProrHJw1YRFaNZK3tyI/pGWnSWt4rl5FoztDr8CQQDIPWiFOSyfR7/9+aBJ\n" +
            "Zg4+NY1UPzkJcAh7I7P5mKiCBH5fWNWlPvYzwZe6nUQwu7kC1LGmoUj+WZjyCw/G\n" +
            "lTSrAkANyjoHHJAXghToQyfFl5MOlTih1gZa2/9f2xhoxVr+6jdmRDv1pJSGD1GL\n" +
            "uBDl3OBVfdZ9Wm4edlsWedRdRAeTAkBpkbahRlt4PmPCivEuwVeRBLrWR97SUval\n" +
            "tVUVqp5sreHScwtwa5lyAAb2pCNnbhFEpM5kg3PwZVx1f895awCxAkEA8SWgNaTq\n" +
            "d+lVoQpgB6cgonP7tJ+bp4DzMVaK36MBrPbCZxQ5zJQ6JYnPoKr6dIGoNOXg+gQe\n" +
            "8yHVIOIVIxZMhQ==\n";
    private static final String ALGORITHM = "RSA";

    /**
     * 得到公钥
     * @param algorithm
     * @param bysKey
     * @return
     */
    private static PublicKey getPublicKeyFromX509(String algorithm,String bysKey) throws NoSuchAlgorithmException, Exception {
        byte[] decodedKey = Base64.decode(bysKey,Base64.DEFAULT);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x509);
    }

    /**
     * 得到私钥
     * @param algorithm
     * @param bysKey
     * @return
     */
    private static PrivateKey getPriceKeyFromX509(String algorithm, String bysKey) throws NoSuchAlgorithmException, Exception {
        byte[] decodedKey = Base64.decode(bysKey,Base64.DEFAULT);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePrivate(x509);
    }

    /**
     * 使用公钥加密
     * @param content
     * @return
     */
    public static String encryptByPublic(String content) {
        try {
            PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);

            byte plaintext[] = content.getBytes("UTF-8");
            byte[] output = cipher.doFinal(plaintext);

            String s = new String(Base64.encode(output,Base64.DEFAULT));

            return s;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 使用公钥解密
     * @param content 密文
     * @return 解密后的字符串
     */
    public static String decryptByPublic(String content) {
        try {
            PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, pubkey);
            InputStream ins = new ByteArrayInputStream(Base64.decode(content,Base64.DEFAULT));
            ByteArrayOutputStream writer = new ByteArrayOutputStream();
            byte[] buf = new byte[128];
            int bufl;
            while ((bufl = ins.read(buf)) != -1) {
                byte[] block = null;
                if (buf.length == bufl) {
                    block = buf;
                } else {
                    block = new byte[bufl];
                    for (int i = 0; i < bufl; i++) {
                        block[i] = buf[i];
                    }
                }
                writer.write(cipher.doFinal(block));
            }
            return new String(writer.toByteArray(), "utf-8");
        } catch (Exception e) {
            return null;
        }
    }





}
