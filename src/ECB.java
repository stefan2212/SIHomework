import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
public class ECB {
    private ArrayList<String> imparteInBlocuri(String plainText){
        ArrayList<String> blocuri=new ArrayList<>();
        int i=0;
        String bloc="";
        for(i=0;i<plainText.length();i++) {
            bloc += plainText.charAt(i);
            if((i+1)%16==0){
                blocuri.add(bloc);
                bloc="";
            }
        }
        if(bloc.length()<16&&bloc.length()>0){
            while(bloc.length()<16)
                bloc+=" ";
            blocuri.add(bloc);
        }
    return blocuri;
    }

    public ArrayList<byte []>ECBCipher(String plainText,String key){
        SecretKey Sec=null;
        byte[] cryptoText=null;
        try{
            Sec=new SecretKeySpec(key.getBytes("UTF-8"),"AES");
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        ArrayList<String> blocuri=imparteInBlocuri(plainText);
        ArrayList<byte[]>blocuriCriptate=new ArrayList<byte[]>();
        try {
            Cipher c= Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE,Sec);
            for (String bloc:blocuri){
                byte[] blocByte=bloc.getBytes();
                cryptoText=c.doFinal(blocByte);
                blocuriCriptate.add(cryptoText);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e){
            e.printStackTrace();
        } catch (BadPaddingException e){
            e.printStackTrace();
        }
        return blocuriCriptate;
    }

    public String ECBDecrypt(ArrayList<byte[]>cipherText,String key){
        SecretKey Sec=null;
        byte[] plainTextByte=null;
        String plainText="";
        try{
            Sec=new SecretKeySpec(key.getBytes("UTF-8"),"AES");
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        try{
            Cipher c= Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE,Sec);
            for(byte[] element:cipherText){
                plainTextByte=c.doFinal(element);
                plainText+=new String(plainTextByte);
            }
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return plainText;
    }
}
