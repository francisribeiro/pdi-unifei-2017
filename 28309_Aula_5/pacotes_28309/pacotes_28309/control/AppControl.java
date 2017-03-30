package pacotes_28309.control;

import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;

import pacotes_28309.view.*;

public class AppControl implements ActionListener {

	private AppView appView;
	private BufferedImage img;
	private Histograma threshold;

	/**
	 * Construtor da classe. Exibe a aplicação.
	 */
	public AppControl() {
		appView = new AppView(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Abrir Imagem
		if (e.getSource() == appView.btnAbrirImagem) {
			appView.plotaImagem(abrirImagem(), appView.left);
			if (img != null) {
				appView.habilitarBotoes();
				threshold = new Histograma(img);
				appView.desenhaHistograma(threshold.getDadosHistograma(), threshold.getMaxYH(), appView.histograma);
			}
		}

		// Equalizar
		if (e.getSource() == appView.btnEqualizar) {
			appView.plotaImagem(threshold.equalize(), appView.right);
			appView.desenhaHistograma(threshold.getDadosHistogramaEqualizado(), threshold.getMaxYHE(), appView.histogramaEqualizado);
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
