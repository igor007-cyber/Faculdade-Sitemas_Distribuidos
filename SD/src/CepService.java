import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;



public class CepService {
	private int cep;
	
	public CepService(int cep) {
		this.cep = cep;
		
	}
	
	
	//método da mágica
	public Cep retornarCEP() throws IOException {
		StringBuilder resposta = new StringBuilder();
		
		try {
			URL url = new URL("https://viacep.com.br/ws/" + 
			this.cep + "/json/");
			
			HttpURLConnection conexao = (HttpURLConnection) 
			url.openConnection();
			
			//Requisicao
			conexao.setRequestMethod("GET");
			conexao.setRequestProperty("Content-type", 
					"application/json");
			conexao.setRequestProperty("Accept", "application/json");
			
			conexao.connect();
			
			Scanner scanner = new Scanner(url.openStream());// vai abrir os dados
			while(scanner.hasNext()) {
				resposta.append(scanner.next());
			}
			conexao.disconnect();
			scanner.close();
			
			Cep objetoJson = new Gson().fromJson(
					resposta.toString(), Cep.class);
			
		
			return objetoJson;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public int getCep() {
		return cep;
	}
	
	public void setCep(int cep) {
		this.cep = cep;
	}
	
}
