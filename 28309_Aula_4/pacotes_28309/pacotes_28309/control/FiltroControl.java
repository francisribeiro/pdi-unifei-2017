package pacotes_28309.control;

import java.awt.*;
import java.awt.image.*;

public class FiltroControl {

	public static BufferedImage  threshold(BufferedImage image, int limiar) {
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
}