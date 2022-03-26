import java.io.PrintStream;
import java.net.Socket;

public class Clinte {

	public static void main(String[] args) {
		
		try {
		//Declarar o socket cliente
		Socket cliente = new Socket("127.0.0.1", 7000);
		
		// Fluxo de Dados para o servidor
		PrintStream ps = new PrintStream(cliente.getOutputStream());
		
		ps.println("oi");
		
		cliente.close();
		
		}catch (Exception e) {
			System.out.println("Ocorreu um erro durante a conexao");
		}
	}

}
