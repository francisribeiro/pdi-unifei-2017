package pacotes_28309.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import pacotes_28309.control.*;

public class TelaApp extends JFrame {
	private JPanel container, panelOne, panelTwo, imgBot, tmplBot, toolBar;
	private JButton abrirImg, abrirTmpl, salvarImg, salvarTmpl, imgCor, tmplCor, btnConvolucao;
	public JLabel lblImgLinhas, lblImgColunas, lblTmplLinhas, lblTmplColunas, corImg, corTmpl;
	public JSlider imgLinhas, imgColunas, tmplLinhas, tmplColunas;
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
		addListeners(appControl);
		layoutDesign();

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
		imgBot = new JPanel(new GridBagLayout());
		tmplBot = new JPanel(new GridBagLayout());
		toolBar = new JPanel(new GridBagLayout());

		// Criando Botões;
		abrirImg = new JButton("Abrir Imagem");
		abrirTmpl = new JButton("Abrir Template");
		salvarImg = new JButton("Salvar Imagem");
		salvarTmpl = new JButton("Salvar Template");
		imgCor = new JButton("Cor da Imagem");
		tmplCor = new JButton("Cor do Template");
		btnConvolucao = new JButton("CONVOLUÇÃO");

		// Labels para imagem e template
		lblImgLinhas = new JLabel("Linhas [0]: ");
		lblTmplLinhas = new JLabel("Linhas [0]: ");
		lblImgColunas = new JLabel("Colunas [0]: ");
		lblTmplColunas = new JLabel("Colunas [0]: ");
		corImg = new JLabel();
		corTmpl = new JLabel();

		// Criando os Sliders com base no modelo
		imgLinhas = new JSlider(0, 33, 1);
		imgColunas = new JSlider(0, 40, 1);
		tmplLinhas = new JSlider(0, 7, 1);
		tmplColunas = new JSlider(0, 7, 1);
	}

	/**
	 * Método Resposável pelas propriedades dos componentes da view.
	 */
	private void componentProperties() {
		// Adicionando as Bordas dos painéis
		borda = BorderFactory.createLineBorder(new Color(255, 193, 7), 1);
		one = BorderFactory.createTitledBorder(borda, "Imagem Sintética");
		two = BorderFactory.createTitledBorder(borda, "Template");
		one.setTitleJustification(TitledBorder.CENTER);
		two.setTitleJustification(TitledBorder.CENTER);
		panelOne.setBorder(one);
		panelTwo.setBorder(two);

		// Add botões ao Panel

		// Componentes da Imagem
		addComp(imgBot, lblImgLinhas, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(imgBot, imgLinhas, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBot, lblImgColunas, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(imgBot, imgColunas, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBot, imgCor, 5, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBot, corImg, 5, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBot, abrirImg, 6, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBot, salvarImg, 6, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

		// Componentes do Template
		addComp(tmplBot, lblTmplLinhas, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(tmplBot, tmplLinhas, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBot, lblTmplColunas, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(tmplBot, tmplColunas, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBot, tmplCor, 5, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBot, corTmpl, 5, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBot, abrirTmpl, 6, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBot, salvarTmpl, 6, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

		// Componente da toobar
		addComp(toolBar, btnConvolucao, 5, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		// Adicionando aos painéis
		panelOne.add(img, BorderLayout.CENTER);
		panelTwo.add(tmpl, BorderLayout.CENTER);
		panelOne.add(imgBot, BorderLayout.SOUTH);
		panelTwo.add(tmplBot, BorderLayout.SOUTH);

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
		abrirImg.setBackground(new Color(63, 81, 181));
		abrirTmpl.setBackground(new Color(63, 81, 181));
		salvarImg.setBackground(new Color(63, 81, 181));
		salvarTmpl.setBackground(new Color(63, 81, 181));
		imgCor.setBackground(new Color(63, 81, 181));
		tmplCor.setBackground(new Color(63, 81, 181));
		btnConvolucao.setBackground(new Color(230, 74, 25));

		// Botões Font color
		abrirImg.setForeground(new Color(255, 193, 7));
		abrirTmpl.setForeground(new Color(255, 193, 7));
		salvarImg.setForeground(new Color(255, 193, 7));
		salvarTmpl.setForeground(new Color(255, 193, 7));
		imgCor.setForeground(new Color(255, 193, 7));
		tmplCor.setForeground(new Color(255, 193, 7));
		btnConvolucao.setForeground(new Color(255, 255, 255));

		// Borda Font size
		one.setTitleFont(new Font("Arial", Font.PLAIN, 20));
		two.setTitleFont(new Font("Arial", Font.PLAIN, 20));

		// Borda Font color
		one.setTitleColor(new Color(255, 193, 7));
		two.setTitleColor(new Color(255, 193, 7));

		// Label Font color
		lblImgLinhas.setForeground(new Color(255, 193, 7));
		lblTmplLinhas.setForeground(new Color(255, 193, 7));
		lblImgColunas.setForeground(new Color(255, 193, 7));
		lblTmplColunas.setForeground(new Color(255, 193, 7));

		// Panel BG color
		panelOne.setBackground(new Color(33, 33, 33));
		panelTwo.setBackground(new Color(33, 33, 33));
		img.setBackground(new Color(33, 33, 33));
		tmpl.setBackground(new Color(33, 33, 33));
		imgBot.setBackground(new Color(33, 33, 33));
		tmplBot.setBackground(new Color(33, 33, 33));
		toolBar.setBackground(new Color(33, 33, 33));

		// Container bg color
		container.setBackground(new Color(33, 33, 33));

		// Convolução size
		btnConvolucao.setPreferredSize(new Dimension(230, 40));

		// JSlider color
		imgLinhas.setBackground(new Color(33, 33, 33));
		imgColunas.setBackground(new Color(33, 33, 33));
		tmplLinhas.setBackground(new Color(33, 33, 33));
		tmplColunas.setBackground(new Color(33, 33, 33));

		// label cor
		corImg.setPreferredSize(new Dimension(140, 25));
		corTmpl.setPreferredSize(new Dimension(150, 25));
		corImg.setOpaque(true);
		corTmpl.setOpaque(true);
		corImg.setBackground(Color.CYAN);
		corTmpl.setBackground(Color.MAGENTA);

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
		imgLinhas.addChangeListener(appControl);
		imgColunas.addChangeListener(appControl);
		tmplLinhas.addChangeListener(appControl);
		tmplColunas.addChangeListener(appControl);
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
	 */
	public void oddAlert() {
		JOptionPane.showMessageDialog(this, "O template precisa ter dimensões ÍMPARES!", "Aviso",
				JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Emite uma alerta caso a imagem não tenha sido setada.
	 */
	public void emptyImg() {
		JOptionPane.showMessageDialog(this, "Não existe IMAGEM para ser Convoluída!", "Imagem vazia",
				JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Emite uma alerta caso o template não tenha sido setada.
	 */
	public void emptyTmpl() {
		JOptionPane.showMessageDialog(this, "Não existe TEMPLATE para ser Convoluído!", "Template Vazio",
				JOptionPane.WARNING_MESSAGE);
	}
}
