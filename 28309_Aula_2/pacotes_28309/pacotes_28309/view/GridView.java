package pacotes_28309.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import pacotes_28309.control.AppControl;
import pacotes_28309.model.Pixel;

public class GridView extends JPanel {

	private Color cor;
	private JPanel[][] grid;
	private int linhas, colunas;
	private BufferedImage imagem;
	private Boolean pressed = false;

	/**
	 * Método que gera um grid de acordo com os parâmetros.
	 * 
	 * @param nLin número de linhas do grid
	 * @param nCol número de colunas do grid
	 * @param abrir caso deseje abrir uma imagem
	 * @return
	 */
	public JPanel gerarGrid(int nLin, int nCol, Boolean abrir) {
		JPanel celula;
		GridBagConstraints gbc;

		// Caso esteja abrindo a imagem, setamos o tamanho do grid com o tamanho
		// da imagem.
		if (abrir) {
			abrirImagem();
			nCol = imagem.getWidth(); // Largura do grid
			nLin = imagem.getHeight(); // Altura do grid
		}

		// Seta as largura e altura do objeto grid
		this.linhas = nLin;
		this.colunas = nCol;

		// Novo grid e suas propriedades
		grid = new JPanel[nLin][nCol];
		setLayout(new GridBagLayout());
		setBackground(new Color(33, 33, 33));

		// Propriedade do gerenciado de layout
		gbc = new GridBagConstraints();

		// Então o grid é gerado
		for (int lin = 0; lin < nLin; lin++) {
			for (int col = 0; col < nCol; col++) {
				gbc.gridx = col;
				gbc.gridy = lin;

				// A cada iteração uma nova célula é gerada e armazenada no grid
				celula = desenhaCelula();
				grid[lin][col] = celula;

				// Gerando as bordas do grid
				Border border = null;
				if (lin < nLin - 1) {
					if (col < nCol - 1)
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					else
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
				} else {
					if (col < nCol - 1)
						border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
					else
						border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
				}

				// Adicionando as bordas e a célula ao painel
				celula.setBorder(border);
				add(celula, gbc);
			}
		}

		// Caso esteja abrindo uma imagem setamos as cores das células para
		// as cores dos pixels da imagem.
		if (abrir) {
			for (int col = 0; col < colunas; col++) {
				for (int lin = 0; lin < linhas; lin++) {
					grid[lin][col].setBackground(new Color(imagem.getRGB(col, lin)));
				}
			}
		}

		return this;
	}

	/**
	 * Desenha uma nova célula (componente unitário do grid), e seta suas
	 * propriedades.
	 * 
	 * @return nova celula
	 */
	private JPanel desenhaCelula() {
		JPanel celula;

		// Instância de célula
		celula = new JPanel();

		// Tamanho unitário
		celula.setPreferredSize(new Dimension(25, 25));

		// Cor de fundo
		celula.setBackground(new Color(33, 33, 33));

		// Eventos de mouse
		celula.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pressed = true;
				celula.setBackground(cor);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pressed = false;
				celula.setBackground(cor);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (pressed)
					celula.setBackground(cor);
			}

		});

		return celula;
	}

	/**
	 * Seta a cor para o grid.
	 * 
	 * @param cor selecionada
	 */
	public void setColor(Color cor) {
		this.cor = cor;
	}

	/**
	 * Selecina a caminho para onde deverá ser salva a nova imagem.
	 * 
	 * @param tipo pode ser IMAGEM ou TEMPLATE
	 */
	public void salvarImagem(String tipo) {
		JFileChooser fileChooser;
		int userSelection;
		File fileToSave = null;

		fileChooser = new JFileChooser();
		userSelection = fileChooser.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION)
			fileToSave = fileChooser.getSelectedFile();

		salvaPixels(fileToSave.toString(), "png", tipo);
	}

	/**
	 * Salva os pixels como imagem unica.
	 *
	 * @param nome da imagem
	 * @param extensao  da imagem
	 * @param tipo (IMAGEM ou TEMPLATE)
	 */
	private void salvaPixels(String nome, String extensao, String tipo) {
		BufferedImage image = new BufferedImage(colunas, linhas, BufferedImage.TYPE_INT_RGB);
		int[] pixels = image.getRGB(0, 0, colunas, linhas, null, 0, colunas);

		// Percorre o JPanel salvando cada célula como um pixel
		for (int col = 0; col < colunas; col++) {
			for (int lin = 0; lin < linhas; lin++) {
				pixels[colunas * lin + col] = grid[lin][col].getBackground().getRGB();
			}
		}

		// Gera uma imagem RGB
		image.setRGB(0, 0, colunas, linhas, pixels, 0, colunas);

		// Salva a imagem
		try {
			ImageIO.write(image, extensao, new File(nome + " [" + tipo + "]" + "." + extensao));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para navegar pela estrutura de diretórios e carregar uma imagem.
	 * 
	 */
	public void abrirImagem() {
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

		// Fazendo a leitura do arquivo
		if (saida == JFileChooser.APPROVE_OPTION) {
			nomeArq = arquivo.getSelectedFile();
			nomeArqLido = nomeArq.toString();
		}

		// Aloca a Imagem carregada
		try {
			imagem = ImageIO.read(new File(nomeArqLido));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
