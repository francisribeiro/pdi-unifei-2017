package pacotes_28309.control;

import java.awt.*;
import java.awt.image.*;

public class TransformacoesControl {

	private BufferedImage img;
	private int largura, altura;
	protected int angulo = 0;
	protected int escala = 0;
	protected int l = 0;
	protected int a = 0;

	public TransformacoesControl(BufferedImage img) {
		this.img = img;
		this.largura = img.getWidth();
		this.altura = img.getHeight();
	}

	/**
	 * Escala uma imagem usando o algoritmo de vizinhança 4 para preeenchimento
	 * de pixels
	 * 
	 * @param imagem de entrada para ser escalada
	 * @param mais zoomIn True, zoomOut False
	 * @return imagem escalada
	 */
	public BufferedImage escalar(Boolean mais) {

		// Verifica se aumenta ou diminui a escala
		if (mais)
			escala += 20;
		else
			escala -= 20;

		BufferedImage novaImagem = new BufferedImage(largura + escala, altura + escala, BufferedImage.TYPE_INT_ARGB);

		// +1 adicionado para um problema de arredondamento
		int proporcaoX = (int) ((largura << 16) / (largura + escala)) + 1;
		int proporcaoY = (int) ((altura << 16) / (altura + escala)) + 1;

		int x2, y2;

		// Faz a escala da imagem preenchendo a vizinhança
		for (int i = 0; i < (altura + escala); i++) {
			for (int j = 0; j < largura + escala; j++) {
				x2 = ((j * proporcaoX) >> 16);
				y2 = ((i * proporcaoY) >> 16);
				novaImagem.setRGB(j, i, img.getRGB(x2, y2));
			}
		}

		return novaImagem;
	}

	/**
	 * Rotaciona uma imagem com base em um ângulo
	 * 
	 * @param pic imagem a ser rotacionada.
	 * @param esquerda True, rotação à direita. False, rotação à esquerda
	 * @return imagem rotacionada
	 */
	public BufferedImage rotacionar(Boolean esquerda) {

		// Verifica para que lado rotacionar a imagem
		if (esquerda)
			angulo += 5;
		else
			angulo -= 5;

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

				// Plota o pixel(x, y) da mesma cor que o pixel (xx, yy), se não
				// for borda
				if (xx >= 0 && xx < largura && yy >= 0 && yy < altura)
					novaImagem.setRGB(x, y, img.getRGB(xx, yy));
			}
		}

		return novaImagem;
	}

	/**
	 * Inverte os pixels das imagens, fazendo o espelhamento.
	 * 
	 * @return imagem espelhada
	 */
	public BufferedImage espelhar() {
		BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

		// Cria a imagem espelhada pixel a pixel
		for (int y = 0; y < altura; y++) {
			for (int lx = 0, rx = largura - 1; lx < largura; lx++, rx--) {
				// Pega o valor do pixel e seta o pixel espelhado na imagem
				novaImagem.setRGB(rx, y, img.getRGB(lx, y));
			}
		}

		img = novaImagem;

		return novaImagem;
	}

	/**
	 * Translada uma imagem com base nos parametros recebidos.
	 * 
	 * @param xx distância em x para movimentar
	 * @param yy distância em y para movimentar
	 * @return imagem transladada
	 */
	public BufferedImage transladar(int xx, int yy) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int cgx = (int) ((width - largura) / 2);
		int cgy = (int) ((height - altura) / 2);

		// Incrementos de altura e largura
		l += xx;
		a += yy;

		BufferedImage novaImagem = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < largura; x++) {
			for (int y = 0; y < altura; y++) {
				// Pega o valor do pixel e seta o pixel  na imagem
				novaImagem.setRGB(cgx + x + l, cgy + y + a, img.getRGB(x, y));
			}
		}

		return novaImagem;
	}

}
