package pacotes_28309.view;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;

import pacotes_28309.control.*;

/**
 * Classe Tela: Pricipal view da aplicação.
 * 
 * @author Francis Ribeiro
 */
public class Tela extends JFrame {
	private JPanel container, panelOne, panelTwo;
	private JPanel topPanelOne, topPanelTwo;
	private JButton abrirImg, abrirTmpl, salvarImg, salvarTmpl;
	public JPanel img, tmpl;
	private JPanel imgBot, tmplBot;
	public JSlider imgLinhas, imgColunas;
	private JButton imgCor;
	public JLabel lblImgLinhas, lblImgColunas;
	public JSlider tmplLinhas, tmplColunas;
	private JButton tmplCor;
	public JLabel lblTmplLinhas, lblTmplColunas;
	private JPanel toolBar;
	private JButton btnConvolucao;
	private TitledBorder one, two;
	private Border borda;
	public JLabel corImg, corTmpl;

	/**
	 * Contrutor da classe. Define as configurações da janela principal da
	 * aplicacação.
	 * 
	 * @param appControl
	 *            Controle principal da aplicação.
	 */
	public Tela(AppControl appControl) {

		// Setando as configurações da janela.
		this.setTitle("App Grid");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());

		// Montando Layout da Tela
		montaLayout(appControl);

		// Empacotando e exibindo a aplicação.
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}

	private void generateComponents() {

		// Criação dos painéis
		container = new JPanel();
		panelOne = new JPanel(new BorderLayout());
		panelTwo = new JPanel(new BorderLayout());
		topPanelOne = new JPanel(new GridLayout(1, 3, 20, 20));
		topPanelTwo = new JPanel(new GridLayout(1, 3, 20, 20));
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
		btnConvolucao = new JButton("Gerar Convolução");

		// Labels para imagem e template
		lblImgLinhas = new JLabel("Linhas [0]: ");
		lblTmplLinhas = new JLabel("Linhas [0]: ");
		lblImgColunas = new JLabel("Colunas [0]: ");
		lblTmplColunas = new JLabel("Colunas [0]: ");
		corImg = new JLabel();
		corTmpl = new JLabel();

		// Criando os Spinners com base no modelo
		imgLinhas = new JSlider(0, 19, 1);
		imgColunas = new JSlider(0, 25, 1);
		tmplLinhas = new JSlider(0, 19, 1);
		tmplColunas = new JSlider(0, 25, 1);

	}

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
		topPanelOne.add(abrirImg);
		topPanelTwo.add(abrirTmpl);
		topPanelOne.add(salvarImg);
		topPanelTwo.add(salvarTmpl);

		// Componentes da Imagem
		addComp(imgBot, lblImgLinhas, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(imgBot, imgLinhas, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBot, lblImgColunas, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(imgBot, imgColunas, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBot, imgCor, 5, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBot, corImg, 5, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

		// Componentes do Template
		addComp(tmplBot, lblTmplLinhas, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(tmplBot, tmplLinhas, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBot, lblTmplColunas, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(tmplBot, tmplColunas, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBot, tmplCor, 5, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBot, corTmpl, 5, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

		// Componente da toobar
		addComp(toolBar, btnConvolucao, 5, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		// Adicionando aos painéis
		panelOne.add(topPanelOne, BorderLayout.NORTH);
		panelTwo.add(topPanelTwo, BorderLayout.NORTH);
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

	private void layoutDesign() {

		// Botões BG color
		abrirImg.setBackground(new Color(63, 81, 181));
		abrirTmpl.setBackground(new Color(63, 81, 181));
		salvarImg.setBackground(new Color(63, 81, 181));
		salvarTmpl.setBackground(new Color(63, 81, 181));
		imgCor.setBackground(new Color(63, 81, 181));
		tmplCor.setBackground(new Color(63, 81, 181));
		btnConvolucao.setBackground(new Color(230, 74, 25));

		// Botões Font size
		abrirImg.setFont(new Font("Arial", Font.PLAIN, 17));
		abrirTmpl.setFont(new Font("Arial", Font.PLAIN, 17));
		salvarImg.setFont(new Font("Arial", Font.PLAIN, 17));
		salvarTmpl.setFont(new Font("Arial", Font.PLAIN, 17));
		imgCor.setFont(new Font("Arial", Font.PLAIN, 17));
		tmplCor.setFont(new Font("Arial", Font.PLAIN, 17));
		btnConvolucao.setFont(new Font("Arial", Font.PLAIN, 20));

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

		// Label Font Size
		lblImgLinhas.setFont(new Font("Arial", Font.PLAIN, 17));
		lblTmplLinhas.setFont(new Font("Arial", Font.PLAIN, 17));
		lblImgColunas.setFont(new Font("Arial", Font.PLAIN, 17));
		lblTmplColunas.setFont(new Font("Arial", Font.PLAIN, 17));

		// Label Font color
		lblImgLinhas.setForeground(new Color(255, 193, 7));
		lblTmplLinhas.setForeground(new Color(255, 193, 7));
		lblImgColunas.setForeground(new Color(255, 193, 7));
		lblTmplColunas.setForeground(new Color(255, 193, 7));

		// Panel BG color
		panelOne.setBackground(new Color(33, 33, 33));
		panelTwo.setBackground(new Color(33, 33, 33));
		topPanelOne.setBackground(new Color(33, 33, 33));
		topPanelTwo.setBackground(new Color(33, 33, 33));
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
		corImg.setPreferredSize(new Dimension(165, 40));
		corTmpl.setPreferredSize(new Dimension(175, 40));
		corImg.setOpaque(true);
		corTmpl.setOpaque(true);
		corImg.setBackground(Color.CYAN);
		corTmpl.setBackground(Color.MAGENTA);

	}

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

	private void montaLayout(AppControl appControl) {
		generateComponents();
		componentProperties();
		addListeners(appControl);
		layoutDesign();
	}

	// Seta as regras para um componente destinado ao GridBagLayout e o adiciona
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
	 * Método para navegar pela estrutura de diretórios e carregar uma imagem.
	 * 
	 * @return nomeArqLido Nome do arquivo lido em uma String
	 */
	public String abrirImagem() {
		JFileChooser arquivo;
		File diretorio, nomeArq = null;
		String nomeArqLido = null;
		int saida;

		// Localizando o arquivo
		arquivo = new JFileChooser();
		diretorio = new File("..\\");
		arquivo.setCurrentDirectory(diretorio);
		arquivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		saida = arquivo.showOpenDialog(arquivo);

		if (saida == JFileChooser.APPROVE_OPTION) {
			// Fazendo a leitura do arquivo
			nomeArq = arquivo.getSelectedFile();
			nomeArqLido = nomeArq.toString();
		}

		return nomeArqLido;
	}

	public void salvarImagem() {
		JFileChooser fileChooser;
		int userSelection;
		File fileToSave;

		fileChooser = new JFileChooser();
		userSelection = fileChooser.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION)
			fileToSave = fileChooser.getSelectedFile();
	}

	public Color selecionarCor(Color corPadrao) {
		Color cor = JColorChooser.showDialog(null, "Selecionar Cor", corPadrao);
		return cor;
	}

	public void setLabelColor(JLabel label, Color cor) {
		label.setBackground(cor);
	}

	public void setLabel(JLabel label, String text) {
		label.setText(text);
	}

	public void addGrid(JPanel container, JPanel panel) {
		container.add(panel);
	}
	
	public Color getImgDefaultColor(){
		return corImg.getBackground();
	}
	
	public Color getTmplDefaultColor(){
		return corTmpl.getBackground();
	}
	
}
