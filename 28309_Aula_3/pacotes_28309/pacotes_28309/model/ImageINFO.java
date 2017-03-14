package pacotes_28309.model;

public class ImageINFO {
	private int[] pixels;
	private int largura;
	private int altura;

	public ImageINFO(int[] pixels, int largura, int altura) {
		this.pixels = pixels;
		this.largura = largura;
		this.altura = altura;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

}
