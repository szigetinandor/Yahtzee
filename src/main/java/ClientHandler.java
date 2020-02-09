import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private final Server server;
	private final Socket socket;
	private final BufferedReader reader;
	private final PrintWriter writer;
	private Player player;

	public ClientHandler(Socket socket, Server server) throws IOException {
		this.server = server;
		this.socket = socket;
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.writer = new PrintWriter(socket.getOutputStream(), true);
	}

	public void run() {
		setName();
		while (true) {
			try {
				String message = reader.readLine();
				processRequest(message);
			} catch (IOException e) {
				try {
					socket.close();
				} catch (IOException ex) {
					server.logger.info(ex.getMessage());
				}
			} catch (WrongRequestFormatException e) {
				server.logger.info(e.getMessage());
			}
		}
	}

	private void processRequest(String message) throws WrongRequestFormatException {
		Request request = Request.make(message);
		request.process(this.);
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
