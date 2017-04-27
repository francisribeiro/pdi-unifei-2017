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

	private static int limites(int valor, int indiceFinal) {
		if (valor < 0)
			return 0;
		if (valor < indiceFinal)
			return valor;

		return indiceFinal - 1;
	}

	private static BuferedImage[] dadosdaImagem(BufferedImage img) {

		// Dados da Imagem
		int largura = img.getWidth();
		int altura = img.getHeight();
		int[] dadosRGB = img.getRGB(0, 0, largura, altura, null, 0, largura);

		// Dados das cores da Imagem
		BuferedImage r = new BuferedImage(largura, altura);
		BuferedImage g = new BuferedImage(largura, altura);
		BuferedImage b = new BuferedImage(largura, altura);

		// Aloca os valores em um array
		for (int y = 0; y < altura; y++)
			for (int x = 0; x < largura; x++) {
				int valorRGB = dadosRGB[y * largura + x];
				r.set(x, y, (valorRGB >>> 16) & 0xFF);
				g.set(x, y, (valorRGB >>> 8) & 0xFF);
				b.set(x, y, valorRGB & 0xFF);
			}

		return new BuferedImage[] { r, g, b };
	}

	private BufferedImage imagemDeSaida(BuferedImage[] rgb) {
		BuferedImage r = rgb[0], g = rgb[1], b = rgb[2];
		BufferedImage imagemSaida = new BufferedImage(r.getLargura(), g.getAltura(), BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < r.getAltura(); y++)
			for (int x = 0; x < r.getLargura(); x++) {
				int red = limites(r.get(x, y), 256);
				int green = limites(g.get(x, y), 256);
				int blue = limites(b.get(x, y), 256);

				// Gera uma imagem RGB
				imagemSaida.setRGB(x, y, (red << 16) | (green << 8) | blue | -0x01000000);
			}

		return imagemSaida;
	}

	private static BuferedImage processoDeConvolucao(BuferedImage imagemEntrada, int[] template, int size,
			int divisor) {
		int imgLargura = imagemEntrada.getLargura();
		int imgAltura = imagemEntrada.getAltura();
		int tmplLargura = size;
		int tmplAltura = size;
		int raiolargura = tmplLargura >>> 1;
		int raioAltura = tmplAltura >>> 1;
		BuferedImage dadosImgSaida = new BuferedImage(imgLargura, imgAltura);

		// Faz a convolução multiplicando as matrizes
		for (int i = imgLargura - 1; i >= 0; i--) {
			for (int j = imgAltura - 1; j >= 0; j--) {
				double novoValor = 0.0;
				for (int kw = tmplLargura - 1; kw >= 0; kw--)
					for (int kh = tmplAltura - 1; kh >= 0; kh--)
						novoValor += template[kh * tmplAltura + kw] * imagemEntrada.get(
								limites(i - kw + raiolargura, imgLargura), limites(j - kh + raioAltura, imgAltura));

				dadosImgSaida.set(i, j, (int) Math.round(novoValor / divisor));
			}
		}

		return dadosImgSaida;
	}

	public BufferedImage media(BufferedImage img, int size) {
		int divisor = size * size;

		BuferedImage[] imgArray = dadosdaImagem(img);
		int[] kernel = new int[size * size];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				kernel[i * size + j] = 1;

		for (int i = 0; i < imgArray.length; i++)
			imgArray[i] = processoDeConvolucao(imgArray[i], kernel, size, divisor);

		return imagemDeSaida(imgArray);
	}

}
































class BuferedImage {
	private int[] arrayDeDados;
	private int largura;
	private int altura;

	public BuferedImage(int largura, int altura) {
		this(new int[largura * altura], largura, altura);
	}

	public BuferedImage(int[] arrayDeDados, int largura, int altura) {
		this.arrayDeDados = arrayDeDados;
		this.largura = largura;
		this.altura = altura;
	}

	public int get(int x, int y) {
		return arrayDeDados[y * largura + x];
	}

	public void set(int x, int y, int value) {
		arrayDeDados[y * largura + x] = value;
	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}

}
