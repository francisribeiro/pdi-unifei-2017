package pacotes_28309.control;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

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

	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
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

	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////

	public static class ArrayData {
		public final int[] dataArray;
		public final int width;
		public final int height;

		public ArrayData(int width, int height) {
			this(new int[width * height], width, height);
		}

		public ArrayData(int[] dataArray, int width, int height) {
			this.dataArray = dataArray;
			this.width = width;
			this.height = height;
		}

		public int get(int x, int y) {
			return dataArray[y * width + x];
		}

		public void set(int x, int y, int value) {
			dataArray[y * width + x] = value;
		}
	}

	private static int bound(int value, int endIndex) {
		if (value < 0)
			return 0;
		if (value < endIndex)
			return value;
		return endIndex - 1;
	}

	public static ArrayData convolute(ArrayData inputData, ArrayData kernel, int kernelDivisor) {
		int inputWidth = inputData.width;
		int inputHeight = inputData.height;
		int kernelWidth = kernel.width;
		int kernelHeight = kernel.height;
		if ((kernelWidth <= 0) || ((kernelWidth & 1) != 1))
			throw new IllegalArgumentException("Kernel must have odd width");
		if ((kernelHeight <= 0) || ((kernelHeight & 1) != 1))
			throw new IllegalArgumentException("Kernel must have odd height");
		int kernelWidthRadius = kernelWidth >>> 1;
		int kernelHeightRadius = kernelHeight >>> 1;

		ArrayData outputData = new ArrayData(inputWidth, inputHeight);
		for (int i = inputWidth - 1; i >= 0; i--) {
			for (int j = inputHeight - 1; j >= 0; j--) {
				double newValue = 0.0;
				for (int kw = kernelWidth - 1; kw >= 0; kw--)
					for (int kh = kernelHeight - 1; kh >= 0; kh--)
						newValue += kernel.get(kw, kh) * inputData.get(bound(i + kw - kernelWidthRadius, inputWidth),
								bound(j + kh - kernelHeightRadius, inputHeight));
				outputData.set(i, j, (int) Math.round(newValue / kernelDivisor));
			}
		}
		return outputData;
	}

	public static ArrayData[] getArrayDatasFromImage(BufferedImage inputImage) {
		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		int[] rgbData = inputImage.getRGB(0, 0, width, height, null, 0, width);
		ArrayData reds = new ArrayData(width, height);
		ArrayData greens = new ArrayData(width, height);
		ArrayData blues = new ArrayData(width, height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int rgbValue = rgbData[y * width + x];
				reds.set(x, y, (rgbValue >>> 16) & 0xFF);
				greens.set(x, y, (rgbValue >>> 8) & 0xFF);
				blues.set(x, y, rgbValue & 0xFF);
			}
		}
		return new ArrayData[] { reds, greens, blues };
	}

	public static BufferedImage writeOutputImage(ArrayData[] redGreenBlue) {
		ArrayData reds = redGreenBlue[0];
		ArrayData greens = redGreenBlue[1];
		ArrayData blues = redGreenBlue[2];
		BufferedImage outputImage = new BufferedImage(reds.width, reds.height, BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < reds.height; y++) {
			for (int x = 0; x < reds.width; x++) {
				int red = bound(reds.get(x, y), 256);
				int green = bound(greens.get(x, y), 256);
				int blue = bound(blues.get(x, y), 256);
				outputImage.setRGB(x, y, (red << 16) | (green << 8) | blue | -0x01000000);
			}
		}
		return outputImage;
	}

	public BufferedImage media(BufferedImage img, int size) {
		int kernelWidth = size;
		int kernelHeight = size;
		int kernelDivisor = size * size;

		int y = size;
		
		ArrayData kernel = new ArrayData(kernelWidth, kernelHeight);
		
		for (int i = 0; i < kernelHeight; i++) {
			for (int j = 0; j < kernelWidth; j++) {
				kernel.set(j, i, 1);
			}
		}

		ArrayData[] dataArrays = getArrayDatasFromImage(img);
		for (int i = 0; i < dataArrays.length; i++)
			dataArrays[i] = convolute(dataArrays[i], kernel, kernelDivisor);
		
		return writeOutputImage(dataArrays);
	}

}
