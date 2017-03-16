package pacotes_28309.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import pacotes_28309.control.*;

@SuppressWarnings("serial")
public class TelaApp extends JFrame {
	private JPanel container, panelOne, panelTwo, imgBottom, tmplBottom, toolBar;
	private JButton abrirImg, abrirTmpl, salvarImg, salvarTmpl, imgCor, tmplCor, btnConvolucao;
	public JLabel lblsldrImgLinhas, lblsldrImgColunas, lblsldrTmplLinhas, lblsldrTmplColunas, lblCorImg, lblCorTmpl;
	public JSlider sldrImgLinhas, sldrImgColunas, sldrTmplLinhas, sldrTmplColunas;
	public JPanel img, tmpl;
	private TitledBorder one, two;
	private Border borda;

	/**
	 * Contrutor da classe. Define as configurações da janela principal da
	 * aplicacação.
	 * 
	 * @param appControl Controle principal da aplicação.
	 */
	public TelaApp(AppControl appControl) {

		// Setando as configurações da janela.
		this.setTitle("App Convolução");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());

		// Montando Layout da Tela
		generateComponents();
		componentProperties();
		layoutDesign();
		addListeners(appControl);

		// Empacotando e exibindo a aplicação.
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}

	/**
	 * Método delegado para instânciar os components da view.
	 */
	private void generateComponents() {

		// Criação dos painéis
		container = new JPanel();
		panelOne = new JPanel(new BorderLayout());
		panelTwo = new JPanel(new BorderLayout());
		img = new JPanel(new BorderLayout());
		tmpl = new JPanel(new BorderLayout());
		imgBottom = new JPanel(new GridBagLayout());
		tmplBottom = new JPanel(new GridBagLayout());
		toolBar = new JPanel(new GridBagLayout());

		// Criando Botões;
		abrirImg = new JButton("Abrir Imagem");
		abrirTmpl = new JButton("Abrir Template");
		salvarImg = new JButton("Salvar Imagem");
		salvarTmpl = new JButton("Salvar Template");
		imgCor = new JButton("Cor da Imagem");
		tmplCor = new JButton("Cor do Template");
		btnConvolucao = new JButton("GERAR CONVOLUÇÃO");

		// Labels para imagem e template
		lblsldrImgLinhas = new JLabel("Linhas [0]: ");
		lblsldrTmplLinhas = new JLabel("Linhas [0]: ");
		lblsldrImgColunas = new JLabel("Colunas [0]: ");
		lblsldrTmplColunas = new JLabel("Colunas [0]: ");
		lblCorImg = new JLabel();
		lblCorTmpl = new JLabel();

		// Criando os Sliders com base no modelo
		sldrImgLinhas = new JSlider(0, 33, 1);
		sldrImgColunas = new JSlider(0, 40, 1);
		sldrTmplLinhas = new JSlider(0, 33, 1);
		sldrTmplColunas = new JSlider(0, 40, 1);
	}

	/**
	 * Método Resposável pelas propriedades dos componentes da view.
	 */
	private void componentProperties() {
		// Adicionando as Bordas dos painéis
		borda = BorderFactory.createLineBorder(new Color(189, 195, 199), 1);
		one = BorderFactory.createTitledBorder(borda, "Imagem Sintética");
		two = BorderFactory.createTitledBorder(borda, "Template");
		one.setTitleJustification(TitledBorder.CENTER);
		two.setTitleJustification(TitledBorder.CENTER);
		panelOne.setBorder(one);
		panelTwo.setBorder(two);

		// Add botões ao Panel

		// Componentes da Imagem
		addComp(imgBottom, lblsldrImgLinhas, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(imgBottom, sldrImgLinhas, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBottom, lblsldrImgColunas, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(imgBottom, sldrImgColunas, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBottom, imgCor, 5, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBottom, lblCorImg, 5, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBottom, abrirImg, 6, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBottom, salvarImg, 6, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

		// Componentes do Template
		addComp(tmplBottom, lblsldrTmplLinhas, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(tmplBottom, sldrTmplLinhas, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBottom, lblsldrTmplColunas, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(tmplBottom, sldrTmplColunas, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBottom, tmplCor, 5, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBottom, lblCorTmpl, 5, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBottom, abrirTmpl, 6, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBottom, salvarTmpl, 6, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

		// Componente da toobar
		addComp(toolBar, btnConvolucao, 5, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		// Adicionando aos painéis
		panelOne.add(img, BorderLayout.CENTER);
		panelTwo.add(tmpl, BorderLayout.CENTER);
		panelOne.add(imgBottom, BorderLayout.SOUTH);
		panelTwo.add(tmplBottom, BorderLayout.SOUTH);

		// Ajustando os painéis ao painel de container
		container.setLayout(new GridLayout(1, 2));
		container.setBorder(new EmptyBorder(10, 10, 10, 10));
		container.add(panelOne);
		container.add(panelTwo);

		// Add ao JFrame
		this.add(container);
		this.add(toolBar, BorderLayout.SOUTH);
	}

	/**
	 * Método usado para melhorar a aparência da view.
	 */
	private void layoutDesign() {

		// Botões BG color
		abrirImg.setBackground(new Color(52, 73, 94));
		abrirTmpl.setBackground(new Color(52, 73, 94));
		salvarImg.setBackground(new Color(52, 73, 94));
		salvarTmpl.setBackground(new Color(52, 73, 94));
		imgCor.setBackground(new Color(52, 73, 94));
		tmplCor.setBackground(new Color(52, 73, 94));
		btnConvolucao.setBackground(new Color(52, 73, 94));

		// Botões Font color
		abrirImg.setForeground(new Color(189, 195, 199));
		abrirTmpl.setForeground(new Color(189, 195, 199));
		salvarImg.setForeground(new Color(189, 195, 199));
		salvarTmpl.setForeground(new Color(189, 195, 199));
		imgCor.setForeground(new Color(189, 195, 199));
		tmplCor.setForeground(new Color(189, 195, 199));
		btnConvolucao.setForeground(new Color(189, 195, 199));

		// Botões tamanho
		abrirImg.setPreferredSize(new Dimension(137, 26));
		abrirTmpl.setPreferredSize(new Dimension(147, 26));

		// Borda Font size
		one.setTitleFont(new Font("Arial", Font.PLAIN, 20));
		two.setTitleFont(new Font("Arial", Font.PLAIN, 20));

		// Borda Font color
		one.setTitleColor(new Color(189, 195, 199));
		two.setTitleColor(new Color(189, 195, 199));

		// Label Font color
		lblsldrImgLinhas.setForeground(new Color(189, 195, 199));
		lblsldrTmplLinhas.setForeground(new Color(189, 195, 199));
		lblsldrImgColunas.setForeground(new Color(189, 195, 199));
		lblsldrTmplColunas.setForeground(new Color(189, 195, 199));

		// Panel BG color
		panelOne.setBackground(new Color(5, 5, 5));
		panelTwo.setBackground(new Color(5, 5, 5));
		img.setBackground(new Color(5, 5, 5));
		tmpl.setBackground(new Color(5, 5, 5));
		imgBottom.setBackground(new Color(5, 5, 5));
		tmplBottom.setBackground(new Color(5, 5, 5));
		toolBar.setBackground(new Color(5, 5, 5));

		// Container bg color
		container.setBackground(new Color(5, 5, 5));

		// Convolução size
		btnConvolucao.setPreferredSize(new Dimension(230, 40));

		// JSlider color
		sldrImgLinhas.setBackground(new Color(5, 5, 5));
		sldrImgColunas.setBackground(new Color(5, 5, 5));
		sldrTmplLinhas.setBackground(new Color(5, 5, 5));
		sldrTmplColunas.setBackground(new Color(5, 5, 5));

		// label cor
		lblCorImg.setPreferredSize(new Dimension(140, 25));
		lblCorTmpl.setPreferredSize(new Dimension(150, 25));
		lblCorImg.setOpaque(true);
		lblCorTmpl.setOpaque(true);
		lblCorImg.setBackground(Color.orange);
		lblCorTmpl.setBackground(Color.orange);

	}

	/**
	 * Método que adiciona os listeners aos componentes.
	 * 
	 * @param appControl controle principal da aplicação.
	 */
	private void addListeners(AppControl appControl) {

		// Listeners dos botões
		abrirImg.addActionListener(appControl);
		abrirTmpl.addActionListener(appControl);
		salvarImg.addActionListener(appControl);
		salvarTmpl.addActionListener(appControl);
		imgCor.addActionListener(appControl);
		tmplCor.addActionListener(appControl);
		btnConvolucao.addActionListener(appControl);

		// Criando os Spinners com base no modelo
		sldrImgLinhas.addChangeListener(appControl);
		sldrImgColunas.addChangeListener(appControl);
		sldrTmplLinhas.addChangeListener(appControl);
		sldrTmplColunas.addChangeListener(appControl);
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
	private void addComp(JPanel painel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place,
			int stretch) {

		GridBagConstraints gridConstraints = new GridBagConstraints();

		gridConstraints.gridx = xPos;
		gridConstraints.gridy = yPos;
		gridConstraints.gridwidth = compWidth;
		gridConstraints.gridheight = compHeight;
		gridConstraints.weightx = 100;
		gridConstraints.weighty = 100;
		gridConstraints.insets = new Insets(5, 5, 5, 5);
		gridConstraints.anchor = place;
		gridConstraints.fill = stretch;

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
	 * Seta um novo rótulo para o label.
	 * 
	 * @param label container
	 * @param text rótulo
	 */
	public void setLabel(JLabel label, String text) {
		label.setText(text);
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
