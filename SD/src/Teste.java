import java.io.IOException;
import java.util.Scanner;


public class Teste {
	static Scanner ler = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("digite o cep: ");
		
		 Cep cep = new CepService(ler.nextInt()).retornarCEP();
		
		
			System.out.println(cep.getLocalidade());
			System.out.println(cep.getDdd());
	}
}