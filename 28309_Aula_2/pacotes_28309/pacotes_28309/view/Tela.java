package pacotes_28309.view;

import java.awt.*;
import java.awt.event.*;
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
	private JButton clearImg, clearTmpl, abrirImg, abrirTmpl, salvarImg, salvarTmpl;
	private JPanel img, tmpl;
	private JPanel imgBot, tmplBot;
	private JSpinner imgLinhas, imgColunas;
	private JButton imgCor;
	private JLabel lblImgLinhas, lblImgColunas, lblImgCor;
	private JSpinner tmplLinhas, tmplColunas;
	private JButton tmplCor;
	private JLabel lblTmplLinhas, lblTmplColunas, lblTmplCor;
	private JPanel toolBar;
	private JButton btnConvolucao;

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
		generateLayout();

		// Empacotando e exibindo a aplicação.
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}

	private void generateLayout() {
		Border borda;
		TitledBorder one, two;

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

		// Adicionando as Bordas dos painéis
		borda = BorderFactory.createLineBorder(new Color(255, 193, 7), 1);
		one = BorderFactory.createTitledBorder(borda, "Imagem Sintética");
		two = BorderFactory.createTitledBorder(borda, "Template");
		one.setTitleJustification(TitledBorder.CENTER);
		two.setTitleJustification(TitledBorder.CENTER);
		panelOne.setBorder(one);
		panelTwo.setBorder(two);

		// Criando Botões;
		clearImg = new JButton("Limpar Imagem");
		clearTmpl = new JButton("Limpar Template");
		abrirImg = new JButton("Abrir Imagem");
		abrirTmpl = new JButton("Abrir Template");
		salvarImg = new JButton("Salvar Imagem");
		salvarTmpl = new JButton("Salvar Template");
		imgCor = new JButton("Alterar Cor");
		tmplCor = new JButton("Alterar Cor");
		btnConvolucao = new JButton("Gerar Convolução");

		// Labels para imagem e template
		lblImgLinhas = new JLabel("Linhas: ");
		lblTmplLinhas = new JLabel("Linhas: ");
		lblImgColunas = new JLabel("Colunas: ");
		lblTmplColunas = new JLabel("Colunas: ");
		lblImgCor = new JLabel("Cor: ");
		lblTmplCor = new JLabel("Cor: ");

		// Criando os Spinners com base no modelo
		imgLinhas = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		imgColunas = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		tmplLinhas = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		tmplColunas = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));

		// Add botões ao Panel
		topPanelOne.add(clearImg);
		topPanelTwo.add(clearTmpl);
		topPanelOne.add(abrirImg);
		topPanelTwo.add(abrirTmpl);
		topPanelOne.add(salvarImg);
		topPanelTwo.add(salvarTmpl);

		// Componentes da Imagem
		addComp(imgBot, lblImgLinhas, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(imgBot, imgLinhas, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBot, lblImgColunas, 2, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(imgBot, imgColunas, 3, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(imgBot, lblImgCor, 4, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(imgBot, imgCor, 5, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

		// Componentes do Template
		addComp(tmplBot, lblTmplLinhas, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(tmplBot, tmplLinhas, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBot, lblTmplColunas, 2, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(tmplBot, tmplColunas, 3, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(tmplBot, lblTmplCor, 4, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addComp(tmplBot, tmplCor, 5, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

		// Componente da toobar
		addComp(toolBar, btnConvolucao, 5, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		// Adicionando aos painéis
		panelOne.add(topPanelOne, BorderLayout.NORTH);
		panelTwo.add(topPanelTwo, BorderLayout.NORTH);
		panelOne.add(img, BorderLayout.CENTER);
		panelTwo.add(tmpl, BorderLayout.CENTER);
		panelOne.add(imgBot, BorderLayout.SOUTH);
		panelTwo.add(tmplBot, BorderLayout.SOUTH);

		/////////////////////////////////////////////////
		// PERSONALIZANDO //
		/////////////////////////////////////////////////

		// Botões BG color
		clearImg.setBackground(new Color(63, 81, 181));
		clearTmpl.setBackground(new Color(63, 81, 181));
		abrirImg.setBackground(new Color(63, 81, 181));
		abrirTmpl.setBackground(new Color(63, 81, 181));
		salvarImg.setBackground(new Color(63, 81, 181));
		salvarTmpl.setBackground(new Color(63, 81, 181));
		imgCor.setBackground(new Color(63, 81, 181));
		tmplCor.setBackground(new Color(63, 81, 181));
		btnConvolucao.setBackground(new Color(230, 74, 25));

		// Botões Font size
		clearImg.setFont(new Font("Arial", Font.PLAIN, 17));
		clearTmpl.setFont(new Font("Arial", Font.PLAIN, 17));
		abrirImg.setFont(new Font("Arial", Font.PLAIN, 17));
		abrirTmpl.setFont(new Font("Arial", Font.PLAIN, 17));
		salvarImg.setFont(new Font("Arial", Font.PLAIN, 17));
		salvarTmpl.setFont(new Font("Arial", Font.PLAIN, 17));
		imgCor.setFont(new Font("Arial", Font.PLAIN, 17));
		tmplCor.setFont(new Font("Arial", Font.PLAIN, 17));
		btnConvolucao.setFont(new Font("Arial", Font.PLAIN, 20));

		// Botões Font color
		clearImg.setForeground(new Color(255, 193, 7));
		clearTmpl.setForeground(new Color(255, 193, 7));
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
		lblImgCor.setFont(new Font("Arial", Font.PLAIN, 17));
		lblTmplCor.setFont(new Font("Arial", Font.PLAIN, 17));

		// Label Font color
		lblImgLinhas.setForeground(new Color(255, 193, 7));
		lblTmplLinhas.setForeground(new Color(255, 193, 7));
		lblImgColunas.setForeground(new Color(255, 193, 7));
		lblTmplColunas.setForeground(new Color(255, 193, 7));
		lblImgCor.setForeground(new Color(255, 193, 7));
		lblTmplCor.setForeground(new Color(255, 193, 7));

		// Spinner Font size
		imgLinhas.setFont(new Font("Arial", Font.PLAIN, 17));
		imgColunas.setFont(new Font("Arial", Font.PLAIN, 17));
		tmplLinhas.setFont(new Font("Arial", Font.PLAIN, 17));
		tmplColunas.setFont(new Font("Arial", Font.PLAIN, 17));

		// Spiner BG color e Font color
		JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) imgLinhas.getEditor();
		editor.getTextField().setBackground(new Color(63, 81, 181));
		editor.getTextField().setForeground(new Color(255, 255, 255));
		JSpinner.DefaultEditor editor2 = (JSpinner.DefaultEditor) imgColunas.getEditor();
		editor2.getTextField().setBackground(new Color(63, 81, 181));
		editor2.getTextField().setForeground(new Color(255, 255, 255));
		JSpinner.DefaultEditor editor3 = (JSpinner.DefaultEditor) tmplLinhas.getEditor();
		editor3.getTextField().setBackground(new Color(63, 81, 181));
		editor3.getTextField().setForeground(new Color(255, 255, 255));
		JSpinner.DefaultEditor editor4 = (JSpinner.DefaultEditor) tmplColunas.getEditor();
		editor4.getTextField().setBackground(new Color(63, 81, 181));
		editor4.getTextField().setForeground(new Color(255, 255, 255));

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

		/////////////////////////////////////////////////
		/////////////////////////////////////////////////

		// Ajustando os painés ao painel de container
		container.setLayout(new GridLayout(1, 2));
		container.setBorder(new EmptyBorder(10, 10, 10, 10));
		container.add(panelOne);
		container.add(panelTwo);

		// Add ao JFrame
		this.add(container);
		this.add(toolBar, BorderLayout.NORTH);
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

}
