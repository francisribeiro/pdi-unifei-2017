package pacotes_28309.control;

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

	private Tela appView;
	private String path;
	private BufferedImage img;

	/**
	 * Construtor da classe. Exibe a aplicação.
	 */
	public AppControl() {
		appView = new Tela(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Abrir Imagem")) {
			path = caminhoImagem();
			img = carregarImagem(path);
			appView.setImagemDireita(img);
		}
		
		if (e.getActionCommand().equals("Rotular Imagem")) {

		}
	}

	/**
	 * Método para navegar pela estrutura de diretórios e carregar uma imagem.
	 * 
	 * @return nomeArqLido Nome do arquivo lido em uma String
	 */
	private String caminhoImagem() {
		JFileChooser arquivo;
		File diretorio, nomeArq = null;
		String nomeArqLido = null;
		int saida;

		// Localizando o arquivo
		arquivo = new JFileChooser();
		diretorio = new File("..\\");
		arquivo.setCurrentDirectory(diretorio);
		arquivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		saida = arquivo.showOpenDialog(arquivo);

		if (saida == JFileChooser.APPROVE_OPTION) {
			// Fazendo a leitura do arquivo
			nomeArq = arquivo.getSelectedFile();
			nomeArqLido = nomeArq.toString();
		}

		return nomeArqLido;
	}

	/**
	 * Método para carregar na mémória uma imagem.
	 * 
	 * @param path
	 *            Caminho para a Imagem.
	 * @return imagem BufferedImage contendo a imagem necessária.
	 */
	private BufferedImage carregarImagem(String path) {
		BufferedImage imagem = null;
		String msg;

		try {
			imagem = ImageIO.read(new File(path));
		} catch (FileNotFoundException e) {
			msg = "Arquivo não encontrado.";
			JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			msg = "Erro no arquivo.";
			JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
		}

		return imagem;
	}

}
