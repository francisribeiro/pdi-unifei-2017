package pacotes_28309.control;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;

public class Histograma {
	private int[] dadosHistograma;
	private int maxY = 0;

	/**
	 * 
	 * @param Path of image to create Histogram of.
	 */
	public Histograma() {
		dadosHistograma = new int[256];
	}

	public BufferedImage escalaDeCinza(BufferedImage imagem) {
		// pegar largura e altura da imagem
		int width = imagem.getWidth();
		int height = imagem.getHeight();

		int media = 0;
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
				imagem.setRGB(i, j, color.getRGB());
			}
		}
		return imagem;
	}

	public void gerarHistograma(BufferedImage img) {

		for (int j = 0; j < 256; j++) 
			dadosHistograma[j] = 0;

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color c = new Color(img.getRGB(x, y));

				dadosHistograma[c.getBlue()]++;
			}
		}

		//maxY = 0;

		for (int j = 0; j < 256; j++) 
			if (maxY < dadosHistograma[j]) 
				maxY = dadosHistograma[j];
	}

	public int[] getcolourBins() {
		return dadosHistograma;
	}

	public int getMaxY() {
		return maxY;
	}

	/**
	 * Método para equalizar a imagem
	 * 
	 * @param imagem Imagem a ser filtrada.
	 * @return imagem.
	 * 
	 */
	public BufferedImage equalizar(BufferedImage original) {

		int red;
		int green;
		int blue;
		int alpha;
		int newPixel = 0;

		// Get the Lookup table for histogram equalization
		ArrayList<int[]> histLUT = histogramEqualizationLUT(original);

		BufferedImage histogramEQ = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

		for (int i = 0; i < original.getWidth(); i++) {
			for (int j = 0; j < original.getHeight(); j++) {

				// Get pixels by R, G, B
				alpha = new Color(original.getRGB(i, j)).getAlpha();
				red = new Color(original.getRGB(i, j)).getRed();
				green = new Color(original.getRGB(i, j)).getGreen();
				blue = new Color(original.getRGB(i, j)).getBlue();

				// Set new pixel values using the histogram lookup table
				red = histLUT.get(0)[red];
				green = histLUT.get(1)[green];
				blue = histLUT.get(2)[blue];

				// Return back to original format
				newPixel = corParaRGB(alpha, red, green, blue);

				// Write pixels into image
				histogramEQ.setRGB(i, j, newPixel);

			}
		}

		return histogramEQ;

	}

	// Get the histogram equalization lookup table for separate R, G, B channels
	private ArrayList<int[]> histogramEqualizationLUT(BufferedImage input) {

		// Get an image histogram - calculated values by R, G, B channels
		ArrayList<int[]> imageHist = imageHistogram(input);

		// Create the lookup table
		ArrayList<int[]> imageLUT = new ArrayList<int[]>();

		// Fill the lookup table
		int[] rhistogram = new int[256];
		int[] ghistogram = new int[256];
		int[] bhistogram = new int[256];

		for (int i = 0; i < rhistogram.length; i++)
			rhistogram[i] = 0;
		for (int i = 0; i < ghistogram.length; i++)
			ghistogram[i] = 0;
		for (int i = 0; i < bhistogram.length; i++)
			bhistogram[i] = 0;

		long sumr = 0;
		long sumg = 0;
		long sumb = 0;

		// Calculate the scale factor
		float scale_factor = (float) (255.0 / (input.getWidth() * input.getHeight()));

		for (int i = 0; i < rhistogram.length; i++) {
			sumr += imageHist.get(0)[i];
			int valr = (int) (sumr * scale_factor);
			if (valr > 255) {
				rhistogram[i] = 255;
			} else
				rhistogram[i] = valr;

			sumg += imageHist.get(1)[i];
			int valg = (int) (sumg * scale_factor);
			if (valg > 255) {
				ghistogram[i] = 255;
			} else
				ghistogram[i] = valg;

			sumb += imageHist.get(2)[i];
			int valb = (int) (sumb * scale_factor);
			if (valb > 255) {
				bhistogram[i] = 255;
			} else
				bhistogram[i] = valb;
		}

		imageLUT.add(rhistogram);
		imageLUT.add(ghistogram);
		imageLUT.add(bhistogram);

		return imageLUT;

	}

	// Return an ArrayList containing histogram values for separate R, G, B
	// channels
	public ArrayList<int[]> imageHistogram(BufferedImage input) {

		int[] rhistogram = new int[256];
		int[] ghistogram = new int[256];
		int[] bhistogram = new int[256];

		for (int i = 0; i < rhistogram.length; i++)
			rhistogram[i] = 0;
		for (int i = 0; i < ghistogram.length; i++)
			ghistogram[i] = 0;
		for (int i = 0; i < bhistogram.length; i++)
			bhistogram[i] = 0;

		for (int i = 0; i < input.getWidth(); i++) {
			for (int j = 0; j < input.getHeight(); j++) {

				int red = new Color(input.getRGB(i, j)).getRed();
				int green = new Color(input.getRGB(i, j)).getGreen();
				int blue = new Color(input.getRGB(i, j)).getBlue();

				// Increase the values of colors
				rhistogram[red]++;
				ghistogram[green]++;
				bhistogram[blue]++;

			}
		}

		ArrayList<int[]> hist = new ArrayList<int[]>();
		hist.add(rhistogram);
		hist.add(ghistogram);
		hist.add(bhistogram);

		return hist;

	}

	// Converte RGB e Alfa para o padrão 8 bit
	private int corParaRGB(int alpha, int red, int green, int blue) {
		int novoPixel = 0;
		novoPixel += alpha;
		novoPixel = novoPixel << 8;
		novoPixel += red;
		novoPixel = novoPixel << 8;
		novoPixel += green;
		novoPixel = novoPixel << 8;
		novoPixel += blue;
		return novoPixel;
	}

}