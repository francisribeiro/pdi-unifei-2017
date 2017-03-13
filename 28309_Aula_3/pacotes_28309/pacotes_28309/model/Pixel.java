package pacotes_28309.model;

import java.awt.*;

public class Pixel {
	private Point ponto;
	private Color cor;

	public Pixel(Point ponto, Color cor) {
		super();
		this.ponto = ponto;
		this.cor = cor;
	}

	public Point getPonto() {
		return ponto;
	}

	public void setPonto(Point ponto) {
		this.ponto = ponto;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}
}
