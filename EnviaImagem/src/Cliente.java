import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Cliente {

	public static void main(String[] args) throws IOException{
		
		Socket socket  =  new Socket("localhost",1234); // Colocar a conexão do servidor
		System.out.println("Servidor Conectado"); // Confirmar a conexão
		
		JFrame jFrame = new JFrame ("Cliente"); // Vai cria uma janela
		jFrame.setSize(400,400); // vai colocar o taanho da janela
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // é usado para especificar uma das várias opções para o botão Fechar
		
		ImageIcon imageIcon = new ImageIcon("/home/rigor/Pictures/Wallpapers/cachorro-removebg-preview.png");
		// Uma implementação da interface Icon que pinta ícones a partir de imagens
		
		JLabel jLabelPic = new JLabel(imageIcon);	// cria uma frase na imagem	
		JButton jButton = new JButton("Enviar imagem para o Servidor."); //coloca um texto no botão
		
		jFrame.add(jLabelPic, BorderLayout.CENTER);
		jFrame.add(jButton, BorderLayout.SOUTH);
		
		jFrame.setVisible(true);
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					OutputStream outputStream = socket.getOutputStream();
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
					
					Image image = imageIcon.getImage();
					
					BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
					
					Graphics graphics = bufferedImage.createGraphics();
					graphics.drawImage(image, 0, 0, null);
					graphics.dispose();
					
					ImageIO.write(bufferedImage, "png", bufferedOutputStream);
					
					bufferedOutputStream.close();
					socket.close();
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		

	}

}
