import java.util.*;
import static java.lang.Math.sqrt;

public class PrimeFarm {
    private int lastN = 0;
    private Vector<Integer> primes = new Vector<Integer>();
    private Set set = Prepare.conSet;

    public PrimeFarm(){
        findN();
    }

    private void findN() {
        //synchronized (this.set) {
            this.lastN = set.size();
        //}
    }

    private int nextPrime(int digit) {
        do {
            digit++;
        }
        while (!isPrime(digit));
        return digit;
    }

    private boolean isPrime(int digit) {
        int k = 1, a = 0, b = 0;
        long sr;
        switch (digit) {
            case 1:
                return false;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return false;
            case 5:
                return true;
            case 6:
                return false;
            case 7:
                return true;
        }
        if (digit % 2 == 0) return false;
        if (digit % 3 == 0) return false;
        if (digit % 5 == 0) return false;
        if (digit % 7 == 0) return false;
        sr = (int) sqrt(digit);
        while (b < sr) {
            a = (6 * k) - 1;
            b = (6 * k) + 1;
            if (digit % a == 0)
                return false;
            if (digit % b == 0)
                return false;
            k += 1;
        }
        return true;
    }

    public Vector<Integer> getPrimes(int n) {
        //synchronized (this.set) {
            Iterator iter = this.set.iterator();
            int last = 1;
            int ncopy = n;
            if (this.primes.size() == n)
                return this.primes;
            if (this.primes.size() > n) {
                this.primes.clear();
            }
            while (iter.hasNext() && ncopy > 0) {
                this.primes.add((int) iter.next());
                last = this.primes.lastElement();
                ncopy--;
            }
            if (ncopy == 0)
                return primes;
            n -= lastN;
            while (n > 0) {
                last = nextPrime(last);
                this.primes.add(last);
                set.add(last);
                n--;
                this.lastN++;
            }
            /*for (int x : this.primes) {
                System.out.println(x);
            }
            //ONLY FOR TEST PURPOSE -> safe time
            */
       // }
        return primes;
    }
}
