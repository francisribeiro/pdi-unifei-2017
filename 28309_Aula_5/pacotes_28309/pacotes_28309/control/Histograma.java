package pacotes_28309.control;

import java.awt.*;
import java.awt.image.*;

public class Histograma {

	private int[] dadosHistograma;
	private int[] dadosHistogramaEqualizado;
	private int maxYH = 0, maxYHE = 0;
	private BufferedImage imagem;

	public Histograma(BufferedImage imagem) {
		this.imagem = imagem;
		histograma(escalaDeCinza());
	}

	// método de aplicação do filtro escala de cinza
	// recebe como parâmetro uma imagem
	private BufferedImage escalaDeCinza() {
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

	private void histograma(BufferedImage img) {
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
			if (maxYH < dadosHistograma[j]) {
				maxYH = dadosHistograma[j];
			}
		}
	}

	public BufferedImage equalize() {
		BufferedImage nImg = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster wr = escalaDeCinza().getRaster();
		WritableRaster er = nImg.getRaster();
		int totpix = wr.getWidth() * wr.getHeight();

		dadosHistogramaEqualizado = new int[256];
		dadosHistogramaEqualizado[0] = dadosHistograma[0];

		for (int i = 1; i < 256; i++)
			dadosHistogramaEqualizado[i] = dadosHistogramaEqualizado[i - 1] + dadosHistograma[i];

		float[] arr = new float[256];
		for (int i = 0; i < 256; i++)
			arr[i] = (float) ((dadosHistogramaEqualizado[i] * 255.0) / (float) totpix);

		for (int x = 0; x < wr.getWidth(); x++)
			for (int y = 0; y < wr.getHeight(); y++) {
				int nVal = (int) arr[wr.getSample(x, y, 0)];
				er.setSample(x, y, 0, nVal);
			}

		for (int j = 0; j < 256; j++)
			if (maxYHE < dadosHistogramaEqualizado[j])
				maxYHE = dadosHistogramaEqualizado[j];

		nImg.setData(er);

		return nImg;
	}

	public int[] getDadosHistograma() {
		return dadosHistograma;
	}

	public int[] getDadosHistogramaEqualizado() {
		return dadosHistogramaEqualizado;
	}

	public int getMaxYH() {
		return maxYH;
	}

	public int getMaxYHE() {
		return maxYHE;
	}

}