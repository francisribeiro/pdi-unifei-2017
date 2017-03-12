package pacotes_28309.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;

public class GridPanel extends JPanel {

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
	 * @param img imagem de entrada
	 * @return gird gerado
	 */
	public JPanel gerarGrid(int nLin, int nCol, Boolean abrir, BufferedImage img) {
		// Caso esteja abrindo a imagem, setamos o tamanho do grid com o tamanho
		// da imagem.
		if (abrir) {
			abrirImagem(img);
			nCol = imagem.getWidth(); // Largura do grid
			nLin = imagem.getHeight(); // Altura do grid
		}

		// Seta as largura e altura do objeto grid
		this.linhas = nLin;
		this.colunas = nCol;

		// Novo grid e suas propriedades
		grid = new JPanel[nLin][nCol];
		setLayout(new GridBagLayout());
		setBackground(new Color(5, 5, 5));

		// Propriedade do gerenciado de layout
		GridBagConstraints gbc = new GridBagConstraints();

		// Então o grid é gerado
		for (int lin = 0; lin < nLin; lin++) {
			for (int col = 0; col < nCol; col++) {
				gbc.gridx = col;
				gbc.gridy = lin;

				// A cada iteração uma nova célula é gerada e armazenada no grid
				JPanel celula = desenhaCelula();
				grid[lin][col] = celula;

				// Gerando as bordas do grid
				Border border = null;

				if (lin < nLin - 1) {
					if (col < nCol - 1)
						border = new MatteBorder(1, 1, 0, 0, new Color(33, 33, 33));
					else
						border = new MatteBorder(1, 1, 0, 1, new Color(33, 33, 33));
				} else {
					if (col < nCol - 1)
						border = new MatteBorder(1, 1, 1, 0, new Color(33, 33, 33));
					else
						border = new MatteBorder(1, 1, 1, 1, new Color(33, 33, 33));
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
		JPanel celula = new JPanel();

		celula.setPreferredSize(new Dimension(15, 15));
		// celula.setBackground(new Color(33,33,33));
		celula.setBackground(new Color(0, 0, 0));
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
	 * Gera uma BufferedImage com os dados do grid.
	 * 
	 * @return imagem do grid
	 */
	public BufferedImage img() {
		BufferedImage img = new BufferedImage(colunas, linhas, BufferedImage.TYPE_INT_RGB);
		int[] pixels = img.getRGB(0, 0, colunas, linhas, null, 0, colunas);

		// Percorre o JPanel salvando cada célula como um pixel
		for (int col = 0; col < colunas; col++)
			for (int lin = 0; lin < linhas; lin++)
				pixels[colunas * lin + col] = grid[lin][col].getBackground().getRGB();

		// Gera uma imagem RGB
		img.setRGB(0, 0, colunas, linhas, pixels, 0, colunas);

		return img;
	}

	/**
	 * Salva os pixels como imagem unica.
	 *
	 * @param nome da imagem
	 * @param extensao da imagem
	 * @param tipo (IMAGEM ou TEMPLATE)
	 */
	private void salvaPixels(String nome, String extensao, String tipo) {
		try {
			ImageIO.write(img(), extensao, new File(nome + " [" + tipo + "]" + "." + extensao));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para navegar pela estrutura de diretórios e carregar uma imagem.
	 * 
	 */
	public void abrirImagem(BufferedImage img) {
		String nomeArqLido = null;

		if (img != null) {
			imagem = img;
			return;
		}

		// Localizando o arquivo
		JFileChooser arquivo = new JFileChooser();
		File diretorio = new File("..\\");
		arquivo.setCurrentDirectory(diretorio);
		arquivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int saida = arquivo.showOpenDialog(arquivo);

		// Fazendo a leitura do arquivo
		if (saida == JFileChooser.APPROVE_OPTION) {
			File nomeArq = arquivo.getSelectedFile();
			nomeArqLido = nomeArq.toString();
		} else if (saida == JFileChooser.CANCEL_OPTION)
			nomeArqLido = "";

		// Aloca a Imagem carregada
		try {
			imagem = ImageIO.read(new File(nomeArqLido));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Selecina a caminho para onde deverá ser salva a nova imagem.
	 * 
	 * @param tipo pode ser IMAGEM ou TEMPLATE
	 */
	public void salvarImagem(String tipo) {
		File fileToSave = null;

		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION)
			fileToSave = fileChooser.getSelectedFile();
		else if (userSelection == JFileChooser.CANCEL_OPTION)
			return;

		salvaPixels(fileToSave.toString(), "png", tipo);
	}
}
