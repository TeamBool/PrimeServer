import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashSet;
import java.util.Vector;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

class Worker extends Thread {
    private Vector<Integer> primes = new Vector<Integer>();
    private int n;
    private Set hashes;

    //Worker(LinkedHashSet<Integer> hashmap, Vector<Integer> primes, int n)
    Worker(Set hashmap, int n) {
        super("my extending thread");
        main.free = false;
        //TODO THREADSAFE!!!!!!
        //this.hashmap = (LinkedHashSet<Integer>)hashmap.clone();
        //this.primes = (Vector<Integer>) primes.clone();
        /*synchronized (hashmap){
            for(Iterator iter = hashmap.iterator(); iter.hasNext();){
                int entry = (int) iter.next();
                this.shashmap.add(entry);
            }
            this.org = hashmap;
        }*/
        this.hashes = hashmap;

        this.n = n;
        start();
    }

    public void run() {
        try {
            System.out.println("start " + n);
            //synchronized (hashmap) {
            //PrimeFarm farm = new PrimeFarm(this.shashmap, this.primes);
            PrimeFarm farm = new PrimeFarm(this.hashes, this.primes);
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
            System.out.println("Thread set " + this.n + " : " + this.hashes.size());
            //}
        } catch (Exception e) {
            System.out.println("my thread interrupted " + n);
        }
        /*synchronized (main.primes){
            if(main.primes.size() >= this.primes.size()){
                return;
            }
            for(int i = main.primes.size(); i < this.primes.size(); i++){
                main.primes.add(this.primes.elementAt(i));
            }
        }*/
    }

    public Vector<Integer> getPrimes() {
        return this.primes;
    }
}