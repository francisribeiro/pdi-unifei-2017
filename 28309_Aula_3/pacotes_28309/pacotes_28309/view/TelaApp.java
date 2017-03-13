package pacotes_28309.view;

import java.awt.*;
import javax.swing.*;
import pacotes_28309.control.*;

public class TelaApp extends JFrame {
	private JButton btnAbrirImagem, btnZoomMais, btnZoomMenos, btnGirarEsquerda, btnGirarDireita;
	private JButton btnEspelhar, btnFrente, btnTras, btnCima, btnBaixo;

	/**
	 * Contrutor da classe. Define as configurações da janela principal da
	 * aplicacação.
	 * 
	 * @param appControl Controle principal da aplicação.
	 */
	public TelaApp(AppControl appControl) {

		// Setando as configurações da janela.
		this.setTitle("Transformações Geométricas");
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
	 * @param frame JFrame da classe {@link #Tela(AppControl)}.
	 * @param appControl Controle principal da aplicação.
	 */
	protected void toolBar(JFrame frame, AppControl appControl) {
		JToolBar toolBar;

		// Cria a barra de ferramentas.
		toolBar = new JToolBar();
		toolBar.setRollover(true);

		// Cria os botões.
		btnAbrirImagem = new JButton("Abrir Imagem");
		btnZoomMais = new JButton("Zoom +");
		btnZoomMenos = new JButton("Zoom -");
		btnGirarEsquerda = new JButton("<-- Girar");
		btnGirarDireita = new JButton("Girar -->");
		btnEspelhar = new JButton("Espelhar");
		btnFrente = new JButton("Frente");
		btnTras = new JButton("Trás");
		btnCima = new JButton("Cima");
		btnBaixo = new JButton("Baixo");

		// Adiciona os listeners.
		btnAbrirImagem.addActionListener(appControl);
		btnZoomMais.addActionListener(appControl);
		btnZoomMenos.addActionListener(appControl);
		btnGirarEsquerda.addActionListener(appControl);
		btnGirarDireita.addActionListener(appControl);
		btnEspelhar.addActionListener(appControl);
		btnFrente.addActionListener(appControl);
		btnTras.addActionListener(appControl);
		btnCima.addActionListener(appControl);
		btnBaixo.addActionListener(appControl);

		// Adicionado os botões na barra de ferramentas
		toolBar.add(btnAbrirImagem);
		toolBar.addSeparator();

		toolBar.add(btnZoomMais);
		toolBar.addSeparator();

		toolBar.add(btnZoomMenos);
		toolBar.addSeparator();

		toolBar.add(btnGirarEsquerda);
		toolBar.addSeparator();

		toolBar.add(btnGirarDireita);
		toolBar.addSeparator();

		toolBar.add(btnEspelhar);
		toolBar.addSeparator();

		toolBar.add(btnFrente);
		toolBar.addSeparator();

		toolBar.add(btnTras);
		toolBar.addSeparator();

		toolBar.add(btnCima);
		toolBar.addSeparator();

		toolBar.add(btnBaixo);

		// Adiciona toolbar ao JFrame.
		frame.add(toolBar, BorderLayout.NORTH);
	}
}
