import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.swing.*;
import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;

public class main {
    public static void main(String[] args){
        Endpoint endpoint = Endpoint.create(new Prepare());
        List<Handler> handlerChain = endpoint.getBinding().getHandlerChain();
        handlerChain.add(new SOAPLoggingHandler());
        endpoint.getBinding().setHandlerChain(handlerChain);
        endpoint.publish("http://localhost:8080/services");
        JOptionPane.showMessageDialog( null, "Server beenden" );
        endpoint.stop();
    }

}
