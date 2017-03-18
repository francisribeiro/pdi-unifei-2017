package pacotes_28309.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import pacotes_28309.control.*;

@SuppressWarnings("serial")
public class AppView extends JFrame {
	public JButton btnAbrirImagem, btnSalvarImagem, btnZoomMais, btnZoomMenos;
	public JButton btnGirarEsquerda, btnGirarDireita, btnEspelhar, btnFrente;
	public JButton btnTras, btnCima, btnBaixo, btnCor;
	private JPanel container, toolBarTransformacoes, tooBarImagem, left, right;
	public JPanel leftImg, rightImg;
	public JSlider sldrLargura, sldrAltura;
	private Border borda;
	private TitledBorder one, two;
	private JLabel linhas, colunas;
	public JLabel cor;

	/**
	 * Contrutor da classe. Define as configurações da janela principal da
	 * aplicacação.
	 * 
	 * @param appControl Controle principal da aplicação.
	 */
	public AppView(AppControl appControl) {

		// Setando as configurações da janela.
		this.setTitle("App Transformacoes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());

		// Montando o layout
		imagemLayout();

		// Adicionando as barras de ferramentas ao JFrame.
		tooBarImagem(appControl);
		toolBarTransformacoes(appControl);

		// Empacotando e exibindo a aplicação.
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}

	private void imagemLayout() {

		// Criando os painéis e adicionado os gerenciadores de layout
		container = new JPanel(new GridLayout(1, 2, 10, 10));
		left = new JPanel(new BorderLayout());
		right = new JPanel(new BorderLayout());
		leftImg = new JPanel(new BorderLayout());
		rightImg = new JPanel(new BorderLayout());

		// Adicionando as Bordas dos painéis e suas propriedades
		borda = BorderFactory.createLineBorder(Color.gray, 0);
		one = BorderFactory.createTitledBorder(borda, "Imagem Original");
		two = BorderFactory.createTitledBorder(borda, "Imagem Transformada");
		one.setTitleJustification(TitledBorder.CENTER);
		two.setTitleJustification(TitledBorder.CENTER);
		one.setTitleFont(new Font("Arial", Font.PLAIN, 17));
		two.setTitleFont(new Font("Arial", Font.PLAIN, 17));
		left.setBorder(one);
		right.setBorder(two);

		// Adicionando paineis ao container
		left.add(leftImg, BorderLayout.CENTER);
		right.add(rightImg, BorderLayout.CENTER);
		container.add(left);
		container.add(right);

		// Adicionando ao frame
		add(container);
	}

	/**
	 * Barra de ferramentas para as transformações geométricas
	 * 
	 * @param appControl controle principal
	 */
	private void toolBarTransformacoes(AppControl appControl) {

		// Painel da barra de ferramentas
		toolBarTransformacoes = new JPanel(new GridBagLayout());

		// Cria os botões.
		btnZoomMais = new JButton();
		btnZoomMenos = new JButton();
		btnGirarEsquerda = new JButton();
		btnGirarDireita = new JButton();
		btnEspelhar = new JButton();
		btnFrente = new JButton();
		btnTras = new JButton();
		btnCima = new JButton();
		btnBaixo = new JButton();

		btnZoomMais.setPreferredSize(new Dimension(60, 60));
		btnZoomMenos.setPreferredSize(new Dimension(60, 60));
		btnGirarEsquerda.setPreferredSize(new Dimension(60, 60));
		btnGirarDireita.setPreferredSize(new Dimension(60, 60));
		btnEspelhar.setPreferredSize(new Dimension(60, 60));
		btnFrente.setPreferredSize(new Dimension(60, 60));
		btnTras.setPreferredSize(new Dimension(60, 60));
		btnCima.setPreferredSize(new Dimension(60, 60));
		btnBaixo.setPreferredSize(new Dimension(60, 60));

		// Adiciona os icones
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
		addComp(toolBarTransformacoes, btnZoomMais, 0, 0, 1, 1, GridBagConstraints.WEST);
		addComp(toolBarTransformacoes, btnZoomMenos, 0, 1, 1, 1, GridBagConstraints.WEST);
		addComp(toolBarTransformacoes, btnGirarDireita, 0, 2, 1, 1, GridBagConstraints.WEST);
		addComp(toolBarTransformacoes, btnGirarEsquerda, 0, 3, 1, 1, GridBagConstraints.WEST);
		addComp(toolBarTransformacoes, btnEspelhar, 0, 4, 1, 1, GridBagConstraints.WEST);
		addComp(toolBarTransformacoes, btnFrente, 0, 5, 1, 1, GridBagConstraints.WEST);
		addComp(toolBarTransformacoes, btnTras, 0, 6, 1, 1, GridBagConstraints.WEST);
		addComp(toolBarTransformacoes, btnCima, 0, 7, 1, 1, GridBagConstraints.WEST);
		addComp(toolBarTransformacoes, btnBaixo, 0, 8, 1, 1, GridBagConstraints.WEST);

		// Adiciona toolbar ao JFrame.
		right.add(toolBarTransformacoes, BorderLayout.WEST);
	}

	/**
	 * Barra de ferramentas para as opções de imagem
	 * 
	 * @param appControl controle principal
	 */
	private void tooBarImagem(AppControl appControl) {

		// Painel da barra de ferramentas
		tooBarImagem = new JPanel(new GridBagLayout());

		// Cria os botões
		btnAbrirImagem = new JButton("Abrir");
		btnSalvarImagem = new JButton("Salvar");
		btnCor = new JButton("Cor");
		linhas = new JLabel("Linhas");
		colunas = new JLabel("Colunas");

		// PROPRIEDADES LABEL
		linhas.setPreferredSize(new Dimension(80, 20));
		colunas.setPreferredSize(new Dimension(80, 20));

		// Label de cor e suas propriedades
		cor = new JLabel();
		cor.setPreferredSize(new Dimension(80, 50));
		cor.setOpaque(true);
		cor.setBackground(Color.RED);

		// Propriedades dos botões
		btnAbrirImagem.setPreferredSize(new Dimension(80, 40));
		btnSalvarImagem.setPreferredSize(new Dimension(80, 40));
		btnCor.setPreferredSize(new Dimension(80, 40));

		// Cria Sliders.
		sldrLargura = new JSlider(0, 25, 1);
		sldrLargura.setMajorTickSpacing(5);
		sldrLargura.setPaintTicks(true);
		sldrLargura.setPaintLabels(true);
		sldrLargura.setPreferredSize(new Dimension(80, 165));
		sldrLargura.setOrientation(JSlider.VERTICAL);

		sldrAltura = new JSlider(0, 25, 1);
		sldrAltura.setMajorTickSpacing(5);
		sldrAltura.setPaintTicks(true);
		sldrAltura.setPaintLabels(true);
		sldrAltura.setPreferredSize(new Dimension(80, 165));
		sldrAltura.setOrientation(JSlider.VERTICAL);

		// Adiciona os listeners.
		btnAbrirImagem.addActionListener(appControl);
		btnSalvarImagem.addActionListener(appControl);
		sldrLargura.addChangeListener(appControl);
		sldrAltura.addChangeListener(appControl);
		btnCor.addActionListener(appControl);

		// Adicionado os botões na barra de ferramentas
		addComp(tooBarImagem, btnAbrirImagem, 0, 0, 1, 1, GridBagConstraints.WEST);
		addComp(tooBarImagem, colunas, 0, 4, 1, 1, GridBagConstraints.WEST);
		addComp(tooBarImagem, btnSalvarImagem, 0, 1, 1, 1, GridBagConstraints.WEST);
		addComp(tooBarImagem, linhas, 0, 6, 1, 1, GridBagConstraints.WEST);
		addComp(tooBarImagem, sldrAltura, 0, 7, 1, 1, GridBagConstraints.WEST);
		addComp(tooBarImagem, sldrLargura, 0, 5, 1, 1, GridBagConstraints.WEST);
		addComp(tooBarImagem, btnCor, 0, 3, 1, 1, GridBagConstraints.WEST);
		addComp(tooBarImagem, cor, 0, 2, 1, 1, GridBagConstraints.WEST);

		// Adiciona toolbar ao JFrame.
		left.add(tooBarImagem, BorderLayout.WEST);
	}

	/**
	 * Habilita os botões na barra de ferramentas
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
		sldrLargura.setEnabled(true);
		sldrAltura.setEnabled(true);
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

	/**
	 * Método para alterar a cor de pintura
	 * 
	 * @param corPadrao cor atual
	 * @return cor selecionada
	 */
	public Color selecionarCor(Color corPadrao) {
		Color cor = JColorChooser.showDialog(null, "Selecionar Cor", corPadrao);
		return cor;
	}

	/**
	 * Seta a cor do label para a nova cor selecionada.
	 * 
	 * @param label label container
	 * @param cor nova cor
	 */
	public void setLabelColor(JLabel label, Color cor) {
		label.setBackground(cor);
	}

	/**
	 * Método que coloca o grid no painel.
	 * 
	 * @param container JPanel que receberá o grid
	 * @param panel que será adicionado
	 */
	public void addGrid(JPanel container, JPanel panel) {
		container.add(panel);
	}

	/**
	 * Pega a cor de fundo de um label.
	 * 
	 * @param label que contêm a cor
	 * @return cor do label
	 */
	public Color getDefaultColor(JLabel label) {
		return label.getBackground();
	}

	/**
	 * Emite uma alerta caso o template não possua dimensões ímpares.
	 * 
	 * @param msg que será exibida
	 * @param titulo da caixa de mensagem
	 */
	public void aviso(String msg, String titulo) {
		JOptionPane.showMessageDialog(this, msg, titulo, JOptionPane.WARNING_MESSAGE);
	}

}
