import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public void startServer() {
		try {
			
			while(!serverSocket.isClosed()) {
				
				Socket socket = serverSocket.accept();
				System.out.println("A new client has connected!");
				ClientHandler clientHandler = new ClientHandler(socket);
					
				Thread thread = new Thread(clientHandler);
				thread.start();
				
			}
			
		} catch (Exception e) {
			
		}
	}
	
	public void closeServerSocket() {
		try {
			
			if(serverSocket != null) {
				serverSocket.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void  main(String[] args) throws IOException {
		
		Server server = new Server();
		server.startServer();
	}
	
}
