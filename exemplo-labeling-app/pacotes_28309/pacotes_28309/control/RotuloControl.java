package pacotes_28309.control;

import java.awt.Color;
import java.awt.image.*;
import java.util.*;

public class RotuloControl {

	private int w; // Width.
	private int h; // Height.
	private int t, p, r; // Vizinhança de 4 pixels.
	private int numeroLabel; // Número dos rótulos.
	private BufferedImage img; // Imagem de entrada.
	private BufferedImage imgOut; // Imagem de saída.
	private int[][] pixels; // Matriz de rotulos.
	private int[] newPixels; // Pixels novos da imagem.
	private int[] red, green, blue; // Array de cores, para a nova imagem.
	private Random random;

	/**
	 * Construtor da classe: Recebe ima imagem e seta as variáveis com suas
	 * propriedades.
	 * 
	 * @param img
	 *            BufferedImage em forma de matriz.
	 */
	public RotuloControl(BufferedImage img) {
		this.w = img.getWidth();
		this.h = img.getHeight();
		this.img = img;
		this.pixels = new int[w][h];
		this.newPixels = img.getRGB(0, 0, w, h, null, 0, w);
		this.numeroLabel = 0;
		this.random = new Random();
		this.imgOut = img;
	}

	/**
	 * Método responsável por executar o algoritmo de dois passos e rotular a
	 * imagem.
	 */
	public void rotularImagem() {
		UnionFindControl uf = new UnionFindControl(w);

		// Varrendo imagem
		for (int col = 1; col < w; col++) {
			for (int lin = 1; lin < h; lin++) {

				// Vizinhanças
				t = img.getRGB(col - 1, lin);
				p = img.getRGB(col, lin);
				r = img.getRGB(col, lin - 1);

				// PASSO 1
				if ((p & 0x00FFFFFF) == 0) // Se o pixel for preto
					continue;
				else {// Caso não seja preto
					if ((t & 0x00FFFFFF) == 0 && (r & 0x00FFFFFF) == 0)
						pixels[col][lin] = ++numeroLabel;
					else if ((t & 0x00FFFFFF) != 0 && (r & 0x00FFFFFF) == 0)
						pixels[col][lin] = pixels[col - 1][lin];
					else if ((r & 0x00FFFFFF) != 0 && (t & 0x00FFFFFF) == 0)
						pixels[col][lin] = pixels[col][lin - 1];
					else if ((r & 0x00FFFFFF) != 0 && (t & 0x00FFFFFF) != 0) {
						pixels[col][lin] = pixels[col - 1][lin];
						uf.union(pixels[col][lin - 1], pixels[col - 1][lin]);
					}
				}

			}
		}

		// PASSO 2
		for (int col = 1; col < w; col++) {
			for (int lin = 1; lin < h; lin++) {
				pixels[col][lin] = uf.find(pixels[col][lin]);
			}
		}
	}

	/**
	 * Método para atribuir novas cores à imagem rotulada.
	 * 
	 * @return imgOut BufferedImage contendo a imagem rotulada colorida.
	 */
	public BufferedImage imagemSaida() {

		// Criando um array de cores aleatórias.
		red = new int[numeroLabel + 1];
		green = new int[numeroLabel + 1];
		blue = new int[numeroLabel + 1];

		for (int i = 0; i <= numeroLabel; i++) {
			red[i] = random.nextInt(255);
			green[i] = random.nextInt(255);
			blue[i] = random.nextInt(255);
		}

		// Atribuindo as cores à imagem.
		for (int col = 0; col < w; col++) {
			for (int lin = 0; lin < h; lin++)
				if (pixels[col][lin] != 0)
					newPixels[w * lin + col] = new Color(
							red[pixels[col][lin]],
							green[pixels[col][lin]],
							blue[pixels[col][lin]]).getRGB();

		}

		// Criando a imagem de saída
		imgOut.setRGB(0, 0, w, h, newPixels, 0, w);

		return imgOut;
	}
}
