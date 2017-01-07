/**
 * Created by stfcr on 12/12/2016.
 */
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class KM {
    private String k1 = "KDGYHQf92E9S2m3B";
    private String k2 = "WrWc9gFNnzkrEkzc";
    private String k3 = "P8Dvje2Y7WQ6UdWk";

    public String getK3() {
        return k3;
    }

    public ArrayList<byte[]> getKey(String cryptoMode) throws NoSuchPaddingException, NoSuchAlgorithmException {
        SecretKeySpec SecKey = null;
        ArrayList<byte[]> key=new ArrayList<byte[]>();
        if (cryptoMode.equals("ECB")) {
            ECB e=new ECB();
            key=e.ECBCipher(k1,k3);
        } else if (cryptoMode.equals("CBC")){
            ECB e=new ECB();
            key=e.ECBCipher(k2,k3);}
        return key;
    }
}
