package pacotes_28309.control;

import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import pacotes_28309.model.*;
import pacotes_28309.view.*;

public class ConvolucaoControl implements ActionListener {

	private TelaConvolucao appConvolucao;
	private BufferedImage imagem, template;
	private GridView imgConvoluida;

	public ConvolucaoControl(BufferedImage imagem, BufferedImage template) {
		this.imagem = imagem;
		this.template = template;
		appConvolucao = new TelaConvolucao(this);

		// Fecha a Tela caso perca o foco
		appConvolucao.addWindowListener(new WindowAdapter() {
			public void windowDeactivated(WindowEvent e) {
				appConvolucao.dispose();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Convoluir")) {
			try {
				imgConvoluidaGrid(imagem.getHeight(), imagem.getWidth(), convoluir(imagem));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Gera um novo grid de imagem convoluida.
	 * 
	 * @param lin altura do grid
	 * @param col largura do grid
	 */
	private void imgConvoluidaGrid(int lin, int col, BufferedImage img) {
		imgConvoluida = new GridView();
		appConvolucao.pnlImgConvolucionada.removeAll();
		appConvolucao.pnlImgConvolucionada.repaint();
		appConvolucao.pnlImgConvolucionada.revalidate();
		appConvolucao.addGrid(appConvolucao.pnlImgConvolucionada, imgConvoluida.gerarGrid(lin, col, true, img));
	}

	/**
	 * 
	 * @param valor
	 * @param indiceFinal
	 * @return
	 */
	private static int fronteira(int valor, int indiceFinal) {
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
	private static Dados[] getDadosdaImagem(BufferedImage img) throws IOException {

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
		BufferedImage outputImage = new BufferedImage(r.getWidth(), g.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < r.getHeight(); y++)
			for (int x = 0; x < r.getWidth(); x++) {
				int red = fronteira(r.get(x, y), 256);
				int green = fronteira(g.get(x, y), 256);
				int blue = fronteira(b.get(x, y), 256);

				// Gera uma imagem RGB
				outputImage.setRGB(x, y, (red << 16) | (green << 8) | blue | -0x01000000);
			}

		return outputImage;
	}

	private static Dados processoDeConvolucao(Dados imagemEntrada, Dados template, int kernelDivisor) {
		int imgLargura = imagemEntrada.getWidth();
		int imgAltura = imagemEntrada.getHeight();
		int tmplLargura = template.getWidth();
		int tmplAltura = template.getHeight();

		if ((tmplLargura <= 0) || ((tmplLargura & 1) != 1))
			throw new IllegalArgumentException("Kernel must have odd width");
		if ((tmplAltura <= 0) || ((tmplAltura & 1) != 1))
			throw new IllegalArgumentException("Kernel must have odd height");

		int kernelWidthRadius = tmplLargura >>> 1;
		int kernelHeightRadius = tmplAltura >>> 1;

		Dados dadosImgSaida = new Dados(imgLargura, imgAltura);

		for (int i = imgLargura - 1; i >= 0; i--) {
			for (int j = imgAltura - 1; j >= 0; j--) {
				double newValue = 0.0;
				for (int kw = tmplLargura - 1; kw >= 0; kw--)
					for (int kh = tmplAltura - 1; kh >= 0; kh--)
						newValue += template.get(kw, kh)
								* imagemEntrada.get(fronteira(i + kw - kernelWidthRadius, imgLargura),
										fronteira(j + kh - kernelHeightRadius, imgAltura));
				dadosImgSaida.set(i, j, (int) Math.round(newValue / kernelDivisor));
			}
		}

		return dadosImgSaida;
	}

	private BufferedImage convoluir(BufferedImage img) throws IOException {
		int kernel[][] = { 
							{ 1, 4, 7, 4, 1 }, 
							{ 4, 16, 26, 16, 4 }, 
							{ 7, 26, 41, 26, 7 }, 
							{ 4, 16, 26, 16, 4 },
							{ 1, 4, 7, 4, 1 } };
		
		int kernelWidth = 5;
		int kernelHeight = 5;
		int kernelDivisor = 256;
		
		System.out.println("Kernel size: " + kernelWidth + "x" + kernelHeight + ", divisor=" + kernelDivisor);
		int y = 5;
		Dados template = new Dados(kernelWidth, kernelHeight);
		
		for (int i = 0; i < kernelHeight; i++) {
			System.out.print("[");
			for (int j = 0; j < kernelWidth; j++) {
				template.set(j, i, kernel[j][i]);
				System.out.print(" " + kernel[j][i] + " ");
			}
			System.out.println("]");
		}

		Dados[] dataArrays = getDadosdaImagem(img);
		
		for (int i = 0; i < dataArrays.length; i++)
			dataArrays[i] = processoDeConvolucao(dataArrays[i], template, kernelDivisor);
		
		return imagemDeSaida(dataArrays);
	}

}
