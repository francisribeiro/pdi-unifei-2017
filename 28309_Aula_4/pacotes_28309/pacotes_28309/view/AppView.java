package pacotes_28309.view;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import pacotes_28309.control.*;

@SuppressWarnings("serial")
public class AppView extends JFrame {
	public JButton btnAbrirImagem, btnZoomMais, btnZoomMenos, btnGirarEsquerda, btnGirarDireita;
	public JButton btnEspelhar, btnFrente, btnTras, btnCima, btnBaixo;
	private JPanel imgContainer, containerGeral;
	public JPanel left, right, histogramaContainer, histograma, left2, right2;
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
		containerGeral = new JPanel(new BorderLayout());
		imgContainer = new JPanel(new GridLayout(1, 2));
		left = new JPanel();
		right = new JPanel(new BorderLayout());

		histogramaContainer = new JPanel(new GridLayout(1, 2));
		left2 = new JPanel();
		right2 = new JPanel(new BorderLayout());
		histograma = new JPanel();

		// Cor dos painéis
		left.setBackground(Color.GRAY);
		right.setBackground(Color.black);

		// propriedades do histograma
		histograma.setPreferredSize(new Dimension(256*2, 100));
		sldrRegua.setPreferredSize(new Dimension(256*2, 50));

		right2.add(histograma, BorderLayout.NORTH);
		right2.add(sldrRegua, BorderLayout.SOUTH);

		JPanel aux = new JPanel(new GridBagLayout());
		
		addComp(aux, right2, 0, 0, 1, 1, GridBagConstraints.WEST);

		// Slider ao painel
		histogramaContainer.add(left2);
		histogramaContainer.add(aux);

		// Adicionando paineis ao container
		imgContainer.add(left);
		imgContainer.add(right);

		// Adicionando ao frame
		containerGeral.add(imgContainer, BorderLayout.CENTER);
		containerGeral.add(histogramaContainer, BorderLayout.PAGE_END);
		add(containerGeral);

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

		// Cria Slider.
		sldrRegua = new JSlider(0, 255, 0);
		sldrRegua.setMajorTickSpacing(30);
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
		add(toolBar, BorderLayout.WEST);
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
			g.setColor(Color.black);
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

	public void desenhaHistograma(int[] dadosHistograma, int maxY) {
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension dim = toolkit.getScreenSize();
		
		Graphics2D g2 = (Graphics2D) histograma.getGraphics();
		g2.setColor(new Color(238, 238, 238));
		g2.fillRect(0, 0, histograma.getWidth(), 100);

		g2.setStroke(new BasicStroke(2));

		int xIntervalo = 2;

		g2.setColor(Color.black);

		// draw the graph for the spesific colour.
		for (int j = 0; j < 256 - 1; j++) {
			// int valor = (int) (((double) dadosHistograma[j] / (double) maxY)
			// * 100);
			int valor2 = (int) (((double) dadosHistograma[j + 1] / (double) maxY) * 100);

			// g2.drawLine(j * xIntervalo, 100 - valor, (j + 1) * xIntervalo,
			// 100 - valor2);

			g2.setColor(Color.blue);

			g2.drawLine(j * xIntervalo, 100, j * xIntervalo, 100 - valor2);

		}
	}
	
	/**
	 * Método auxiliar para setar as regras para um componente destinado ao
	 * GridBagLayout e o adicionar.
	 * 
	 * @param painel painel de destino
	 * @param comp componente de destino
	 * @param xPos posição em x
	 * @param yPos posição em y
	 * @param compWidth largura do componente
	 * @param compHeight altura do componente
	 * @param place localização
	 * @param stretch preenchimento
	 */
	private void addComp(JPanel painel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place) {

		GridBagConstraints gridConstraints = new GridBagConstraints();

		gridConstraints.gridx = xPos;
		gridConstraints.gridy = yPos;
		gridConstraints.gridwidth = compWidth;
		gridConstraints.gridheight = compHeight;
		gridConstraints.weightx = 0;
		gridConstraints.weighty = 0;
		gridConstraints.insets = new Insets(5, 5, 5, 5);
		gridConstraints.anchor = place;

		painel.add(comp, gridConstraints);
	}

}
