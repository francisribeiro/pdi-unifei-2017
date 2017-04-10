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
	private BufferedImage Nimg, Nsal, Npimenta, NsalPimenta;
	private boolean mediana = false, media = false, pseudo = false;
	private Ruidos ruidos;
	private Filtros filtro;

	/**
	 * Construtor da classe. Exibe a aplicação.
	 */
	public AppControl() {
		appView = new AppView(this);
		ruidos = new Ruidos();
		filtro = new Filtros();
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

			mediana = false;
			pseudo = false;
			
			if (!media) {
				Nimg = img;
				Nsal = sal;
				Npimenta = pimenta;
				NsalPimenta = salPimenta;
				media = true;
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					appView.plotaImagem(Nimg =filtro.media(Nimg, 3), appView.o_f);
					appView.plotaImagem(Npimenta = filtro.media(Npimenta, 3),appView.p_f);
					appView.plotaImagem(Nsal = filtro.media(Nsal, 3), appView.s_f);
					appView.plotaImagem(NsalPimenta = filtro.media(NsalPimenta, 3), appView.sp_f);
				}
			});
		}

		// Mediana
		if (e.getSource() == appView.btnMediana) {
			appView.setFilterTitle("Mediana");

			media = false;
			pseudo = false;
			
			if (!mediana) {
				Nimg = img;
				Nsal = sal;
				Npimenta = pimenta;
				NsalPimenta = salPimenta;
				mediana = true;
			}

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					appView.plotaImagem(Nimg = filtro.mediana(Nimg), appView.o_f);
					appView.plotaImagem(Npimenta = filtro.mediana(Npimenta), appView.p_f);
					appView.plotaImagem(Nsal = filtro.mediana(Nsal), appView.s_f);
					appView.plotaImagem(NsalPimenta = filtro.mediana(NsalPimenta), appView.sp_f);
				}
			});
			
		}

		// PseudoMediana
		if (e.getSource() == appView.btnPseudoMediana) {
			appView.setFilterTitle("Pseudo Mediana");
			
			mediana = false;
			media = false;
			
			if (!pseudo) {
				Nimg = img;
				Nsal = sal;
				Npimenta = pimenta;
				NsalPimenta = salPimenta;
				pseudo = true;
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					appView.plotaImagem(Nimg = filtro.pseudoMediana(Nimg), appView.o_f);
					appView.plotaImagem(Npimenta = filtro.pseudoMediana(Npimenta), appView.p_f);
					appView.plotaImagem(Nsal = filtro.pseudoMediana(Nsal), appView.s_f);
					appView.plotaImagem(NsalPimenta = filtro.pseudoMediana(NsalPimenta), appView.sp_f);
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
