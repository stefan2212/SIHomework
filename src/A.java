/**
 * Created by stfcr on 12/12/2016.
 */
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class A {
    private String k3;
    private String modCriptare;
    private ArrayList<byte[]> firstKeyEncrypted;
    private String firstKey;
    public A(String key){
        k3=key;
        Scanner s;
        System.out.println("Introduceti modul de criptare dorit");
        s=new Scanner(System.in);
        modCriptare=s.next();
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
    public String criptMode(){
        return modCriptare;
    }
    public ArrayList<byte[]> start(boolean canStart){
        ArrayList<byte[]>cipherText=new ArrayList<byte[]>();
        if (canStart==false)
            return null;
        String text="";
        text=readFromFile("fisier.txt");
        if(modCriptare.equals("ECB")){
            ECB e=new ECB();
            cipherText=e.ECBCipher(text,firstKey);}
        else if(modCriptare.equals("CBC")){
            CBC e=new CBC();
            cipherText=e.CBCCipher(text,firstKey);}
        return cipherText;
    }

    private String readFromFile(String filename){
        String text="";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                text+=sCurrentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
