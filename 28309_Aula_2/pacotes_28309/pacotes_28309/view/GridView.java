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

	private JPanel panel, celula;
	private Color cor;
	private JPanel[][] pnlMatriz;
	private int l, c;
	private BufferedImage imagem;

	/**
	 * 
	 * @param nLin
	 * @param nCol
	 * @param appControl
	 * @return
	 */
	public JPanel TestPane(int nLin, int nCol, Boolean abrir) {
		if (abrir) {
			abrirImagem();
			nCol = imagem.getWidth();
			nLin = imagem.getHeight();
		}

		this.l = nLin;
		this.c = nCol;

		GridBagConstraints gbc;

		pnlMatriz = new JPanel[nLin][nCol];

		setLayout(new GridBagLayout());
		setBackground(new Color(33, 33, 33));

		gbc = new GridBagConstraints();

		for (int lin = 0; lin < nLin; lin++) {
			for (int col = 0; col < nCol; col++) {
				gbc.gridx = col;
				gbc.gridy = lin;

				celula = drawCelula();

				pnlMatriz[lin][col] = celula;

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

				celula.setBorder(border);
				add(celula, gbc);
			}
		}

		if (abrir) {
			for (int col = 0; col < c; col++) {
				for (int lin = 0; lin < l; lin++) {
					pnlMatriz[lin][col].setBackground(new Color(imagem.getRGB(col, lin)));
				}
			}
		}

		return this;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel drawCelula() {
		JPanel celula;

		celula = new JPanel();
		celula.setPreferredSize(new Dimension(25, 25));
		celula.setBackground(new Color(33, 33, 33));
		celula.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				celula.setBackground(cor);
			}

		});

		return celula;
	}

	public void setColor(Color cor) {
		this.cor = cor;
	}

	public void salvarImagem(String tipo) {
		JFileChooser fileChooser;
		int userSelection;
		File fileToSave = null;

		fileChooser = new JFileChooser();
		userSelection = fileChooser.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION)
			fileToSave = fileChooser.getSelectedFile();

		saveImage(fileToSave.toString(), "png", tipo);
	}

	/**
	 * Save the Panel as image with the name and the type in parameters
	 *
	 * @param name
	 *            name of the file
	 * @param type
	 *            type of the file
	 */
	private void saveImage(String name, String extension, String tipo) {
		BufferedImage image = new BufferedImage(c, l, BufferedImage.TYPE_INT_RGB);
		int[] pixels = image.getRGB(0, 0, c, l, null, 0, c);

		for (int col = 0; col < c; col++) {
			for (int lin = 0; lin < l; lin++) {
				pixels[c * lin + col] = pnlMatriz[lin][col].getBackground().getRGB();
			}
		}

		image.setRGB(0, 0, c, l, pixels, 0, c);

		try {
			ImageIO.write(image, extension, new File(name + " [" + tipo + "]" + "." + extension));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para navegar pela estrutura de diretórios e carregar uma imagem.
	 * 
	 * @return nomeArqLido Nome do arquivo lido em uma String
	 */
	public void abrirImagem() {
		JFileChooser arquivo;
		File diretorio, nomeArq = null;
		String nomeArqLido = null;
		int saida;
		int w, h;

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

		try {
			imagem = ImageIO.read(new File(nomeArqLido));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
