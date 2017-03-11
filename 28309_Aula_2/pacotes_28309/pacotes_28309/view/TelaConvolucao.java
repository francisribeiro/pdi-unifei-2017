package pacotes_28309.view;

import java.awt.*;
import javax.swing.*;
import pacotes_28309.control.*;

public class TelaConvolucao extends JDialog {

	private JButton btnConvoluir;
	private JPanel pnlImgConvolucionada;
	
	/**
	 * Contrutor da classe. Define as configurações da janela modal da aplicacação.
	 * 
	 * @param convolucaoControl Controle principal da aplicação.
	 */
	public TelaConvolucao(ConvolucaoControl convolucaoControl) {

		// Setando as configurações da janela.
		this.setTitle("Convolução das Imagens");
		this.setPreferredSize(new Dimension(700, 600));
		this.setLayout(new BorderLayout());
		this.setModal(true);
		
		//Panel para a imagem
		pnlImgConvolucionada = new JPanel();
		pnlImgConvolucionada.setBackground(new Color(33,33,33));
		this.add(pnlImgConvolucionada);

		// Adicionando a barra de ferramentas.
		toolBar(convolucaoControl);

		// Empacotando e exibindo a aplicação.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	/**
	 * Cria uma toolbar para os botões.
	 * 
	 * @param appControl Controle principal da aplicação.
	 */
	protected void toolBar(ConvolucaoControl convolucaoControl) {
		JToolBar toolBar;

		// Cria a barra de ferramentas.
		toolBar = new JToolBar();
		toolBar.setRollover(true);

		// Cria os botões.
		btnConvoluir = new JButton("Convoluir");

		// Adiciona os listeners.
		btnConvoluir.addActionListener(convolucaoControl);

		toolBar.add(btnConvoluir);
		toolBar.addSeparator();

		// Adiciona toolbar ao JFrame.
		add(toolBar, BorderLayout.NORTH);
	}

}
