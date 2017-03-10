package pacotes_28309.control;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pacotes_28309.view.*;

/**
 * Classe AppControl: Principal controler da aplicação.
 * 
 * @author Francis Ribeiro
 */
public class AppControl implements ActionListener, ChangeListener {

	private Tela appView;
	private TelaConvolucao appConvolucao;
	private GridView gridImg, gridTmpl;
	private Color imgLabelColor, tmplLabelColor;

	/**
	 * Construtor da classe. Exibe a aplicação.
	 */
	public AppControl() {
		appView = new Tela(this);
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
		if (e.getActionCommand().equals("GERAR CONVOLUÇÃO")) {
			appConvolucao = new TelaConvolucao(this);
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
		gridImg = new GridView();
		gridImg.setColor(Color.CYAN);
		appView.img.removeAll();
		appView.img.repaint();
		appView.img.revalidate();
		appView.addGrid(appView.img, gridImg.gerarGrid(lin, col, false));
	}

	/**
	 * Gera um novo grid de template
	 * 
	 * @param lin altura do grid
	 * @param col largura do grid
	 */
	private void templateGrid(int lin, int col) {
		gridTmpl = new GridView();
		gridTmpl.setColor(Color.MAGENTA);
		appView.tmpl.removeAll();
		appView.tmpl.repaint();
		appView.tmpl.revalidate();
		appView.addGrid(appView.tmpl, gridTmpl.gerarGrid(lin, col, false));
	}

	/**
	 * Abre uma imagem.
	 */
	private void abrirImagem() {
		gridImg = new GridView();
		appView.addGrid(appView.img, gridImg.gerarGrid(0, 0, true));
		gridImg.setColor(Color.BLUE);
		appView.tmpl.repaint();
		appView.tmpl.revalidate();

	}

	/**
	 * Abre um template.
	 */
	private void abrirTemplate() {
		gridTmpl = new GridView();
		appView.addGrid(appView.tmpl, gridTmpl.gerarGrid(0, 0, true));
		gridTmpl.setColor(Color.BLUE);
		appView.img.repaint();
		appView.img.revalidate();
	}

}
