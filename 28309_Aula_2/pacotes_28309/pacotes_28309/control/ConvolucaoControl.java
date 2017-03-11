package pacotes_28309.control;

import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import pacotes_28309.model.*;
import pacotes_28309.view.TelaConvolucao;

public class ConvolucaoControl implements ActionListener {

	private TelaConvolucao appConvolucao;
	private BufferedImage imagem, template;

	public ConvolucaoControl(BufferedImage imagem, BufferedImage template) {
		appConvolucao = new TelaConvolucao(this);
		this.imagem = imagem;
		this.template = template;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Convoluir")) {
		
		}
	}
	
	public static DataArray[] getArrayDatasFromImage(String filename) throws IOException {
		BufferedImage inputImage = ImageIO.read(new File(filename));
		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		int[] rgbData = inputImage.getRGB(0, 0, width, height, null, 0, width);
		DataArray reds = new DataArray(width, height);
		DataArray greens = new DataArray(width, height);
		DataArray blues = new DataArray(width, height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int rgbValue = rgbData[y * width + x];
				reds.set(x, y, (rgbValue >>> 16) & 0xFF);
				greens.set(x, y, (rgbValue >>> 8) & 0xFF);
				blues.set(x, y, rgbValue & 0xFF);
			}
		}
		return new DataArray[] { reds, greens, blues };
	}

}
