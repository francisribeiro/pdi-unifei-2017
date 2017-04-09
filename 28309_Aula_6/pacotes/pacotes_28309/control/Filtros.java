package pacotes_28309.control;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class Filtros {
	
	public BufferedImage mediana(BufferedImage img) {
		BufferedImage nImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		Color[] pixel = new Color[8];
		int[] R = new int[8];
		int[] B = new int[8];
		int[] G = new int[8];

		for (int i = 1; i < img.getWidth() - 1; i++)
			for (int j = 1; j < img.getHeight() - 1; j++) {
				pixel[0] = new Color(img.getRGB(i - 1, j - 1));
				pixel[1] = new Color(img.getRGB(i - 1, j));
				pixel[2] = new Color(img.getRGB(i - 1, j + 1));
				pixel[3] = new Color(img.getRGB(i, j + 1));
				pixel[4] = new Color(img.getRGB(i + 1, j + 1));
				pixel[5] = new Color(img.getRGB(i + 1, j));
				pixel[6] = new Color(img.getRGB(i + 1, j - 1));
				pixel[7] = new Color(img.getRGB(i, j - 1));

				for (int k = 0; k < 8; k++) {
					R[k] = pixel[k].getRed();
					B[k] = pixel[k].getBlue();
					G[k] = pixel[k].getGreen();
				}

				Arrays.sort(R);
				Arrays.sort(G);
				Arrays.sort(B);

				nImg.setRGB(i, j,
						new Color((int) ((R[3] + R[4]) / 2), (int) ((G[3] + G[4]) / 2), (int) ((B[3] + B[4]) / 2))
								.getRGB());
			}

		return nImg;
	}

	public BufferedImage pseudoMediana(BufferedImage img) {
		BufferedImage nImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		Color[] pixel = new Color[8];
		int[] R = new int[8];
		int[] B = new int[8];
		int[] G = new int[8];

		int red, green, blue;

		for (int i = 1; i < img.getWidth() - 1; i++)
			for (int j = 1; j < img.getHeight() - 1; j++) {
				pixel[0] = new Color(img.getRGB(i - 1, j - 1));
				pixel[1] = new Color(img.getRGB(i - 1, j));
				pixel[2] = new Color(img.getRGB(i - 1, j + 1));
				pixel[3] = new Color(img.getRGB(i, j + 1));
				pixel[4] = new Color(img.getRGB(i + 1, j + 1));
				pixel[5] = new Color(img.getRGB(i + 1, j));
				pixel[6] = new Color(img.getRGB(i + 1, j - 1));
				pixel[7] = new Color(img.getRGB(i, j - 1));

				for (int k = 0; k < 8; k++) {
					R[k] = pixel[k].getRed();
					B[k] = pixel[k].getBlue();
					G[k] = pixel[k].getGreen();
				}

				// Red
				int[] res = maxMin(R);
				int[] res2 = minMax(R);
				red = (max(res, 0, res.length) + min(res2, 0, res2.length)) / 2;

				// Green
				res = maxMin(G);
				res2 = minMax(G);
				green = (max(res, 0, res.length) + min(res2, 0, res2.length)) / 2;

				// Blue
				res = maxMin(B);
				res2 = minMax(B);
				blue = (max(res, 0, res.length) + min(res2, 0, res2.length)) / 2;

				nImg.setRGB(i, j, new Color(red, green, blue).getRGB());
			}

		return nImg;
	}

	private int min(int[] array, int inicio, int fim) {
		int min = 256;

		for (int i = inicio; i < fim; i++)
			if (array[i] < min)
				min = array[i];

		return min;
	}

	private int max(int[] array, int inicio, int fim) {
		int max = 0;

		for (int i = inicio; i < fim; i++)
			if (array[i] > max)
				max = array[i];

		return max;
	}

	private int[] maxMin(int[] array) {
		int m = (int) (array.length + 1) / 2;
		int[] maxMinArray = new int[m];

		for (int i = 0; i < m; i++)
			maxMinArray[i] = min(array, i, m + i);

		return maxMinArray;
	}

	private int[] minMax(int[] array) {
		int m = (int) (array.length + 1) / 2;
		int[] minMaxArray = new int[m];

		for (int i = 0; i < m; i++)
			minMaxArray[i] = max(array, i, m + i);

		return minMaxArray;
	}
}
