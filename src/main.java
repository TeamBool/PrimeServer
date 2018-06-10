import java.util.*;
import javax.swing.*;
import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;

public class main {
    public static void main(String[] args) {
        Endpoint endpoint = Endpoint.create(new Prepare());
        List<Handler> handlerChain = endpoint.getBinding().getHandlerChain();
        handlerChain.add(new SOAPLoggingHandler());
        endpoint.getBinding().setHandlerChain(handlerChain);
        if (args.length < 1) {
            try {
                endpoint.publish("http://localhost:8080/services");
            } catch (com.sun.xml.internal.ws.server.ServerRtException e) {
                System.out.println(e);
                System.out.println("Port already in use! Choose different one!");
                return;
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Port already in use! Choose different one!");
                return;
            }
        } else {
            if (args[0].equalsIgnoreCase("h")) {
                System.out.println("Usage");
                System.out.println("java -jar prime-server.jar http://host:123.123.123.123:8080/dir");
                return;
            } else {
                try {
                    endpoint.publish(args[0]);
                } catch (com.sun.xml.internal.ws.server.ServerRtException e) {
                    System.out.println(e);
                    System.out.println("Port already in use! Choose different one!");
                    return;
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Port already in use! Choose different one!");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Server beenden");
        endpoint.stop();
    }
}
