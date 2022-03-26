import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
	public static void main(String[] args) throws IOException {
		
		Socket socket = null; //objeto que faz conexao 
		InputStreamReader leitor = null; //fluxo dados
		OutputStreamWriter escritorDados = null; //fluxo dados
		BufferedReader bufferedReader = null; //leitor
		BufferedWriter bufferedWriter = null;
		
		ServerSocket socketServidor = null;
		socketServidor = new ServerSocket(4867);
	
		while(true) {
			try {
				socket = socketServidor.accept();
				
				leitor = new InputStreamReader(socket.getInputStream());
				escritorDados = new OutputStreamWriter(socket.getOutputStream());
				
				bufferedReader = new BufferedReader(leitor);
				bufferedWriter = new BufferedWriter(escritorDados);
				
				Scanner scanner = new Scanner(System.in);
				
				while(true) {
					String mensagemClienteRecebe = scanner.nextLine();
					String mensagemCliente = bufferedReader.readLine();
					System.out.println("Cliente mandou: " + mensagemCliente);
					
					bufferedWriter.write("Mensagem recebida: " + mensagemClienteRecebe);
					bufferedWriter.newLine();
					bufferedWriter.flush();
					
					if(mensagemCliente.equalsIgnoreCase("tchau")) {
						break;
					}
				}
				
				socket.close();
				leitor.close();
				escritorDados.close();
				bufferedReader.close();
				bufferedWriter.close();
				
			}catch(Exception e){
				e.printStackTrace();
				
			}
		}
	}
}
