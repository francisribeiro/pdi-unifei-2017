package pacotes_28309.control;

import java.awt.*;
import java.awt.image.*;

public class Histograma {

	private int[] dadosHistograma;
	private int maxY = 0;
	private BufferedImage imagem;

	public Histograma(BufferedImage imagem) {
		this.imagem = imagem;
	}

	// método de aplicação do filtro escala de cinza
	// recebe como parâmetro uma imagem
	public BufferedImage escalaDeCinza() {
		// pegar largura e altura da imagem
		int width = imagem.getWidth();
		int height = imagem.getHeight();
		BufferedImage newImg = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_ARGB);

		int media = 0;
		// laço para varrer a matriz de pixels da imagem
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) { // rgb recebe o valor RGB do
												// pixel em questão
				int rgb = imagem.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16);// //R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				// media dos valores do RGB
				// será o valor do pixel na nova imagem
				media = (r + g + b) / 3;

				// criar uma instância de Color
				Color color = new Color(media, media, media);
				// setar o valor do pixel com a nova cor
				newImg.setRGB(i, j, color.getRGB());
			}
		}

		return newImg;
	}

	public BufferedImage threshold(BufferedImage image, int limiar) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16);
				int g = (int) ((rgb & 0x0000FF00) >>> 8);
				int b = (int) (rgb & 0x000000FF);
				int media = (r + g + b) / 3;
				Color white = new Color(255, 255, 255);
				Color black = new Color(0, 0, 0);
				// como explicado no artigo, no threshold definimos um limiar,
				// que é um valor "divisor de águas"
				// pixels com valor ABAIXO do limiar viram pixels PRETOS,
				// pixels com valor ACIMA do limiar viram pixels BRANCOS
				if (media < limiar)
					newImg.setRGB(i, j, black.getRGB());
				else
					newImg.setRGB(i, j, white.getRGB());
			}
		}

		return newImg;
	}

	public void histograma(BufferedImage img) {
		dadosHistograma = new int[256];
		
		for (int j = 0; j < 256; j++) {
			dadosHistograma[j] = 0;
		}

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color c = new Color(img.getRGB(x, y));
				dadosHistograma[c.getBlue()]++;// BLUE
			}
		}

		for (int j = 0; j < 256; j++) {
			if (maxY < dadosHistograma[j]) {
				maxY = dadosHistograma[j];
			}
		}
	}

	public int[] getDadosHistograma() {
		return dadosHistograma;
	}

	public int getMaxY() {
		return maxY;
	}

}