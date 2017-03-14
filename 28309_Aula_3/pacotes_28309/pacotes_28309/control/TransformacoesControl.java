package pacotes_28309.control;

import java.awt.image.*;
import java.io.IOException;
import pacotes_28309.model.*;

public class TransformacoesControl {

	private BufferedImage img;
	private int colunas, linhas;
	protected int angulo = 0;
	protected int escala = 0;

	public TransformacoesControl(BufferedImage img) {
		this.img = img;
		this.colunas = img.getWidth();
		this.linhas = img.getHeight();
	}

	/**
	 * Adiciona as informações das imagens em um array de Dados.
	 * 
	 * @param img imagem pare extrair dados
	 * @return dados da imagem
	 * @throws IOException
	 */
	private static ImageINFO dadosdaImagem(BufferedImage img) throws IOException {

		// Dados da Imagem
		int largura = img.getWidth();
		int altura = img.getHeight();
		int[] pixels = img.getRGB(0, 0, largura, altura, null, 0, largura);

		for (int x = 0; x < largura; x++)
			for (int y = 0; y < altura; y++)
				pixels[largura * y + x] = img.getRGB(x, y);

		return new ImageINFO(pixels, largura, altura);
	}

	/**
	 * Gera uma BufferedImage contento a imagem de saída para ser impressa na
	 * tela.
	 * 
	 * @param img array com os valores da imagem
	 * @return imagem de saída
	 * @throws IOException
	 */
	private BufferedImage imagemDeSaida(ImageINFO img) throws IOException {
		BufferedImage imagemSaida = new BufferedImage(img.getLargura(), img.getAltura(), BufferedImage.TYPE_INT_ARGB);
		imagemSaida.setRGB(0, 0, img.getLargura(), img.getAltura(), img.getPixels(), 0, img.getLargura());
		return imagemSaida;
	}

		
	public BufferedImage resizePixels2(BufferedImage imagem, Boolean mais) {
		
		if (mais)
			escala += 20;
		else
			escala -= 20;
		
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		BufferedImage temp = new BufferedImage(largura+escala, altura+escala, BufferedImage.TYPE_INT_ARGB);
		
		// EDIT: added +1 to account for an early rounding problem
		int x_ratio = (int) ((largura << 16) / (largura+escala)) + 1;
		int y_ratio = (int) ((altura << 16) / (altura+escala)) + 1;

		int x2, y2;
		
		for (int i = 0; i < (altura+escala); i++) {
			for (int j = 0; j < largura+escala; j++) {
				x2 = ((j * x_ratio) >> 16);
				y2 = ((i * y_ratio) >> 16);
				temp.setRGB(j,i , imagem.getRGB(x2, y2));
				//temp[(i * largura+escala) + j] = pixels[(y2 * largura) + x2];
			}
		}
		
		return temp;
	}

	/**
	 * Rotaciona uma imagem préviamente aberta.
	 * 
	 * @param pic imagem a ser rotacionada.
	 * @param esquerda True, rotação à direita. False, rotação à esquerda
	 * @return imagem rotacionada
	 */
	public BufferedImage rotacao(BufferedImage imagem, Boolean esquerda) {

		// Verifica para que lado rotacionar a imagem
		if (esquerda)
			angulo += 5;
		else
			angulo -= 5;

		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		double angle = Math.toRadians(angulo);
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double x0 = 0.5 * (largura - 1); // Ponto para rotação,
		double y0 = 0.5 * (altura - 1); // no centro geométrico da imagem
		BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < largura; x++) {
			for (int y = 0; y < altura; y++) {
				double a = x - x0;
				double b = y - y0;
				int xx = (int) (+a * cos - b * sin + x0);
				int yy = (int) (+a * sin + b * cos + y0);

				// Plota o pixel(x, y) da mesma cor que o pixel (xx, yy),
				// se não for borda
				if (xx >= 0 && xx < largura && yy >= 0 && yy < altura) {
					novaImagem.setRGB(x, y, imagem.getRGB(xx, yy));
				}
			}
		}

		return novaImagem;
	}

}
