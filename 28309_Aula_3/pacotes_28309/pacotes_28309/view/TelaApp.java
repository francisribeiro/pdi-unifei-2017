package pacotes_28309.view;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import pacotes_28309.control.*;

@SuppressWarnings("serial")
public class TelaApp extends JFrame {
	public JButton btnAbrirImagem, btnZoomMais, btnZoomMenos, btnGirarEsquerda, btnGirarDireita;
	public JButton btnEspelhar, btnFrente, btnTras, btnCima, btnBaixo;
	private JToolBar toolBar;
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
		canvas = new JPanel();
		canvas.setBackground(Color.DARK_GRAY);

		this.add(canvas, BorderLayout.CENTER);

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
		// Cria a barra de ferramentas.
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		// toolBar.setOrientation(JToolBar.VERTICAL);

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

		// Desabilita os botões
		btnZoomMais.setEnabled(false);
		btnZoomMenos.setEnabled(false);
		btnGirarEsquerda.setEnabled(false);
		btnGirarDireita.setEnabled(false);
		btnEspelhar.setEnabled(false);
		btnFrente.setEnabled(false);
		btnTras.setEnabled(false);
		btnCima.setEnabled(false);
		btnBaixo.setEnabled(false);

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

	/**
	 * Repinta o canvas para os eventos futuros.
	 * 
	 * @param g graficos
	 */
	private void limparTela(Graphics g) {
		g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	/**
	 * Método para sobreescrever o método de pintura da tela
	 * 
	 * @param g gráficos
	 * @param img imagem para ser plotada
	 */
	public void paintComponent(Graphics g, BufferedImage img) {
		int x = (canvas.getWidth() - img.getWidth()) / 2;
		int y = (canvas.getHeight() - img.getHeight()) / 2;

		canvas.paintComponents(g);
		limparTela(g);
		g.drawImage(img, x, y, this);
	}

	/**
	 * Plota a imagem na tela
	 * 
	 * @param img de entrada
	 */
	public void plotaImagem(BufferedImage img) {
		if (img != null) 
			paintComponent(canvas.getGraphics(), img);
	}

	/** 
	 * Habilita os botões de transformações
	 */
	public void habilitarBotoes() {
		btnZoomMais.setEnabled(true);
		btnZoomMenos.setEnabled(true);
		btnGirarEsquerda.setEnabled(true);
		btnGirarDireita.setEnabled(true);
		btnEspelhar.setEnabled(true);
		btnFrente.setEnabled(true);
		btnTras.setEnabled(true);
		btnCima.setEnabled(true);
		btnBaixo.setEnabled(true);
	}

}
