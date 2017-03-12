package pacotes_28309.view;

import java.awt.*;
import javax.swing.*;
import pacotes_28309.control.*;

@SuppressWarnings("serial")
public class TelaConvolucao extends JFrame {

	public JPanel pnlImgConvolucionada;

	/**
	 * Contrutor da classe. Define as configurações da janela modal da
	 * aplicacação.
	 * 
	 * @param convolucaoControl Controle principal da aplicação.
	 */
	public TelaConvolucao(ConvolucaoControl convolucaoControl) {

		// Setando as configurações da janela.
		this.setTitle("Resultado da Convolução");
		this.setPreferredSize(new Dimension(700, 600));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Panel para a imagem
		pnlImgConvolucionada = new JPanel(new BorderLayout());
		this.add(pnlImgConvolucionada);

		// Empacotando e exibindo a aplicação.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	/**
	 * Método que coloca o grid no painel.
	 * 
	 * @param container JPanel que receberá o grid
	 * @param panel que será adicionado
	 */
	public void addGrid(JPanel container, JPanel panel) {
		container.add(panel);
	}
	
}
