package pacotes_28309.view;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import pacotes_28309.control.*;

@SuppressWarnings("serial")
public class AppView extends JFrame {
	public JButton btnAbrirImagem, btnMedia, btnMediana, btnPseudoMediana, btnFechar;
	private JPanel container, um, dois, tres, quatro;
	private JPanel um_top, um_bottom, dois_top, dois_bottom, tres_top, tres_bottom, quatro_top, quatro_bottom;
	public JLabel original, pimenta, sal, salPimenta, ori_fil, pim_fil, sal_fil, salP_fil;
	public JPanel o, p, s, sp, o_f, p_f, s_f, sp_f;

	/**
	 * Contrutor da classe. Define as configurações da janela principal da
	 * aplicacação.
	 * 
	 * @param appControl Controle principal da aplicação.
	 */
	public AppView(AppControl appControl) {

		// Setando as configurações da janela.
		this.setTitle("App Filtros");
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

		um_top = new JPanel(new BorderLayout());
		dois_top = new JPanel(new BorderLayout());
		tres_top = new JPanel(new BorderLayout());
		quatro_top = new JPanel(new BorderLayout());

		o = new JPanel();
		p = new JPanel();
		s = new JPanel();
		sp = new JPanel();

		original = new JLabel("Original", SwingConstants.CENTER);
		pimenta = new JLabel("Pimenta", SwingConstants.CENTER);
		sal = new JLabel("Sal", SwingConstants.CENTER);
		salPimenta = new JLabel("Pimenta e Sal", SwingConstants.CENTER);

		original.setFont(new Font("Arial", Font.PLAIN, 16));
		pimenta.setFont(new Font("Arial", Font.PLAIN, 16));
		sal.setFont(new Font("Arial", Font.PLAIN, 16));
		salPimenta.setFont(new Font("Arial", Font.PLAIN, 16));

		original.setForeground(new Color(238, 238, 238));
		pimenta.setForeground(new Color(238, 238, 238));
		sal.setForeground(new Color(238, 238, 238));
		salPimenta.setForeground(new Color(238, 238, 238));
		
		um_top.add(original, BorderLayout.PAGE_START);
		um_top.add(o, BorderLayout.CENTER);

		dois_top.add(pimenta, BorderLayout.PAGE_START);
		dois_top.add(p, BorderLayout.CENTER);

		tres_top.add(sal, BorderLayout.PAGE_START);
		tres_top.add(s, BorderLayout.CENTER);

		quatro_top.add(salPimenta, BorderLayout.PAGE_START);
		quatro_top.add(sp, BorderLayout.CENTER);

		//////////////////////////////////////

		um_bottom = new JPanel(new BorderLayout());
		dois_bottom = new JPanel(new BorderLayout());
		tres_bottom = new JPanel(new BorderLayout());
		quatro_bottom = new JPanel(new BorderLayout());

		o_f = new JPanel();
		p_f = new JPanel();
		s_f = new JPanel();
		sp_f = new JPanel();

		ori_fil = new JLabel("", SwingConstants.CENTER);
		pim_fil = new JLabel("", SwingConstants.CENTER);
		sal_fil = new JLabel("", SwingConstants.CENTER);
		salP_fil = new JLabel("", SwingConstants.CENTER);

		ori_fil.setFont(new Font("Arial", Font.PLAIN, 16));
		pim_fil.setFont(new Font("Arial", Font.PLAIN, 16));
		sal_fil.setFont(new Font("Arial", Font.PLAIN, 16));
		salP_fil.setFont(new Font("Arial", Font.PLAIN, 16));

		um_bottom.add(ori_fil, BorderLayout.PAGE_START);
		um_bottom.add(o_f, BorderLayout.CENTER);

		dois_bottom.add(pim_fil, BorderLayout.PAGE_START);
		dois_bottom.add(p_f, BorderLayout.CENTER);

		tres_bottom.add(sal_fil, BorderLayout.PAGE_START);
		tres_bottom.add(s_f, BorderLayout.CENTER);

		quatro_bottom.add(salP_fil, BorderLayout.PAGE_START);
		quatro_bottom.add(sp_f, BorderLayout.CENTER);

		um.add(um_top);
		um.add(um_bottom);
		dois.add(dois_top);
		dois.add(dois_bottom);
		tres.add(tres_top);
		tres.add(tres_bottom);
		quatro.add(quatro_top);
		quatro.add(quatro_bottom);

		container.add(um);
		container.add(dois);
		container.add(tres);
		container.add(quatro);

		add(container, BorderLayout.CENTER);
	}

	private void setNoiseTitleColor() {
		original.setForeground(Color.BLACK);
		pimenta.setForeground(Color.BLACK);
		sal.setForeground(Color.BLACK);
		salPimenta.setForeground(Color.BLACK);
	}

	public void setFilterTitle(String txt) {
		ori_fil.setText("Original c/ " + txt);
		pim_fil.setText("Pimenta c/ " + txt);
		sal_fil.setText("Sal c/ " + txt);
		salP_fil.setText("Pimenta e Sal c/ " + txt);
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
		btnMedia = new JButton("Media");
		btnMediana = new JButton("Mediana");
		btnPseudoMediana = new JButton("Pseudo Mediana");
		btnFechar = new JButton("Fechar");

		// Desabilita os botões
		btnMedia.setEnabled(false);
		btnMediana.setEnabled(false);
		btnPseudoMediana.setEnabled(false);

		// Adiciona os listeners.
		btnAbrirImagem.addActionListener(appControl);
		btnMedia.addActionListener(appControl);
		btnMediana.addActionListener(appControl);
		btnPseudoMediana.addActionListener(appControl);
		btnFechar.addActionListener(appControl);

		// Adicionado os botões na barra de ferramentas
		toolBar.add(btnAbrirImagem);
		toolBar.addSeparator();

		toolBar.add(btnMedia);
		toolBar.addSeparator();

		toolBar.add(btnMediana);
		toolBar.addSeparator();

		toolBar.add(btnPseudoMediana);
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
		g.setColor(new Color(238, 238, 238));
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

		// panel.paintComponents(g);
		limparTela(g, panel);
		g.drawImage(img, x, y, this);
	}

	/**
	 * Plota a imagem na tela
	 * 
	 * @param img de entrada
	 */
	public void plotaImagem(BufferedImage img, JPanel panel) {
		if (img != null) {
			paintComponent(panel.getGraphics(), img, panel);
			setNoiseTitleColor();
		}
	}

	/**
	 * Habilita botão de equalizar a imagem, logo após uma imagem ser aberta.
	 */
	public void habilitarBotoes() {
		btnMedia.setEnabled(true);
		btnMediana.setEnabled(true);
		btnPseudoMediana.setEnabled(true);
	}

	public void limparFiltros() {
		um_bottom.repaint();
		dois_bottom.repaint();
		tres_bottom.repaint();
		quatro_bottom.repaint();
		ori_fil.setText("");
		pim_fil.setText("");
		sal_fil.setText("");
		salP_fil.setText("");
	}
}
