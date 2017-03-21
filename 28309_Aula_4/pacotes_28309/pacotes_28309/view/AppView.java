package pacotes_28309.view;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import pacotes_28309.control.*;

@SuppressWarnings("serial")
public class AppView extends JFrame {
	public JButton btnAbrirImagem, btnZoomMais, btnZoomMenos, btnGirarEsquerda, btnGirarDireita;
	public JButton btnEspelhar, btnFrente, btnTras, btnCima, btnBaixo;
	private JPanel container;
	public JPanel left, right, histogramaContainer, histograma;
	public JSlider sldrRegua;

	/**
	 * Contrutor da classe. Define as configurações da janela principal da
	 * aplicacação.
	 * 
	 * @param appControl Controle principal da aplicação.
	 */
	public AppView(AppControl appControl) {

		// Setando as configurações da janela.
		this.setTitle("App Histograma");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());

		// Adicionando a barra de ferramentas ao JFrame.
		toolBar(this, appControl);
		montaLayout();

		// Empacotando e exibindo a aplicação.
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}

	private void montaLayout() {

		// Criando os painéis
		container = new JPanel(new GridLayout(1, 2));
		left = new JPanel();
		right = new JPanel(new BorderLayout());
		histogramaContainer = new JPanel(new BorderLayout());
		histograma = new JPanel();

		// Cor dos painéis
		left.setBackground(Color.GRAY);
		right.setBackground(Color.DARK_GRAY);

		// propriedades do histograma
		histograma.setPreferredSize(new Dimension(histogramaContainer.getWidth(), 100));
		histograma.setBackground(Color.white);

		// Slider ao painel
		histogramaContainer.add(histograma, BorderLayout.NORTH);
		histogramaContainer.add(sldrRegua, BorderLayout.SOUTH);

		// Adicionando paineis ao container
		right.add(histogramaContainer, BorderLayout.PAGE_END);
		container.add(left);
		container.add(right);

		// Adicionando ao frame
		add(container);
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
		toolBar.setFloatable(false);

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

		// Cria Slider.
		sldrRegua = new JSlider(0, 255, 0);
		sldrRegua.setMajorTickSpacing(15);
		sldrRegua.setMinorTickSpacing(5);
		sldrRegua.setPaintTicks(true);
		sldrRegua.setPaintLabels(true);

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
		sldrRegua.setEnabled(false);

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
		sldrRegua.addChangeListener(appControl);

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
		toolBar.addSeparator();

		// Adiciona toolbar ao JFrame.
		frame.add(toolBar, BorderLayout.NORTH);
	}

	/**
	 * Repinta o canvas para os eventos futuros.
	 * 
	 * @param g graficos
	 */
	private void limparTela(Graphics g, JPanel panel) {
		if (panel == left) {
			g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
		} else {
			g.clearRect(0, 0, panel.getWidth(), panel.getHeight() - histogramaContainer.getHeight());
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, panel.getWidth(), panel.getHeight() - histogramaContainer.getHeight());
		}

	}

	/**
	 * Método para sobreescrever o método de pintura da tela
	 * 
	 * @param g gráficos
	 * @param img imagem para ser plotada
	 */
	public void paintComponent(Graphics g, BufferedImage img, JPanel panel) {
		int x = (panel.getWidth() - img.getWidth()) / 2;
		int y = (panel.getHeight() - img.getHeight()) / 2;

		panel.paintComponents(g);
		limparTela(g, panel);
		g.drawImage(img, x, y, this);
	}

	/**
	 * Plota a imagem na tela
	 * 
	 * @param img de entrada
	 */
	public void plotaImagem(BufferedImage img, JPanel panel) {
		if (img != null)
			paintComponent(panel.getGraphics(), img, panel);
	}

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
		sldrRegua.setEnabled(true);
	}

	public void desenhaHistograma(int RED, int GREEN, int BLUE, int[][] colourBins, int maxY) {
		Graphics2D g2 = (Graphics2D) histograma.getGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, histograma.getWidth(), histograma.getHeight());

		g2.setStroke(new BasicStroke(2));

		int xIntervalo = (int) ((double) histograma.getWidth() / ((double) 256 + 1));

		g2.setColor(Color.black);

		for (int i = 0; i < 3; i++) {

			// Set the graph
			if (i == RED) {
				g2.setColor(Color.red);
			} else if (i == GREEN) {
				g2.setColor(Color.GREEN);
			} else if (i == BLUE) {
				g2.setColor(Color.blue);
			}

			// draw the graph for the spesific colour.
			for (int j = 0; j < 256 - 1; j++) {
				int value = (int) (((double) colourBins[i][j] / (double) maxY) * histograma.getHeight());
				int value2 = (int) (((double) colourBins[i][j + 1] / (double) maxY) * histograma.getHeight());
				
				g2.drawLine(j * xIntervalo, histograma.getHeight() - value, (j + 1) * xIntervalo,
						histograma.getHeight() - value2);

			}
		}

	}
}
