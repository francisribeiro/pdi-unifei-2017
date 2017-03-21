package pacotes_28309.control;

import java.awt.*;
import java.awt.image.*;

public class Histograma {
	public final int RED = 0;
	public final int GREEN = 1;
	public final int BLUE = 2;
	private int[][] colourBins;
	private int maxY = 0;

	public Histograma() {
		colourBins = new int[3][];// Red, Green, Blue

		for (int i = 0; i < 3; i++) // Red, Green, Blue
			colourBins[i] = new int[256];
	}

	public void load(BufferedImage img) {

		// Reset all the bins
		for (int i = 0; i < 3; i++) {// Red, Green, Blue
			for (int j = 0; j < 256; j++) {
				colourBins[i][j] = 0;
			}
		}

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color c = new Color(img.getRGB(x, y));

				colourBins[RED][c.getRed()]++;
				colourBins[GREEN][c.getGreen()]++;
				colourBins[BLUE][c.getBlue()]++;
			}
		}

		for (int i = 0; i < 3; i++) {// Red, Green, Blue
			for (int j = 0; j < 256; j++) {
				if (maxY < colourBins[i][j]) {
					maxY = colourBins[i][j];
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

	public int[][] getColourBins() {
		return colourBins;
	}

	public int getMaxY() {
		return maxY;
	}

}
