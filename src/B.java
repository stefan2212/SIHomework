import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by stfcr on 12/12/2016.
 */
public class B {
    private String k3;
    static String modCriptare;
    private ArrayList<byte[]>firstKeyEncrypted;
    private String firstKey;
    public B(){}
    public B(String modCrypto,String key){
        k3=key;
        modCriptare=modCrypto;
    }
    public void getFirstKey(){
        KM km=new KM();
        try {
            firstKeyEncrypted=km.getKey(modCriptare);
            if(modCriptare.equals("ECB")){
                ECB d=new ECB();
                firstKey=d.ECBDecrypt(firstKeyEncrypted,k3);}
            else if(modCriptare.equals("CBC")){
                ECB d=new ECB();
                firstKey=d.ECBDecrypt(firstKeyEncrypted,k3);}
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public boolean trimiteMesaj(){
        return true;
    }
    public String getPlainText(ArrayList<byte[]>cipherText){
        String plainText="";
        if(modCriptare.equals("ECB")){
            ECB d=new ECB();
            plainText=d.ECBDecrypt(cipherText,firstKey);}
        else if(modCriptare.equals("CBC")) {
            CBC d = new CBC();
            plainText=d.CBCDecrypt(cipherText,firstKey);
        }
        return plainText;
    }
}
