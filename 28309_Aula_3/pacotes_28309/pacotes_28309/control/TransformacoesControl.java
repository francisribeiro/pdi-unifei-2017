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

	/**
	 * Bilinear resize ARGB image. pixels is an array of size w * h. Target
	 * dimension is w2 * h2. w2 * h2 cannot be zero.
	 * 
	 * @param pixels Image pixels.
	 * @param w Image width.
	 * @param h Image height.
	 * @param w2 New width.
	 * @param h2 New height.
	 * @return New array with size w2 * h2.
	 */
	public int[] resizeBilinear(int[] pixels, int w, int h, int w2, int h2) {
		int[] temp = new int[w2 * h2];
		int a, b, c, d, x, y, index;
		float x_ratio = ((float) (w - 1)) / w2;
		float y_ratio = ((float) (h - 1)) / h2;
		float x_diff, y_diff, blue, red, green;
		int offset = 0;
		for (int i = 0; i < h2; i++) {
			for (int j = 0; j < w2; j++) {
				x = (int) (x_ratio * j);
				y = (int) (y_ratio * i);
				x_diff = (x_ratio * j) - x;
				y_diff = (y_ratio * i) - y;
				index = (y * w + x);
				a = pixels[index];
				b = pixels[index + 1];
				c = pixels[index + w];
				d = pixels[index + w + 1];

				// blue element
				// Yb = Ab(1-w)(1-h) + Bb(w)(1-h) + Cb(h)(1-w) + Db(wh)
				blue = (a & 0xff) * (1 - x_diff) * (1 - y_diff) + (b & 0xff) * (x_diff) * (1 - y_diff)
						+ (c & 0xff) * (y_diff) * (1 - x_diff) + (d & 0xff) * (x_diff * y_diff);

				// green element
				// Yg = Ag(1-w)(1-h) + Bg(w)(1-h) + Cg(h)(1-w) + Dg(wh)
				green = ((a >> 8) & 0xff) * (1 - x_diff) * (1 - y_diff) + ((b >> 8) & 0xff) * (x_diff) * (1 - y_diff)
						+ ((c >> 8) & 0xff) * (y_diff) * (1 - x_diff) + ((d >> 8) & 0xff) * (x_diff * y_diff);

				// red element
				// Yr = Ar(1-w)(1-h) + Br(w)(1-h) + Cr(h)(1-w) + Dr(wh)
				red = ((a >> 16) & 0xff) * (1 - x_diff) * (1 - y_diff) + ((b >> 16) & 0xff) * (x_diff) * (1 - y_diff)
						+ ((c >> 16) & 0xff) * (y_diff) * (1 - x_diff) + ((d >> 16) & 0xff) * (x_diff * y_diff);

				temp[offset++] = 0xff000000 | // hardcode alpha
						((((int) red) << 16) & 0xff0000) | ((((int) green) << 8) & 0xff00) | ((int) blue);
			}
		}
		return temp;
	}

	
	public int[] resizePixels(int[] pixels,int w1,int h1,int w2,int h2) {
	    int[] temp = new int[w2*h2] ;
	    // EDIT: added +1 to account for an early rounding problem
	    int x_ratio = (int)((w1<<16)/w2) +1;
	    int y_ratio = (int)((h1<<16)/h2) +1;
	    //int x_ratio = (int)((w1<<16)/w2) ;
	    //int y_ratio = (int)((h1<<16)/h2) ;
	    int x2, y2 ;
	    for (int i=0;i<h2;i++) {
	        for (int j=0;j<w2;j++) {
	            x2 = ((j*x_ratio)>>16) ;
	            y2 = ((i*y_ratio)>>16) ;
	            temp[(i*w2)+j] = pixels[(y2*w1)+x2] ;
	        }                
	    }                
	    return temp ;
	}
	
	public BufferedImage escalar(BufferedImage imagem, boolean mais) throws IOException {
		ImageINFO data = dadosdaImagem(imagem);
		
		if (mais)
			escala += 20;
		else
			escala -=20;

		int[] pixels = resizePixels(data.getPixels(), data.getLargura(), data.getAltura(), data.getAltura() + escala,
				data.getLargura() + escala);

		data.setAltura(data.getAltura() + escala);
		data.setLargura(data.getLargura() + escala);
		data.setPixels(pixels);

		return imagemDeSaida(data);

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
