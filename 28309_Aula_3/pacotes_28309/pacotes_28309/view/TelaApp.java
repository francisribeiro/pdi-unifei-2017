package pacotes_28309.view;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import pacotes_28309.control.*;

public class TelaApp extends JFrame {
	public JButton btnAbrirImagem, btnZoomMais, btnZoomMenos, btnGirarEsquerda, btnGirarDireita;
	public JButton btnEspelhar, btnFrente, btnTras, btnCima, btnBaixo;
	private JPanel canvas;

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

		// Criando e adicionando painel ao Frame
		canvas = new JPanel(new BorderLayout());
		this.add(canvas);

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
		toolBar.setOrientation(JToolBar.VERTICAL);

		// Cria os botões.
		btnAbrirImagem = new JButton();
		btnZoomMais = new JButton();
		btnZoomMenos = new JButton();
		btnGirarEsquerda = new JButton();
		btnGirarDireita = new JButton();
		btnEspelhar = new JButton();
		btnFrente = new JButton();
		btnTras = new JButton();
		btnCima = new JButton();
		btnBaixo = new JButton();

		// Adiciona os icones
		btnAbrirImagem.setIcon(new ImageIcon(this.getClass().getResource("abrir.png")));
		btnZoomMais.setIcon(new ImageIcon(this.getClass().getResource("zoom_in.png")));
		btnZoomMenos.setIcon(new ImageIcon(this.getClass().getResource("zoom_out.png")));
		btnGirarEsquerda.setIcon(new ImageIcon(this.getClass().getResource("esquerda.png")));
		btnGirarDireita.setIcon(new ImageIcon(this.getClass().getResource("direita.png")));
		btnEspelhar.setIcon(new ImageIcon(this.getClass().getResource("espelhar.png")));
		btnFrente.setIcon(new ImageIcon(this.getClass().getResource("frente.png")));
		btnTras.setIcon(new ImageIcon(this.getClass().getResource("tras.png")));
		btnCima.setIcon(new ImageIcon(this.getClass().getResource("cima.png")));
		btnBaixo.setIcon(new ImageIcon(this.getClass().getResource("baixo.png")));

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
		frame.add(toolBar, BorderLayout.WEST);
	}

	/**
	 * Coloca uma Imagem dentro do painel
	 * 
	 * @param img que será alocada
	 */
	public void displayImage(BufferedImage img) {
		if (img != null) {
			canvas.removeAll(); // Limpa o painel
			ImageIcon image = new ImageIcon(img);
			JLabel label = new JLabel("", image, JLabel.CENTER);
			canvas.add(label, BorderLayout.CENTER);
			canvas.revalidate(); // Reativa o painel
		}
	}
}
