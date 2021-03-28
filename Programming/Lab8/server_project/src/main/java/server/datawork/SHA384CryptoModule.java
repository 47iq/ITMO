package server.datawork;

import org.apache.logging.log4j.LogManager;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA384CryptoModule implements CryptoModule {

    @Override
    public String hash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            byte[] messageDigest = md.digest(str.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(no.toString(16));
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        } catch (Exception e) {
            e.printStackTrace();
            LogManager.getLogger().error("Can't encrypt string.");
            System.exit(1);
            return null;
        }
    }
}
