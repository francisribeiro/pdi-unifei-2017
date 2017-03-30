package pacotes_28309.view;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import pacotes_28309.control.*;

@SuppressWarnings("serial")
public class AppView extends JFrame {
	public JButton btnAbrirImagem, btnEqualizar, btnFechar;
	private JPanel imgContainer, containerGeral;
	public JPanel left, right, histogramaContainer, histograma, histogramaEqualizado, left2, right2;

	/**
	 * Contrutor da classe. Define as configurações da janela principal da
	 * aplicacação.
	 * 
	 * @param appControl Controle principal da aplicação.
	 */
	public AppView(AppControl appControl) {

		// Setando as configurações da janela.
		this.setTitle("App Equalizar");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setUndecorated(true);

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
		right2 = new JPanel();
		histograma = new JPanel();
		histogramaEqualizado = new JPanel();

		// Cor dos painéis
		left.setBackground(Color.GRAY);
		right.setBackground(Color.BLACK);

		// propriedades do histograma
		histograma.setPreferredSize(new Dimension(300, 100));
		histogramaEqualizado.setPreferredSize(new Dimension(300, 100));
		
		left2.add(histograma);
		right2.add(histogramaEqualizado);

		// Slider ao painel
		histogramaContainer.add(left2);
		histogramaContainer.add(right2);

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
		toolBar.setOrientation(JToolBar.HORIZONTAL);

		// Cria os botões.
		btnAbrirImagem = new JButton("Abrir Imagem");
		btnEqualizar = new JButton("Equalizar");
		btnFechar = new JButton("Fechar");

		// Desabilita os botões
		btnEqualizar.setEnabled(false);
		
		// Adiciona os listeners.
		btnAbrirImagem.addActionListener(appControl);
		btnEqualizar.addActionListener(appControl);
		btnFechar.addActionListener(appControl);

		// Adicionado os botões na barra de ferramentas
		toolBar.add(btnAbrirImagem);
		toolBar.addSeparator();

		toolBar.add(btnEqualizar);
		toolBar.addSeparator();

		toolBar.add(btnFechar);
		toolBar.addSeparator();

		// Adiciona toolbar ao JFrame.
		add(toolBar, BorderLayout.NORTH);
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
		btnEqualizar.setEnabled(true);
	}

	public void desenhaHistograma(int[] dadosHistograma, int maxY, JPanel panel) {
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension dim = toolkit.getScreenSize();
		
		Graphics2D g2 = (Graphics2D) panel.getGraphics();
		g2.setColor(new Color(238, 238, 238));
		g2.fillRect(0, 0, panel.getWidth(), 100);

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
