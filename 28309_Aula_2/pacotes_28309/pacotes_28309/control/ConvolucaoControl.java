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
		
		//Fecha a Tela caso perca o foco
		appConvolucao.addWindowListener(new WindowAdapter(){
			public void windowDeactivated(WindowEvent e){
				appConvolucao.dispose();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Convoluir")) {
			imgConvoluidaGrid(imagem.getHeight(), imagem.getWidth());
		}
	}

	/**
	 * Gera um novo grid de imagem convoluida.
	 * 
	 * @param lin altura do grid
	 * @param col largura do grid
	 */
	private void imgConvoluidaGrid(int lin, int col) {
		imgConvoluida = new GridView();
		appConvolucao.pnlImgConvolucionada.removeAll();
		appConvolucao.pnlImgConvolucionada.repaint();
		appConvolucao.pnlImgConvolucionada.revalidate();
		appConvolucao.addGrid(appConvolucao.pnlImgConvolucionada, imgConvoluida.gerarGrid(lin, col, false));
	}
	
	/**
	 * Adiciona as informações das imagens em um array de Dados.
	 * 
	 * @param img imagem pare extrair dados
	 * @return dados das cores da imagem
	 * @throws IOException
	 */
	public static Dados[] getDadosdaImagem(BufferedImage img) throws IOException {

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
	
	

}
