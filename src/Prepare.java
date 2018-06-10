import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListSet;

@WebService(targetNamespace = "default")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class Prepare {
    private int n = 0;
    public static ConcurrentSkipListSet conSet = new ConcurrentSkipListSet();
    public Prepare(){}
    @WebMethod(operationName = "newWorker")
    public @WebResult(name = "PrimeVector")
    Vector<Integer> newWorker(@WebParam(name = "nPrimes") int n){
        Vector<Integer> vec = null;
        try {
            synchronized (this.conSet) {
                vec = new Worker(n).call();
                System.out.println("Size: " + this.conSet.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vec;
    }
    @WebMethod(operationName = "primesString")
    public @WebResult(name = "PrimeString") String primesString(@WebParam(name = "nPrimes") int n){
        StringBuffer buff = new StringBuffer();
        for(int x : newWorker(n)){
            buff.append(x);
            buff.append(", ");
        }
        buff.deleteCharAt(buff.length()-1);
        buff.deleteCharAt(buff.length()-1);
        return buff.toString();
    }
    @WebMethod(operationName = "primesArray")
    public @WebResult(name = "PrimeArray")
    int[] primesArray(@WebParam(name = "nPrimes") int n){
        int array[] = new int[n];
        Vector<Integer> vec = newWorker(n);
        for(int i = 0; i < vec.size(); i++){
            array[i] = vec.elementAt(i);
        }
        return array;
    }
}
