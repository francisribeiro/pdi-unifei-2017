package pacotes_28309.control;

import java.awt.event.*;
import pacotes_28309.view.*;

/**
 * Classe AppControl: Principal controler da aplicação.
 * 
 * @author Francis Ribeiro
 */
public class AppControl implements ActionListener {
	
	private Tela appView;

	/**
	 * Construtor da classe. Exibe a aplicação.
	 */
	public AppControl() {
		appView = new Tela(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Carregar Imagem")) {
			System.out.println("Abrir tela para carregar a Imagem.");
		}
	}

}
