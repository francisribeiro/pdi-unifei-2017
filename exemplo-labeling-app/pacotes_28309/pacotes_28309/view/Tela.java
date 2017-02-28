package pacotes_28309.view;

import java.awt.*;
import java.awt.image.*;
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
		
		toolBar.add(btnCarregarImagem);
		toolBar.addSeparator();
		
		toolBar.add(btnRotularImagem	);
		toolBar.addSeparator();

		// Adiciona toolbar ao JFrame.
		frame.add(toolBar, BorderLayout.NORTH);
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
	 *            BufferedImage contendo a imagem a transformada.
	 */
	public void setImagemEsquerda(BufferedImage image) {
		this.lblImgEsquerda.setIcon(new ImageIcon(image));
	}
}
