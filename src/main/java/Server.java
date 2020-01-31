import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Server {

    private ServerSocket serverSocket;
    private final int port;
    private ArrayList<ClientHandler> handlers;
    final Logger logger = Logger.getLogger(this.getClass().getName());

    public Server(int port) {
        this.port = port;
        this.handlers = new ArrayList<>();
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        logger.info("Server " + InetAddress.getLocalHost() + " started, listening on port " + port);
        acceptClients();
    }

    private void acceptClients() throws IOException {
        while(true) {
            Socket client = serverSocket.accept();
            ClientHandler handler = new ClientHandler(client, this);
            handlers.add(handler);
            Thread t = new Thread(handler);
            t.start();
        }
    }

    public void sendM

    public synchronized void sendPointsInformationToAllClients(PointsInformation pointsInformation) {
        handlers.forEach(x -> x.sendMessage(message));
    }

    public void sendAllItems(ClientHandler client) {
        logger.info(client.getName() + " requested all items.");
        if(items.isEmpty()) {
            client.sendMessage("The list is empty!");
            return;
        }
        for(Map.Entry<String, String> entry : items.entrySet())
            client.sendMessage("<" + entry.getKey() + "> : " + entry.getValue());
    }

    public void sendItem(String key, ClientHandler client) {
        if(items.containsKey(key)) {
            client.sendMessage("<" + key + "> : " + items.get(key));
            logger.info(client.getName() + " requested value of '" + key + "'.");
        }
        else {
            client.sendMessage("Key '" + key + "' doesn't exist.");
            logger.info(client.getName() + " requested non-existent item '" + key + "'.");
        }
    }

    public void addItem(String key, String value, ClientHandler sender) {
        String action = (items.containsKey(key)) ? "updated " : "added ";
        items.put(key, value);
        String message = sender.getName() + " " + action + key + " (value: " + value + ")";
        logger.info(message);
        sendMessageToAllClients(message);
    }

    public void removeItem(String key, ClientHandler sender) {
        if(!items.containsKey(key)) {
            logger.info(sender.getName() + " tried to remove non-existent item '" + key + "'.");
            sender.sendMessage("Key '" + key + "' doesn't exist.");
        }
        else {
            items.remove(key);
            logger.info(sender.getName() + " removed item '" + key + "'.");
            sendMessageToAllClients(sender.getName() + " removed item '" + key + "'.");
        }
    }
}
