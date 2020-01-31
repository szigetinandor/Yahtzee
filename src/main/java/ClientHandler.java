import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Server server;
    private final Socket client;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private String name;

    public ClientHandler(Socket client, Server server) throws IOException {
        this.server = server;
        this.client = client;
        this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.writer = new PrintWriter(client.getOutputStream(), true);
    }

    public void run() {
        setName();
        while (true) {
            try {
                String message = reader.readLine();
                processRequest(message);
            } catch (IOException e) {
                try {
                    client.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (WrongRequestFormatException e) {
                server.logger.info(e.getMessage());
            }
        }
    }

    private void processRequest(String message) throws  WrongRequestFormatException {
        Request request = Request.get(message);
        request.process(this, server);
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    private void setName() {
        try {
            this.name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.logger.info(this.name + " connected.");
    }

    public String getName() {
        return this.name;
    }

}
