package pacotes_28309.control;

import java.awt.*;
import java.awt.image.*;

public class Histograma {
	public final int RED = 0;
	public final int GREEN = 1;
	public final int BLUE = 2;
	private int[][] caixaDeCores;
	private int maxY = 0;

	public Histograma() {
		caixaDeCores = new int[3][];// Red, Green, Blue

		for (int i = 0; i < 3; i++) // Red, Green, Blue
			caixaDeCores[i] = new int[256];
	}

	public void load(BufferedImage img) {

		// Reset all the bins
		for (int i = 0; i < 3; i++) {// Red, Green, Blue
			for (int j = 0; j < 256; j++) {
				caixaDeCores[i][j] = 0;
			}
		}

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color c = new Color(img.getRGB(x, y));

				caixaDeCores[RED][c.getRed()]++;
				caixaDeCores[GREEN][c.getGreen()]++;
				caixaDeCores[BLUE][c.getBlue()]++;
			}
		}

		for (int i = 0; i < 3; i++) {// Red, Green, Blue
			for (int j = 0; j < 256; j++) {
				if (maxY < caixaDeCores[i][j]) {
					maxY = caixaDeCores[i][j];
				}
			}
		}
	}

	public int getRED() {
		return RED;
	}

	public int getGREEN() {
		return GREEN;
	}

	public int getBLUE() {
		return BLUE;
	}

	public int[][] getCaixaDeCores() {
		return caixaDeCores;
	}

	public int getMaxY() {
		return maxY;
	}

}
