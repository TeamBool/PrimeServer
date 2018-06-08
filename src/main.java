import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.swing.*;
import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;

public class main {
    //public static LinkedHashSet<Integer> hashmap = new LinkedHashSet<>();
    public static ConcurrentHashMap hashmaps = new ConcurrentHashMap();
    public static Set hashmap = java.util.Collections.newSetFromMap(hashmaps);
    //public static Vector<Integer> primes = new Vector<Integer>();
    //public static Vector<Worker> workers = new Vector<Worker>();
    //public static Collection primes = Collections.synchronizedCollection(new Vector<>());
    public static boolean free = true;
    public static void main(String[] args){
        Endpoint endpoint = Endpoint.create(new Prepare());
        //Endpoint endpoint = Endpoint.publish( "http://localhost:8080/services", new StockPrice() );
        List<Handler> handlerChain = endpoint.getBinding().getHandlerChain();
        handlerChain.add(new SOAPLoggingHandler());
        endpoint.getBinding().setHandlerChain(handlerChain);
        endpoint.publish("http://localhost:8080/services");
        //JOptionPane.showMessageDialog( null, "Server beenden" );
        //endpoint.stop();
    }

}
