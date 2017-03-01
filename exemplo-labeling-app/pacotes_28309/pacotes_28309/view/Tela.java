package pacotes_28309.view;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import pacotes_28309.control.*;

/**
 * Classe Tela: Pricipal view da aplicação.
 * 
 * @author Francis Ribeiro
 */
public class Tela extends JFrame {
	private JButton btnCarregarImagem, btnRotularImagem;
	private JPanel container, panelOne, panelTwo;
	private JLabel lblImgDireita, lblImgEsquerda;

	/**
	 * Contrutor da classe. Define as configurações da janela principal da
	 * aplicacação.
	 * 
	 * @param appControl
	 *            Controle principal da aplicação.
	 */
	public Tela(AppControl appControl) {

		// Setando as configurações da janela.
		this.setTitle("Exemplo Labeling App");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

		// Adicionando a barra de ferramentas ao JFrame.
		toolBar(this, appControl);

		// Painéis da aplicação
		container = new JPanel();
		panelOne = new JPanel();
		panelTwo = new JPanel();

		// Label contendo as imagens
		lblImgDireita = new JLabel();
		lblImgEsquerda = new JLabel();

		// Setando as Imagens nos painéis
		panelOne.add(lblImgDireita);
		panelTwo.add(lblImgEsquerda);

		// Ajustando os painíes ao painel de container
		container.setLayout(new GridLayout(1, 2));
		container.add(panelOne);
		container.add(panelTwo);

		// Adicionando o container ao frame.
		this.add(container);

		// Empacotando e exibindo a aplicação.
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}

	/**
	 * 
	 * @param frame
	 *            JFrame da classe {@link #Tela(AppControl)}.
	 * @param appControl
	 *            Controle principal da aplicação.
	 */
	private void toolBar(JFrame frame, AppControl appControl) {
		JToolBar toolBar;

		// Cria a barra de ferramentas.
		toolBar = new JToolBar();
		toolBar.setRollover(true);

		// Cria os botões.
		btnCarregarImagem = new JButton("Abrir Imagem");
		btnRotularImagem = new JButton("Rotular Imagem");

		// Adiciona os listeners.
		btnCarregarImagem.addActionListener(appControl);
		btnRotularImagem.addActionListener(appControl);

		// Colocando os botões na barra de ferramentas.
		toolBar.add(btnCarregarImagem);
		toolBar.addSeparator();

		toolBar.add(btnRotularImagem);
		toolBar.addSeparator();

		// Adiciona toolbar ao JFrame.
		frame.add(toolBar, BorderLayout.NORTH);
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
	 * @return imagem BufferedImage contendo a imagem necessária.
	 */
	public BufferedImage carregarImagem() {
		BufferedImage imagem = null;
		String msg, path;

		try {
			path = caminhoImagem();// Localiza o caminho da imagem.
			imagem = ImageIO.read(new File(path));// Carrega a imagem.
		} catch (FileNotFoundException e) {
			msg = "Arquivo não encontrado.";
			JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			msg = "Erro no arquivo.";
			JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
		}

		return imagem;
	}

	/**
	 * Método que exibe uma imagem na metade direita da tela.
	 * 
	 * @param image
	 *            BufferedImage contendo a imagem a ser transformada.
	 */
	public void setImagemDireita(BufferedImage image) {
		this.lblImgDireita.setIcon(new ImageIcon(image));
	}

	/**
	 * Método que exibe uma imagem na metade esquerda da tela.
	 * 
	 * @param image
	 *            BufferedImage contendo a imagem transformada.
	 */
	public void setImagemEsquerda(BufferedImage image) {
		this.lblImgEsquerda.setIcon(new ImageIcon(image));
	}
}
