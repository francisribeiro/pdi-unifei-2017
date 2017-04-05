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

	/**
	 * Monta o layout de janelas
	 */
	private void montaLayout() {

		// Criando os painéis
		containerGeral = new JPanel(new BorderLayout());
		imgContainer = new JPanel(new GridLayout(1, 2));
		left = new JPanel();
		right = new JPanel();

		histogramaContainer = new JPanel(new GridLayout(1, 2));
		left2 = new JPanel();
		right2 = new JPanel();
		histograma = new JPanel();
		histogramaEqualizado = new JPanel();

		// Cor dos painéis
		left.setBackground(Color.GRAY);
		right.setBackground(Color.LIGHT_GRAY);
		left2.setBackground(Color.GRAY);
		right2.setBackground(Color.LIGHT_GRAY);

		// propriedades do histograma
		histograma.setPreferredSize(new Dimension(450, 150));
		histogramaEqualizado.setPreferredSize(new Dimension(450, 150));
		histograma.setBackground(Color.GRAY);
		histogramaEqualizado.setBackground(Color.LIGHT_GRAY);

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
			g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
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

	/**
	 * Habilita botão de equalizar a imagem, logo após uma imagem ser aberta.
	 */
	public void habilitarBotoes() {
		btnEqualizar.setEnabled(true);
	}

	/**
	 * Desenha um histograma com base nos parâmetros.
	 * 
	 * @param dadosHistograma array de dados
	 * @param maxY valor máximo do array
	 * @param panel painel em que será incluído o histograma
	 */
	public void desenhaHistograma(int[] dadosHistograma, int maxY, JPanel panel) {
		Graphics2D g2 = (Graphics2D) panel.getGraphics();
		double xIntervalo = 1.7;

		if (panel == histograma)
			g2.setColor(Color.GRAY);
		else
			g2.setColor(Color.LIGHT_GRAY);
		
		g2.fillRect(0, 0, panel.getWidth(), 150);
		g2.setStroke(new BasicStroke(2));
		
		if (panel == histograma)
			g2.setColor(Color.BLACK);
		else
			g2.setColor(Color.BLACK);

		// Desenha o gráfico para a cor específica
		for (int j = 0; j < 256 - 1; j++) {
			int valor = (int) (((double) dadosHistograma[j + 1] / (double) maxY) * 150);
			g2.drawLine((int) (j * xIntervalo), 150, (int) (j * xIntervalo), 150 - valor);

		}
	}
}
