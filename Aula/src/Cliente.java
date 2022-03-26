import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) {
		Socket socket = null; //objeto que faz conexao 
		InputStreamReader leitor = null; //fluxo dados
		OutputStreamWriter escritorDados = null; //fluxo dados
		BufferedReader bufferedReader = null; //leitor
		BufferedWriter bufferedWriter = null; //escritor
		
		try {
			socket = new Socket("localhost",4867);//Abrindo conexão
			
			leitor = new InputStreamReader(socket.getInputStream());// lendo no trafego nas nuvens
			escritorDados = new OutputStreamWriter(socket.getOutputStream()); // escrevendo no trafego nas nuvens
			
			bufferedReader = new BufferedReader(leitor); // leitura no java
			bufferedWriter = new BufferedWriter(escritorDados);// escrita no java
			
			Scanner scanner = new Scanner(System.in);
			
			while(true) {
				String mensagem = scanner.nextLine();
				bufferedWriter.write(mensagem);
				bufferedWriter.newLine();//escrever uma linha pra mandar pro servidor
				bufferedWriter.flush();//confirmação
				
				System.out.println("Servidor:" + bufferedReader.readLine());
				
				if(mensagem.equalsIgnoreCase("tchau")) {
					break;
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			
		}finally {
			//fechar tudo que a gente ta usando
			try {
			socket.close();
			leitor.close();
			escritorDados.close();
			bufferedReader.close();
			bufferedWriter.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
