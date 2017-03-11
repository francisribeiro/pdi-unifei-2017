package pacotes_28309.control;

import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import pacotes_28309.model.*;
import pacotes_28309.view.*;

public class ConvolucaoControl {

	private TelaConvolucao appConvolucao;
	private BufferedImage imagem, template;
	private GridPanel imgConvoluida;

	public ConvolucaoControl(BufferedImage imagem, BufferedImage template) {
		this.imagem = imagem;
		this.template = template;
		this.appConvolucao = new TelaConvolucao(this);
		

		//Faz a convolução da imagem
		try {
			imgConvoluidaGrid(imagem.getHeight(), imagem.getWidth(), convoluir(imagem, template));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Fecha a Tela caso perca o foco
		appConvolucao.addWindowListener(new WindowAdapter() {
			public void windowDeactivated(WindowEvent e) {
				appConvolucao.dispose();
			}
		});
	}

	/**
	 * Gera um novo grid de imagem convoluida.
	 * 
	 * @param lin altura do grid
	 * @param col largura do grid
	 */
	private void imgConvoluidaGrid(int lin, int col, BufferedImage img) {
		imgConvoluida = new GridPanel();
		appConvolucao.pnlImgConvolucionada.removeAll();
		appConvolucao.pnlImgConvolucionada.repaint();
		appConvolucao.pnlImgConvolucionada.revalidate();
		appConvolucao.addGrid(appConvolucao.pnlImgConvolucionada, imgConvoluida.gerarGrid(lin, col, true, img));
	}

	/**
	 * Não permite que uma valor RGB seja extrapolado.
	 * 
	 * @param valor
	 * @param indiceFinal
	 * @return valor RGB
	 */
	private static int limites(int valor, int indiceFinal) {
		if (valor < 0)
			return 0;
		if (valor < indiceFinal)
			return valor;

		return indiceFinal - 1;
	}

	/**
	 * Adiciona as informações das imagens em um array de Dados.
	 * 
	 * @param img imagem pare extrair dados
	 * @return dados das cores da imagem
	 * @throws IOException
	 */
	private static Dados[] dadosdaImagem(BufferedImage img) throws IOException {

		// Dados da Imagem
		int largura = img.getWidth();
		int altura = img.getHeight();
		int[] dadosRGB = img.getRGB(0, 0, largura, altura, null, 0, largura);

		// Dados das cores da Imagem
		Dados r = new Dados(largura, altura);
		Dados g = new Dados(largura, altura);
		Dados b = new Dados(largura, altura);

		// Aloca os valores em um array
		for (int y = 0; y < altura; y++)
			for (int x = 0; x < largura; x++) {
				int valorRGB = dadosRGB[y * largura + x];
				r.set(x, y, (valorRGB >>> 16) & 0xFF);
				g.set(x, y, (valorRGB >>> 8) & 0xFF);
				b.set(x, y, valorRGB & 0xFF);
			}

		return new Dados[] { r, g, b };
	}

	/**
	 * Gera uma BufferedImage contento a imagem de saída para ser impressa na
	 * tela.
	 * 
	 * @param rgb array com os valores da imagem
	 * @return imagem de saída
	 * @throws IOException
	 */
	private BufferedImage imagemDeSaida(Dados[] rgb) throws IOException {
		Dados r = rgb[0], g = rgb[1], b = rgb[2];
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

	/**
	 * Método responsável pelo processo de convolução de uma imagem.
	 * 
	 * @param imagemEntrada imagem a ser convolucionada
	 * @param template que será aplicado na imagem como filtro
	 * @param kernelDivisor divisor do filtro
	 * @return dados da imagem de saída
	 */
	private static Dados processoDeConvolucao(Dados imagemEntrada, Dados template, int divisor) {
		int imgLargura = imagemEntrada.getLargura();
		int imgAltura = imagemEntrada.getAltura();
		int tmplLargura = template.getLargura();
		int tmplAltura = template.getAltura();
		int raiolargura = tmplLargura >>> 1;
		int raioAltura = tmplAltura >>> 1;
		Dados dadosImgSaida = new Dados(imgLargura, imgAltura);

		// Faz a convolução multiplicando as matrizes
		for (int i = imgLargura - 1; i >= 0; i--) {
			for (int j = imgAltura - 1; j >= 0; j--) {
				double newValue = 0.0;
				for (int kw = tmplLargura - 1; kw >= 0; kw--)
					for (int kh = tmplAltura - 1; kh >= 0; kh--)
						newValue += template.get(kw, kh) * imagemEntrada.get(limites(i + kw - raiolargura, imgLargura),
								limites(j + kh - raioAltura, imgAltura));

				dadosImgSaida.set(i, j, (int) Math.round(newValue / divisor));
			}
		}

		return dadosImgSaida;
	}

	/**
	 * Reune todas as informações sobre a imagem e o template e faz o processo
	 * de convolução.
	 * 
	 * @param img imagem de entrada
	 * @param template template que será aplicado na imagem
	 * @return imagem comvoluida
	 * @throws IOException
	 */
	private BufferedImage convoluir(BufferedImage img, BufferedImage tmpl) throws IOException {
		int divisor = 256;
		Dados[] imgArray = dadosdaImagem(img);
		Dados[] templateArray = dadosdaImagem(tmpl);

		for (int i = 0; i < imgArray.length; i++)
			imgArray[i] = processoDeConvolucao(imgArray[i], templateArray[i], divisor);

		return imagemDeSaida(imgArray);
	}

}
