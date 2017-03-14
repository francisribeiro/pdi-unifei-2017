package pacotes_28309.control;

import java.awt.image.*;
import java.io.IOException;
import pacotes_28309.model.*;

public class ImagemControl extends Transformacoes {

	private BufferedImage img;
	private int colunas, linhas;

	public ImagemControl(BufferedImage img) {
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
		int valorRGB;
		int largura = img.getWidth();
		int altura = img.getHeight();
		int[] dadosRGB = img.getRGB(0, 0, largura, altura, null, 0, largura);

		// Aloca os valores em um array
		for (int y = 0; y < altura; y++)
			for (int x = 0; x < largura; x++)
				valorRGB = dadosRGB[y * largura + x];

		return new ImageINFO(dadosRGB, largura, altura);
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
		BufferedImage imagemSaida = new BufferedImage(
				img.getLargura(), 
				img.getAltura(),
				BufferedImage.TYPE_INT_ARGB);
		
		imagemSaida.setRGB(0, 0, 
				img.getLargura(), 
				img.getAltura(), 
				img.getArrayDeDados(), 0, 
				img.getLargura());
		return imagemSaida;
	}

	public BufferedImage zoomIn() {
		BufferedImage temp = null;
		
		try {
			temp= imagemDeSaida(zoomIn(dadosdaImagem(img)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return temp;
	}

	public BufferedImage zoomOut() {
		BufferedImage temp = null;
		
		try {
			temp = imagemDeSaida(zoomOut(dadosdaImagem(img)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return temp;
	}

}
