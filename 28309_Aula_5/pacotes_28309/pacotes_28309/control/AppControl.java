package pacotes_28309.control;

import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;

import pacotes_28309.view.*;

public class AppControl implements ActionListener, ChangeListener {

	private AppView appView;
	private BufferedImage img;
	private Thresholder threshold;
	private int limiar = 0;

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
				desenharGrafico();
			}
		}

		// Zoom +
		if (e.getSource() == appView.btnZoomMais) {
			appView.plotaImagem(img, appView.left);
			if (limiar > 0)
				appView.plotaImagem(threshold.threshold(img, limiar), appView.right);
		}

		// Zoom -
		if (e.getSource() == appView.btnZoomMenos) {
			appView.plotaImagem(img, appView.left);
			if (limiar > 0)
				appView.plotaImagem(threshold.threshold(img, limiar), appView.right);
		}

		// Girar esquerda
		if (e.getSource() == appView.btnGirarEsquerda) {
			appView.plotaImagem(img, appView.left);
			if (limiar > 0)
				appView.plotaImagem(threshold.threshold(img, limiar), appView.right);
		}

		// Girar direta
		if (e.getSource() == appView.btnGirarDireita) {
			appView.plotaImagem(img, appView.left);
			if (limiar > 0)
				appView.plotaImagem(threshold.threshold(img, limiar), appView.right);
		}

		// Abrir Imagem
		if (e.getSource() == appView.btnEspelhar) {
			appView.plotaImagem(img, appView.left);
			if (limiar > 0)
				appView.plotaImagem(threshold.threshold(img, limiar), appView.right);
		}

		// Mover frente
		if (e.getSource() == appView.btnFrente) {
			appView.plotaImagem(img, appView.left);
			if (limiar > 0)
				appView.plotaImagem(threshold.threshold(img, limiar), appView.right);
		}

		// Mover trás
		if (e.getSource() == appView.btnTras) {
			appView.plotaImagem(img, appView.left);
			if (limiar > 0)
				appView.plotaImagem(threshold.threshold(img, limiar), appView.right);
		}

		// Mover cima
		if (e.getSource() == appView.btnCima) {
			appView.plotaImagem(img, appView.left);
			if (limiar > 0)
				appView.plotaImagem(threshold.threshold(img, limiar), appView.right);
		}

		// Mover baixo
		if (e.getSource() == appView.btnBaixo) {
			appView.plotaImagem(img, appView.left);
			if (limiar > 0)
				appView.plotaImagem(threshold.threshold(img, limiar), appView.right);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == appView.sldrRegua) {
			this.limiar = appView.sldrRegua.getValue();
			appView.plotaImagem(threshold.threshold(img, limiar), appView.right);
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

	private void desenharGrafico() {
		threshold = new Thresholder(img);
		threshold.histograma(threshold.escalaDeCinza());
		appView.desenhaHistograma(threshold.getDadosHistograma(), threshold.getMaxY());
	}
}
