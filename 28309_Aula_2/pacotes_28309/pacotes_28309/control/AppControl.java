package pacotes_28309.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import pacotes_28309.view.*;

public class AppControl implements ActionListener, ChangeListener {

	private TelaApp appView;
	private GridControl gridImg, gridTmpl;
	private Color imgLabelColor, tmplLabelColor;
	private Boolean imgSet, tmplSet;

	@SuppressWarnings("unused")
	private ConvolucaoControl cc;

	/**
	 * Construtor da classe. Exibe a aplicação.
	 */
	public AppControl() {
		appView = new TelaApp(this);
		imgSet = false;
		tmplSet = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Abre uma imagem previamente salva
		if (e.getActionCommand().equals("Abrir Imagem")) {
			abrirImagem();
		}

		// Abre um template previamente salvo
		if (e.getActionCommand().equals("Abrir Template")) {
			abrirTemplate();
		}

		// Alterar cor da imagem
		if (e.getActionCommand().equals("Cor da Imagem")) {
			imgLabelColor = appView.selecionarCor(appView.getDefaultColor(appView.lblCorImg));
			appView.setLabelColor(appView.lblCorImg, imgLabelColor);
			gridImg.setColor(imgLabelColor);
		}

		// Alterar cor do template
		if (e.getActionCommand().equals("Cor do Template")) {
			tmplLabelColor = appView.selecionarCor(appView.getDefaultColor(appView.lblCorTmpl));
			appView.setLabelColor(appView.lblCorTmpl, tmplLabelColor);
			gridTmpl.setColor(tmplLabelColor);
		}

		// Salvar imagem
		if (e.getActionCommand().equals("Salvar Imagem")) {
			if (imgSet)
				gridImg.salvarImagem("IMAGEM");
			else// Emite aviso de imagem vazia
				appView.aviso("Não Existe IMAGEM para ser salva!", "Imagem vazia");

		}

		// Salvar template
		if (e.getActionCommand().equals("Salvar Template")) {
			if (tmplSet)
				gridTmpl.salvarImagem("TEMPLATE");
			else // Emite aviso de template vazio
				appView.aviso("Não Existe TEMPLATE para ser salvo!", "Template vazio");
		}

		// Convolucionar imagem
		if (e.getActionCommand().equals("GERAR CONVOLUÇÃO")) {
			if (!imgSet) {
				// Emite aviso de imagem vazia
				appView.aviso("A IMAGEM está vazia!", "Imagem vazia");
			} else if (!tmplSet) {
				// Emite aviso de template vazia
				appView.aviso("O TEMPLATE está vazio!", "Template vazio");
			} else {
				cc = new ConvolucaoControl(gridImg.img(), gridTmpl.img());
			}

		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// Slider de linhas da Imagem
		if (e.getSource() == appView.sldrImgLinhas) {
			appView.setLabel(appView.lblsldrImgLinhas, "Linhas [" + appView.sldrImgLinhas.getValue() + "]:");
			imageGrid(appView.sldrImgLinhas.getValue(), appView.sldrImgColunas.getValue());
		}

		// Slider de colunas da Imagem
		if (e.getSource() == appView.sldrImgColunas) {
			appView.setLabel(appView.lblsldrImgColunas, "Coluna [" + appView.sldrImgColunas.getValue() + "]:");
			imageGrid(appView.sldrImgLinhas.getValue(), appView.sldrImgColunas.getValue());
		}

		// Slider de linhas do Template
		if (e.getSource() == appView.sldrTmplLinhas) {
			appView.setLabel(appView.lblsldrTmplLinhas, "Linhas [" + appView.sldrTmplLinhas.getValue() + "]:");
			templateGrid(appView.sldrTmplLinhas.getValue(), appView.sldrTmplColunas.getValue());
		}

		// Slider de colunas do Template
		if (e.getSource() == appView.sldrTmplColunas) {
			appView.setLabel(appView.lblsldrTmplColunas, "Colunas [" + appView.sldrTmplColunas.getValue() + "]:");
			templateGrid(appView.sldrTmplLinhas.getValue(), appView.sldrTmplColunas.getValue());
		}
	}

	/**
	 * Gera um novo grid de imagem e faz algumas atribuições à esse grid.
	 * 
	 * @param lin altura do grid
	 * @param col largura do grid
	 */
	private void imageGrid(int lin, int col) {
		gridImg = new GridControl(lin, col, false, null);
		appView.img.removeAll();
		gridImg.setColor(appView.getDefaultColor(appView.lblCorImg));
		appView.img.revalidate();
		appView.addGrid(appView.img, gridImg.criarGrid());
		appView.setLabelColor(appView.lblCorImg, appView.getDefaultColor(appView.lblCorImg));

		// Analisa se existe imagem
		if (lin > 0 && col > 0)
			imgSet = true;
		else
			imgSet = false;
	}

	/**
	 * Gera um novo grid de imagem e faz algumas atribuições à esse grid.
	 * 
	 * @param lin altura do grid
	 * @param col largura do grid
	 */
	private void templateGrid(int lin, int col) {
		gridTmpl = new GridControl(lin, col, false, null);
		appView.tmpl.removeAll();
		gridTmpl.setColor(appView.getDefaultColor(appView.lblCorTmpl));
		appView.tmpl.revalidate();
		appView.addGrid(appView.tmpl, gridTmpl.criarGrid());
		appView.setLabelColor(appView.lblCorTmpl, appView.getDefaultColor(appView.lblCorTmpl));

		// Analisa se existe template
		if (lin > 0 && col > 0)
			tmplSet = true;
		else
			tmplSet = false;
	}

	/**
	 * Abre uma imagem.
	 */
	private void abrirImagem() {
		gridImg = new GridControl(0, 0, true, null);
		appView.img.removeAll();
		appView.addGrid(appView.img, gridImg.criarGrid());
		gridImg.setColor(Color.orange);
		appView.img.revalidate();
		imgSet = true;
	}

	/**
	 * Abre um template.
	 */
	private void abrirTemplate() {
		gridTmpl = new GridControl(0, 0, true, null);
		appView.tmpl.removeAll();
		appView.addGrid(appView.tmpl, gridTmpl.criarGrid());
		gridTmpl.setColor(Color.orange);
		appView.tmpl.revalidate();
		tmplSet = true;
	}

}
