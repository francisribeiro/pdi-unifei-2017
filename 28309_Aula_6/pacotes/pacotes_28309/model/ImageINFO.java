package pacotes_28309.model;

public class ImageINFO {
	private int[] arrayDeDados;
	private int largura;
	private int altura;

	public ImageINFO(int largura, int altura) {
		this(new int[largura * altura], largura, altura);
	}

	public ImageINFO(int[] arrayDeDados, int largura, int altura) {
		this.arrayDeDados = arrayDeDados;
		this.largura = largura;
		this.altura = altura;
	}

	public int get(int x, int y) {
		return arrayDeDados[y * largura + x];
	}

	public void set(int x, int y, int value) {
		arrayDeDados[y * largura + x] = value;
	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}

}
