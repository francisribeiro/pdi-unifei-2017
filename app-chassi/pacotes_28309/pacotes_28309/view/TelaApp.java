package pacotes_28309.view;

import java.awt.*;
import javax.swing.*;
import pacotes_28309.control.*;

/**
 * Classe Tela: Pricipal view da aplicação.
 * 
 * @author Francis Ribeiro
 */
public class TelaApp extends JFrame {
	public JButton btnCarregarImagem;

	/**
	 * Contrutor da classe. Define as configurações da janela principal da
	 * aplicacação.
	 * 
	 * @param appControl
	 *            Controle principal da aplicação.
	 */
	public TelaApp(AppControl appControl) {

		// Setando as configurações da janela.
		this.setTitle("App Chassi");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

		// Adicionando a barra de ferramentas ao JFrame.
		toolBar(this, appControl);

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
	protected void toolBar(JFrame frame, AppControl appControl) {
		JToolBar toolBar;

		// Cria a barra de ferramentas.
		toolBar = new JToolBar();
		toolBar.setRollover(true);

		// Cria os botões.
		btnCarregarImagem = new JButton("Botão");

		// Adiciona os listeners.
		btnCarregarImagem.addActionListener(appControl);

		toolBar.add(btnCarregarImagem);
		toolBar.addSeparator();

		// Adiciona toolbar ao JFrame.
		frame.add(toolBar, BorderLayout.NORTH);
	}
}
