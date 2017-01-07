import java.util.ArrayList;

/**
 * Created by stfcr on 12/12/2016.
 */
public class Main {
    public static void main(String args[]){
        KM km=new KM();
        A a=new A(km.getK3());
        B b=new B(a.criptMode(),km.getK3());
        b.getFirstKey();
        System.out.println();
        a.getFirstKey();
        boolean canStart=b.trimiteMesaj();
        System.out.println(b.getPlainText(a.start(canStart)));
    }
}
