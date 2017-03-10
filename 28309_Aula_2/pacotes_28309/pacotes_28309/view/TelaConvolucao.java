package pacotes_28309.view;

import java.awt.*;
import javax.swing.*;

import pacotes_28309.control.AppControl;

public class TelaConvolucao extends JDialog {

	
	public TelaConvolucao(AppControl appControl) {

		// Setando as configurações da janela.
		this.setTitle("Convolução das Imagens");
		this.setPreferredSize(new Dimension(600, 500));
		this.setLayout(new BorderLayout());
		this.setModal(true);

		// Empacotando e exibindo a aplicação.
		this.pack();
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		this.setResizable(false);
	}

}
