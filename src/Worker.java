import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashSet;
import java.util.Vector;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@WebService(targetNamespace = "default")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Worker implements Callable<Vector<Integer>> {
    private Vector<Integer> primes = new Vector<Integer>();
    private int n = -1;
    Worker() {
        //super("my extending thread");
    }
    Worker(int n) {
        //super("my extending thread");
        this.n = n;
        //start();
    }
    public void run() {
        try {
            System.out.println("start " + n);
            PrimeFarm farm = new PrimeFarm();
            long start = System.currentTimeMillis();
            NumberFormat formatter = new DecimalFormat("#0.00000");
            primes = farm.getPrimes(n);
            long end = System.currentTimeMillis();
            System.out.print(n + "th prime: ");
            int last = -1;
            for (Iterator iter = this.primes.iterator(); iter.hasNext(); )
                last = (int) iter.next();
            System.out.println(last);
            System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
            System.out.println("Thread vec " + this.n + " : " + primes.size());
        } catch (Exception e) {
            System.out.println("my thread interrupted " + n);
        }
    }

    @WebMethod(operationName = "getPrimesVec")
    public @WebResult(name = "PrimeVector")Vector<Integer> getPrimesVec() {
        return this.primes;
    }

    @WebMethod(operationName = "getPrimesString")
    public @WebResult(name = "primesString") String getPrimesString() {
        StringBuffer buff = new StringBuffer();
        for(int x : this.primes){
            buff.append(x);
            buff.append(", ");
        }
        //buff.delete(buff.length()-1, buff.length()-3);
        System.out.println("Buffer: " + buff.toString());
        return buff.toString();
    }

    @WebMethod(operationName = "getPrimesArray")
    public @WebResult(name = "primesArray") int[] getPrimesArray(){
        int array[] = new int[n];
        for(int i = 0; i < this.primes.size(); i++){
            array[i] = this.primes.elementAt(i);
            System.out.println(this.primes.elementAt(i) + " " + array[i]);
        }
        return array;
    }

    @WebMethod(operationName = "setN")
    public void setN(@WebParam(name = "n") int n){
        this.n = n;
        //start();
    }

    /*@WebMethod(operationName = "stop")
    public void stopT(){
        this.stop();
    }*/

    @Override
    public Vector<Integer> call() throws Exception {
        try{
        System.out.println("start " + n);
        PrimeFarm farm = new PrimeFarm();
        long start = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        primes = farm.getPrimes(n);
        long end = System.currentTimeMillis();
        System.out.print(n + "th prime: ");
        int last = -1;
        for (Iterator iter = this.primes.iterator(); iter.hasNext(); )
            last = (int) iter.next();
        System.out.println(last);
        System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
        System.out.println("Thread vec " + this.n + " : " + primes.size());
    } catch (Exception e) {
        System.out.println("my thread interrupted " + n);
    }
    return primes;
    }
}