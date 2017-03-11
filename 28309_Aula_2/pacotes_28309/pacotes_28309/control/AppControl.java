package pacotes_28309.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import pacotes_28309.view.*;

public class AppControl implements ActionListener, ChangeListener {

	private TelaApp appView;
	private GridPanel gridImg, gridTmpl;
	private Color imgLabelColor, tmplLabelColor;
	private ConvolucaoControl cc;
	private Boolean imgSet, tmplSet;

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
			imgLabelColor = appView.selecionarCor(appView.getDefaultColor(appView.corImg));
			appView.setLabelColor(appView.corImg, imgLabelColor);
			gridImg.setColor(imgLabelColor);
		}

		// Alterar cor do template
		if (e.getActionCommand().equals("Cor do Template")) {
			tmplLabelColor = appView.selecionarCor(appView.getDefaultColor(appView.corTmpl));
			appView.setLabelColor(appView.corTmpl, tmplLabelColor);
			gridTmpl.setColor(tmplLabelColor);
		}

		// Salvar imagem
		if (e.getActionCommand().equals("Salvar Imagem")) {
			gridImg.salvarImagem("IMAGEM");
		}

		// Salvar template
		if (e.getActionCommand().equals("Salvar Template")) {
			gridTmpl.salvarImagem("TEMPLATE");
		}

		// Convolucionar imagem
		if (e.getActionCommand().equals("CONVOLUÇÃO")) {
			if ((appView.tmplLinhas.getValue() & 1) != 1 || (appView.tmplColunas.getValue() & 1) != 1) {
				appView.oddAlert(); // Emite aviso de template não ímpar
			} else if(!imgSet){
				appView.emptyImg(); // Emite aviso de imagem vazia
			}else if(!tmplSet){
				appView.emptyTmpl(); // Emite aviso de template vazio
			}else{
				cc = new ConvolucaoControl(gridImg.img(), gridTmpl.img());
			}

		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// Slider de linhas da Imagem
		if (e.getSource() == appView.imgLinhas) {
			appView.setLabel(appView.lblImgLinhas, "Linhas [" + appView.imgLinhas.getValue() + "]:");
			imageGrid(appView.imgLinhas.getValue(), appView.imgColunas.getValue());
		}

		// Slider de colunas da Imagem
		if (e.getSource() == appView.imgColunas) {
			appView.setLabel(appView.lblImgColunas, "Coluna [" + appView.imgColunas.getValue() + "]:");
			imageGrid(appView.imgLinhas.getValue(), appView.imgColunas.getValue());
		}

		// Slider de linhas do Template
		if (e.getSource() == appView.tmplLinhas) {
			appView.setLabel(appView.lblTmplLinhas, "Linhas [" + appView.tmplLinhas.getValue() + "]:");
			templateGrid(appView.tmplLinhas.getValue(), appView.tmplColunas.getValue());
		}

		// Slider de colunas do Template
		if (e.getSource() == appView.tmplColunas) {
			appView.setLabel(appView.lblTmplColunas, "Colunas [" + appView.tmplColunas.getValue() + "]:");
			templateGrid(appView.tmplLinhas.getValue(), appView.tmplColunas.getValue());
		}
	}

	/**
	 * Gera um novo grid de imagem
	 * 
	 * @param lin altura do grid
	 * @param col largura do grid
	 */
	private void imageGrid(int lin, int col) {
		gridImg = new GridPanel();
		gridImg.setColor(Color.CYAN);
		appView.img.removeAll();
		appView.img.repaint();
		appView.img.revalidate();
		appView.addGrid(appView.img, gridImg.gerarGrid(lin, col, false, null));

		if (lin > 0 && col > 0)
			imgSet = true;
		else
			imgSet = false;
	}

	/**
	 * Gera um novo grid de template
	 * 
	 * @param lin altura do grid
	 * @param col largura do grid
	 */
	private void templateGrid(int lin, int col) {
		gridTmpl = new GridPanel();
		gridTmpl.setColor(Color.MAGENTA);
		appView.tmpl.removeAll();
		appView.tmpl.repaint();
		appView.tmpl.revalidate();
		appView.addGrid(appView.tmpl, gridTmpl.gerarGrid(lin, col, false, null));

		if (lin > 0 && col > 0)
			tmplSet = true;
		else
			tmplSet = false;
	}

	/**
	 * Abre uma imagem.
	 */
	private void abrirImagem() {
		gridImg = new GridPanel();
		appView.addGrid(appView.img, gridImg.gerarGrid(0, 0, true, null));
		gridImg.setColor(Color.CYAN);
		appView.tmpl.repaint();
		appView.tmpl.revalidate();
		imgSet = true;
	}

	/**
	 * Abre um template.
	 */
	private void abrirTemplate() {
		gridTmpl = new GridPanel();
		appView.addGrid(appView.tmpl, gridTmpl.gerarGrid(0, 0, true, null));
		gridTmpl.setColor(Color.MAGENTA);
		appView.img.repaint();
		appView.img.revalidate();
		tmplSet = true;
	}

}
