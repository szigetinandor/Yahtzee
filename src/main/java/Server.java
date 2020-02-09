import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Server {

    private ServerSocket serverSocket;
    private final int port;
    private List<ClientHandler> clients;
    final Logger logger = Logger.getLogger(this.getClass().getName());

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
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
            clients.add(handler);
            Thread t = new Thread(handler);
            t.start();
        }
    }

    public void startGame() {
        Game game = new Game(this);
        for(ClientHandler client : clients) {
            client.getPlayer().setGame(game);
            game.assignPlayer(client.getPlayer());
        }
        game.play();
    }

    public void sendMessageToClient(String message, ClientHandler client) {
        client.sendMessage(message);
    }

    public void sendMessageToAllClients(String message) {
        for(ClientHandler client : clients)
            client.sendMessage(message);
    }

}
