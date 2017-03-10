package pacotes_28309.model;

import java.awt.Color;

public class Pixel {
	private Color cor;

	public Pixel(int largura, Color cor) {
		this.cor = cor;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

}
