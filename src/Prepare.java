import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Set;
import java.util.Vector;

@WebService(targetNamespace = "default")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class Prepare {
    private Set<Integer> hashmap = main.hashmap;
    private int n = 0;
    public Prepare(){

    }

    public @WebResult(name = "PrimeVector")
    Vector<Integer> newWorker(@WebParam(name = "nPrimes") int n){
        Worker worker = new Worker(main.hashmap, n);
        Vector<Integer> vec = worker.getPrimes();
        return vec;
    }

    public @WebResult(name = "PrimeString") String primesString(@WebParam(name = "nPrimes") int n){
        StringBuffer buff = new StringBuffer();
        for(int x : newWorker(n)){
            buff.append(x);
            buff.append(", ");
        }
        //buff.delete(buff.length()-1, buff.length()-3);
        return buff.toString();
    }

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
