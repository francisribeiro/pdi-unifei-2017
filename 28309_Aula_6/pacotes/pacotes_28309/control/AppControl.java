package pacotes_28309.control;

import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import pacotes_28309.view.*;

public class AppControl implements ActionListener {

	private AppView appView;
	@SuppressWarnings("unused")
	private BufferedImage img, grayScale, sal, pimenta, salPimenta;
	private Ruidos ruidos;

	/**
	 * Construtor da classe. Exibe a aplicação.
	 */
	public AppControl() {
		appView = new AppView(this);
		ruidos = new Ruidos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Abrir Imagem
		if (e.getSource() == appView.btnAbrirImagem) {
			appView.plotaImagem(abrirImagem(), appView.um_top);

			if (img != null) {
				// Ruídos
				grayScale = ruidos.escalaDeCinza(img);
				sal = ruidos.Sal(grayScale);
				pimenta = ruidos.Pimenta(grayScale);
				salPimenta = ruidos.Pimenta(sal);
				
				//Plotando Ruídos
				appView.plotaImagem(pimenta, appView.dois_top);
				appView.plotaImagem(sal, appView.tres_top);
				appView.plotaImagem(salPimenta, appView.quatro_top);
			}
		}

		// Equalizar
		if (e.getSource() == appView.btnRuidos) {

		}

		// Fechar
		if (e.getSource() == appView.btnFechar) {
			System.exit(0);
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
}
