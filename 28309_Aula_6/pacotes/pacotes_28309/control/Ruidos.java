package pacotes_28309.control;

import java.awt.Color;
import java.awt.image.*;

public class Ruidos {

	public BufferedImage escalaDeCinza(BufferedImage imagem) {
		// pegar largura e altura da imagem
		int width = imagem.getWidth();
		int height = imagem.getHeight();
		int media = 0;
		BufferedImage nImg = new BufferedImage(imagem.getWidth(), imagem.getHeight(), imagem.getType());

		// laço para varrer a matriz de pixels da imagem
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) { // rgb recebe o valor RGB do
												// pixel em questão
				int rgb = imagem.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				// media dos valores do RGB
				// será o valor do pixel na nova imagem
				media = (r + g + b) / 3;

				// criar uma instância de Color
				Color color = new Color(media, media, media);
				// setar o valor do pixel com a nova cor
				nImg.setRGB(i, j, color.getRGB());
			}
		}

		return nImg;
	}

	public BufferedImage Sal(BufferedImage imagem) {
		double low, high, amount;
		BufferedImage nImg = new BufferedImage(imagem.getWidth(), imagem.getHeight(), imagem.getType());

		amount = 0.1;
		low = amount / 2;
		high = 1 - low;

		for (int i = 0; i < imagem.getWidth(); i++)
			for (int j = 0; j < imagem.getHeight(); j++)
				if (Math.random() >= high)
					nImg.setRGB(i, j, Color.WHITE.getRGB());
				else
					nImg.setRGB(i, j, imagem.getRGB(i, j));

		return nImg;
	}

	public BufferedImage Pimenta(BufferedImage imagem) {
		double low, amount;
		BufferedImage nImg = new BufferedImage(imagem.getWidth(), imagem.getHeight(), imagem.getType());

		amount = 0.1;
		low = amount / 2;

		for (int i = 0; i < imagem.getWidth(); i++)
			for (int j = 0; j < imagem.getHeight(); j++)
				if (Math.random() <= low)
					nImg.setRGB(i, j, Color.BLACK.getRGB());
				else
					nImg.setRGB(i, j, imagem.getRGB(i, j));

		return nImg	;
	}

}
