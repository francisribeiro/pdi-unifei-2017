package pacotes_28309.view;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import pacotes_28309.control.*;

@SuppressWarnings("serial")
public class AppView extends JFrame {
	public JButton btnAbrirImagem, btnRuidos, btnFechar;
	private JPanel container, um, dois, tres, quatro;
	public JPanel um_top, um_bottom, dois_top, dois_bottom, tres_top, tres_bottom, quatro_top, quatro_bottom;

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
		container = new JPanel(new GridLayout(1, 4));
		um = new JPanel(new GridLayout(2, 1));
		dois = new JPanel(new GridLayout(2, 1));
		tres = new JPanel(new GridLayout(2, 1));
		quatro = new JPanel(new GridLayout(2, 1));

		um_top = new JPanel();
		um_bottom = new JPanel();
		dois_top = new JPanel();
		dois_bottom = new JPanel();
		tres_top = new JPanel();
		tres_bottom = new JPanel();
		quatro_top = new JPanel();
		quatro_bottom = new JPanel();
		
		um.add(um_top);
		um.add(um_bottom);
		dois.add(dois_top);
		dois.add(dois_bottom);
		tres.add(tres_top);
		tres.add(tres_bottom);
		quatro.add(quatro_top);
		quatro.add(quatro_bottom);
		
		um_top.setBackground(Color.blue);
		um_bottom.setBackground(Color.cyan);
		dois_top.setBackground(Color.black);
		dois_bottom.setBackground(Color.yellow);
		tres_top.setBackground(Color.blue);
		tres_bottom.setBackground(Color.cyan);
		quatro_top.setBackground(Color.black);
		quatro_bottom.setBackground(Color.yellow);
		
		container.add(um);
		container.add(dois);
		container.add(tres);
		container.add(quatro);
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
		toolBar.setOrientation(JToolBar.HORIZONTAL);

		// Cria os botões.
		btnAbrirImagem = new JButton("Abrir Imagem");
		btnRuidos = new JButton("Ruídos");
		btnFechar = new JButton("Fechar");

		// Desabilita os botões
		btnRuidos.setEnabled(false);

		// Adiciona os listeners.
		btnAbrirImagem.addActionListener(appControl);
		btnRuidos.addActionListener(appControl);
		btnFechar.addActionListener(appControl);

		// Adicionado os botões na barra de ferramentas
		toolBar.add(btnAbrirImagem);
		toolBar.addSeparator();

		toolBar.add(btnRuidos);
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
		g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
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
		btnRuidos.setEnabled(true);
	}

}
