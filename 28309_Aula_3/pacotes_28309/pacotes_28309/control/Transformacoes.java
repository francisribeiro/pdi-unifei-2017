package pacotes_28309.control;

import pacotes_28309.model.*;

public class Transformacoes {

	protected double fator = 1.5;

	protected ImageINFO zoomOut(ImageINFO img) {
		this.fator+= 1.5;
		
		int imgLargura = img.getLargura();
		int imgAltura = img.getAltura();
		int imgLarguraRaio = (int) ((imgLargura << 16) / (imgLargura * fator)) + 1;
		int imgAlturaRaio = (int) ((imgAltura << 16) / (imgAltura * fator)) + 1;
		ImageINFO temp = new ImageINFO((int) (imgLargura * fator), (int) (imgAltura * fator));

		int x2, y2;
		for (int i = 0; i < (imgAltura * fator); i++) {
			for (int j = 0; j < (imgLargura * fator); j++) {
				x2 = ((j * imgLarguraRaio) >> 16);
				y2 = ((i * imgAlturaRaio) >> 16);
				temp.set(i, j, img.get(y2, x2));
			}
		}

		return temp;
	}

	protected ImageINFO zoomIn(ImageINFO img) {
		this.fator= 1.5;
		int imgLargura = img.getLargura();
		int imgAltura = img.getAltura();
		int imgLarguraRaio = (int) ((imgLargura << 16) / (imgLargura / fator)) + 1;
		int imgAlturaRaio = (int) ((imgAltura << 16) / (imgAltura / fator)) + 1;
		ImageINFO temp = new ImageINFO((int) (imgLargura / fator), (int) (imgAltura / fator));

		int x2, y2;
		for (int i = 0; i < (imgAltura / fator); i++) {
			for (int j = 0; j < (imgLargura / fator); j++) {
				x2 = ((j * imgLarguraRaio) >> 16);
				y2 = ((i * imgAlturaRaio) >> 16);
				temp.set(i, j, img.get(y2, x2));
			}
		}

		return temp;
	}

}
