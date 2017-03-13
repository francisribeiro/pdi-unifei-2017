package pacotes_28309.control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.*;
import pacotes_28309.model.*;

public class ImagemControl {

	private Graphics draw;
	private BufferedImage img;
	private int colunas, linhas;
	private Pixel[][] imagem;

	public ImagemControl(BufferedImage img, Graphics draw) {
		this.draw = draw;
		this.img = img;
		this.colunas = img.getWidth();
		this.linhas = img.getHeight();
		alocarImagem();
	}

	private void plotaPixel(double x, double y) {
		draw.drawLine((int) x, (int) y, (int) x, (int) y);
	}

	private void alocarImagem() {
		imagem = new Pixel[linhas][colunas];


		for (int col = 0; col < colunas; col++) {
			for (int lin = 0; lin < linhas; lin++) {

				int clr = img.getRGB(lin, col);

				imagem[lin][col] = new Pixel(new Point(lin, col), new Color(
						(clr & 0x00ff0000) >> 16, 
						(clr & 0x0000ff00) >> 8, 
						(clr & 0x0000ff00) >> 8));
			}
		}
	}

	public BufferedImage plotarImagem() {
		//int[] pixels = img.getRGB(0, 0, colunas, linhas, null, 0, colunas);

		for (int col = 0; col < colunas; col++) {
			for (int lin = 0; lin < linhas; lin++) {
				//pixels[colunas * lin + col] = imagem[col][lin].getCor().getRGB();
				
				draw.setColor(imagem[lin][col].getCor());
				
				plotaPixel(
						imagem[lin][col].getPonto().getX(), 
						imagem[lin][col].getPonto().getY());
			}
		}
		
		//img.setRGB(0, 0, colunas, linhas, pixels, 0, colunas);

		return img;
	}

}
