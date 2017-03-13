package pacotes_28309.control;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import pacotes_28309.view.*;

/**
 * Classe AppControl: Principal controler da aplicação.
 * 
 * @author Francis Ribeiro
 */
public class AppControl implements ActionListener {

	private TelaApp appView;
	private BufferedImage img;
	private Graphics draw;
	private ImagemControl imagemControl;

	/**
	 * Construtor da classe. Exibe a aplicação.
	 */
	public AppControl() {
		appView = new TelaApp(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Abrir Imagem
		if (e.getSource() == appView.btnAbrirImagem) {
			abrirImagem();
			if (img != null)
				appView.habilitarBotoes();
			
			plotarImagem();
		}

		// Zoom +
		if (e.getSource() == appView.btnZoomMais) {
			System.out.println("Zoom +");
		}

		// Zoom -
		if (e.getSource() == appView.btnZoomMenos) {
			System.out.println("Zoom -");
		}

		// Girar esquerda
		if (e.getSource() == appView.btnGirarEsquerda) {
			System.out.println("Girar esquerda");
		}

		// Girar direta
		if (e.getSource() == appView.btnGirarDireita) {
			System.out.println("Girar direita");
		}

		// Abrir Imagem
		if (e.getSource() == appView.btnEspelhar) {
			System.out.println("Espelhar");
		}

		// Mover frente
		if (e.getSource() == appView.btnFrente) {
			System.out.println("Frente");
		}

		// Mover trás
		if (e.getSource() == appView.btnTras) {
			System.out.println("Trás");
		}

		// Mover cima
		if (e.getSource() == appView.btnCima) {
			System.out.println("Cima");
		}

		// Mover baixo
		if (e.getSource() == appView.btnBaixo) {
			System.out.println("Baixo");
		}

	}

	/**
	 * Método para navegar pela estrutura de diretórios e carregar uma imagem.
	 * 
	 */
	private BufferedImage abrirImagem() {
		String nomeArqLido = null;

		// Localizando o arquivo
		JFileChooser arquivo = new JFileChooser();
		File diretorio = new File("..\\");
		arquivo.setCurrentDirectory(diretorio);
		arquivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int saida = arquivo.showOpenDialog(arquivo);

		// Fazendo a leitura do arquivo
		if (saida == JFileChooser.APPROVE_OPTION) {
			File nomeArq = arquivo.getSelectedFile();
			nomeArqLido = nomeArq.toString();
		} else if (saida == JFileChooser.CANCEL_OPTION)
			return null;

		// Aloca a Imagem carregada
		try {
			img = ImageIO.read(new File(nomeArqLido));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return img;
	}

	/**
	 * 
	 */
	private void plotarImagem() {
		draw = appView.startDrawing();
		appView.limparTela();
		imagemControl = new ImagemControl(img, draw);
		imagemControl.plotarImagem(); 
		//appView.paintComponent(draw,imagemControl.plotarImagem() );
	}

}
