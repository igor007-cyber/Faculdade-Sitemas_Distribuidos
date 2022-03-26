import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import javax.imageio.ImageIO;


public class Server {

	public static void main(String[] args) throws IOException{

		JFrame jFrame = new JFrame ("Cliente"); // Vai cria uma janela
		jFrame.setSize(400,400); // vai colocar o taanho da janela
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // é usado para especificar uma das várias opções para o botão Fechar
		
		JLabel jLabelText = new JLabel("Esperando conexão do Cliente...");	// cria uma frase na imagem	
		
		jFrame.add(jLabelText, BorderLayout.SOUTH);
		
		jFrame.setVisible(true);
		
		ServerSocket serverSocket = new ServerSocket(1234);
		
		Socket socket = serverSocket.accept();
		
		InputStream inputStream = socket.getInputStream();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		
		BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);
		
		bufferedInputStream.close();		
		socket.close();
		
		JLabel jLabelPic = new JLabel(new ImageIcon(bufferedImage));
		jLabelText.setText("Receber imagem.");
		jFrame.add(jLabelPic, BorderLayout.CENTER);
		
		
		
		

	}

}
