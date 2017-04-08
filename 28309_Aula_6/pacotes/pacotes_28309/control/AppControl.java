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
			appView.plotaImagem(abrirImagem(), appView.o);

			if (img != null) {
				appView.habilitarBotoes();
				appView.limparFiltros();
				
				// Ruídos
				grayScale = ruidos.escalaDeCinza(img);
				sal = ruidos.Sal(grayScale);
				pimenta = ruidos.Pimenta(grayScale);
				salPimenta = ruidos.Pimenta(sal);

				// Plotando Ruídos
				appView.plotaImagem(pimenta, appView.p);
				appView.plotaImagem(sal, appView.s);
				appView.plotaImagem(salPimenta, appView.sp);
			}
		}

		// Media
		if (e.getSource() == appView.btnMedia) {
			appView.setFilterTitle("Media");

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					appView.plotaImagem(grayScale, appView.o_f);
					appView.plotaImagem(grayScale, appView.p_f);
					appView.plotaImagem(grayScale, appView.s_f);
					appView.plotaImagem(grayScale, appView.sp_f);
				}
			});
		}

		// Mediana
		if (e.getSource() == appView.btnMediana) {
			appView.setFilterTitle("Mediana");

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					appView.plotaImagem(pimenta, appView.o_f);
					appView.plotaImagem(pimenta, appView.p_f);
					appView.plotaImagem(pimenta, appView.s_f);
					appView.plotaImagem(pimenta, appView.sp_f);
				}
			});
		}

		// PseudoMediana
		if (e.getSource() == appView.btnPseudoMediana) {
			appView.setFilterTitle("Pseudo Mediana");

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					appView.plotaImagem(sal, appView.o_f);
					appView.plotaImagem(sal, appView.p_f);
					appView.plotaImagem(sal, appView.s_f);
					appView.plotaImagem(sal, appView.sp_f);
				}
			});
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
