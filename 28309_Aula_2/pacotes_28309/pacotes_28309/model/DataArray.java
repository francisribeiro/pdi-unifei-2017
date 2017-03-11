package pacotes_28309.model;

public class DataArray {
	private int[] dataArray;
	private int width;
	private int height;

	public DataArray(int width, int height) {
		this(new int[width * height], width, height);
	}

	public DataArray(int[] dataArray, int width, int height) {
		this.dataArray = dataArray;
		this.width = width;
		this.height = height;
	}

	public int get(int x, int y) {
		return dataArray[y * width + x];
	}

	public void set(int x, int y, int value) {
		dataArray[y * width + x] = value;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
