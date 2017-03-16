package pacotes_28309.control;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.event.*;
import pacotes_28309.view.*;

public class AppControl implements ActionListener, ChangeListener {

	private AppView appView;
	private GridControl gridImg;
	private Boolean imgSet;
	private Color imgLabelColor;

	public AppControl() {
		appView = new AppView(this);
		imgSet = false;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// Slider de linhas da Imagem
		if (e.getSource() == appView.sldrAltura) {
			imageGrid(appView.sldrAltura.getValue(), appView.sldrLargura.getValue());
		}

		if (e.getSource() == appView.sldrLargura) {
			imageGrid(appView.sldrAltura.getValue(), appView.sldrLargura.getValue());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Abre uma imagem previamente salva
		if (e.getActionCommand().equals("Abrir Imagem")) {
			abrirImagem();
		}

		// Salvar imagem
		if (e.getActionCommand().equals("Salvar Imagem")) {
			if (imgSet)
				gridImg.salvarImagem("IMAGEM");
			else// Emite aviso de imagem vazia
				appView.aviso("Não Existe IMAGEM para ser salva!", "Imagem vazia");
		}
		
		// Alterar cor da imagem
		if (e.getActionCommand().equals("Selecionar Cor")) {
			imgLabelColor = appView.selecionarCor(appView.getDefaultColor(appView.cor));
			appView.setLabelColor(appView.cor, imgLabelColor);
			gridImg.setColor(imgLabelColor);
		}
	}

	/**
	 * Gera um novo grid de imagem e faz algumas atribuições à esse grid.
	 * 
	 * @param lin altura do grid
	 * @param col largura do grid
	 */
	private void imageGrid(int lin, int col) {
		appView.habilitarBotoes();
		gridImg = new GridControl(lin, col, false, null);
		appView.leftImg.removeAll();
		appView.leftImg.repaint();
		gridImg.setColor(appView.getDefaultColor(appView.cor));
		appView.leftImg.revalidate();
		appView.addGrid(appView.leftImg, gridImg.criarGrid());
		appView.setLabelColor(appView.cor, appView.getDefaultColor(appView.cor));

		// Analisa se existe imagem
		if (lin > 0 && col > 0)
			imgSet = true;
		else
			imgSet = false;
	}

	/**
	 * Abre uma imagem.
	 */
	private void abrirImagem() {
		appView.habilitarBotoes();
		gridImg = new GridControl(0, 0, true, null);
		appView.leftImg.removeAll();
		appView.leftImg.repaint();
		appView.addGrid(appView.leftImg, gridImg.criarGrid());
		gridImg.setColor(Color.RED);
		appView.leftImg.revalidate();
		imgSet = true;
	}

}
