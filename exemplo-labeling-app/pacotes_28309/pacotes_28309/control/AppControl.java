package pacotes_28309.control;

import java.awt.event.*;
import java.awt.image.*;
import pacotes_28309.view.*;

/**
 * Classe AppControl: Principal controler da aplicação.
 * 
 * @author Francis Ribeiro
 */
public class AppControl implements ActionListener {

	private Tela appView;
	private BufferedImage img;
	private RotuloControl rc;

	/**
	 * Construtor da classe: Exibe a aplicação.
	 */
	public AppControl() {
		appView = new Tela(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Abrir Imagem")) {
			img = appView.carregarImagem();
			appView.setImagemDireita(img);
		}

		if (e.getActionCommand().equals("Rotular Imagem")) {
			rc = new RotuloControl(img);
			rc.rotularImagem();
			appView.setImagemEsquerda(rc.imagemSaida());
		}
	}
}
