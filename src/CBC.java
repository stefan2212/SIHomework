import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CBC {
    private String initVector="V6Aa5j54TghnZ6S5";
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
    public ArrayList<byte []>CBCCipher(String plainText,String key){
        SecretKey Sec=null;
        byte[] iv=null;
        byte[] cryptoText=null;

        ArrayList<String>plainTextPeBlocuri=imparteInBlocuri(plainText);
        ArrayList<byte[]>plainTextBlocks=new ArrayList<byte[]>();
        ArrayList<byte[]>cipherBlocks=new ArrayList<byte[]>();
        int i=0;
        for(String element:plainTextPeBlocuri){
            plainTextBlocks.add(element.getBytes());
        }
        try{
            iv=initVector.getBytes();
            Sec=new SecretKeySpec(key.getBytes("UTF-8"),"AES");
            Cipher c= Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE,Sec);
            byte[] xored;
            for(i=0;i<plainTextBlocks.size();i++)
            {
                if (i==0){
                    xored=XOR2(plainTextBlocks.get(i),iv);
                    cryptoText=c.doFinal(xored);
                    cipherBlocks.add(cryptoText);
                }
                else{
                    int k=i;
                    xored=XOR2(plainTextBlocks.get(i),cipherBlocks.get(--k));
                    cryptoText=c.doFinal(xored);
                    cipherBlocks.add(cryptoText);
                }
            }
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
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
        return cipherBlocks;
    }
    public String CBCDecrypt(ArrayList<byte[]>cipherText,String key){
        SecretKey Sec=null;
        byte[] iv=null;
        byte[] plainTextByte=null;
        ArrayList<byte[]>plainTextBlock=new ArrayList<byte[]>();
        String plainText="";
        try{
            iv=initVector.getBytes();
            Sec=new SecretKeySpec(key.getBytes("UTF-8"),"AES");
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        try {
           Cipher c = Cipher.getInstance("AES");
           c.init(Cipher.DECRYPT_MODE,Sec);
           int i;
           for(i=cipherText.size()-1;i>=0;i--){
                if(i==0){
                    byte crypto[];
                    crypto=c.doFinal(cipherText.get(i));
                    plainTextBlock.add(XOR2(crypto,iv));
                }
                else{
                    int j=i;
                    byte []crypto;
                    crypto=c.doFinal(cipherText.get(i));
                    plainTextBlock.add(XOR2(crypto,cipherText.get(j-1)));
                }
           }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        for(int j=plainTextBlock.size()-1;j>=0;j--)
            plainText+=new String(plainTextBlock.get(j));
        return plainText;
    }
    public byte[] XOR2(byte [] plainText,byte [] cipherText){
        int i;
        byte[] rezultat=new byte[16];
        for(i=0;i<minimul(plainText.length,cipherText.length);i++){
            rezultat[i]= (byte) (plainText[i]^cipherText[i]);
        }
        return rezultat;
    }

    int minimul(int a,int b){
        if(a>b)
            return b;
        return a;
    }
}
