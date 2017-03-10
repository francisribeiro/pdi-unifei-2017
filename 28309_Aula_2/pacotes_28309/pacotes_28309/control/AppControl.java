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
	private GridView grid = new GridView();
	private Color imgLabelColor, tmplLabelColor;

	/**
	 * Construtor da classe. Exibe a aplicação.
	 */
	public AppControl() {
		appView = new Tela(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Abrir Imagem")) {
			abrir();
		}

		if (e.getActionCommand().equals("Abrir Template")) {
			grid.abrirImagem();
		}

		if (e.getActionCommand().equals("Cor da Imagem")) {
			imgLabelColor = appView.selecionarCor(appView.getImgDefaultColor());
			appView.setLabelColor(appView.corImg, imgLabelColor);
			grid.setColor(imgLabelColor);
		}

		if (e.getActionCommand().equals("Cor do Template")) {
			tmplLabelColor = appView.selecionarCor(appView.getTmplDefaultColor());
			appView.setLabelColor(appView.corTmpl, tmplLabelColor);
			grid.setColor(tmplLabelColor);
		}

		if (e.getActionCommand().equals("Salvar Imagem")) {
			grid.salvarImagem("IMAGEM");
		}

		if (e.getActionCommand().equals("Salvar Template")) {
			grid.salvarImagem("TEMPLATE");
		}

		if (e.getActionCommand().equals("Gerar Convolução")) {
			appConvolucao = new TelaConvolucao(this);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == appView.imgLinhas) {
			appView.setLabel(appView.lblImgLinhas, "Linhas [" + appView.imgLinhas.getValue() + "]:");
			imageGrid(appView.imgLinhas.getValue(), appView.imgColunas.getValue());
		}

		if (e.getSource() == appView.imgColunas) {
			appView.setLabel(appView.lblImgColunas, "Coluna [" + appView.imgColunas.getValue() + "]:");
			imageGrid(appView.imgLinhas.getValue(), appView.imgColunas.getValue());
		}

		if (e.getSource() == appView.tmplLinhas) {
			appView.setLabel(appView.lblTmplLinhas, "Linhas [" + appView.tmplLinhas.getValue() + "]:");
			templateGrid(appView.tmplLinhas.getValue(), appView.tmplColunas.getValue());
		}

		if (e.getSource() == appView.tmplColunas) {
			appView.setLabel(appView.lblTmplColunas, "Colunas [" + appView.tmplColunas.getValue() + "]:");
			templateGrid(appView.tmplLinhas.getValue(), appView.tmplColunas.getValue());
		}
	}

	private void imageGrid(int lin, int col) {
		grid = new GridView();
		grid.setColor(Color.CYAN);
		appView.img.removeAll();
		appView.img.repaint();
		appView.img.revalidate();
		appView.addGrid(appView.img, grid.TestPane(lin, col, false));

	}

	private void templateGrid(int lin, int col) {
		grid = new GridView();
		grid.setColor(Color.MAGENTA);
		appView.tmpl.removeAll();
		appView.tmpl.repaint();
		appView.tmpl.revalidate();
		appView.addGrid(appView.tmpl, grid.TestPane(lin, col, false));
	}
	
	private void abrir(){
		grid = new GridView();
		appView.addGrid(appView.img, grid.TestPane(0, 0, true));
		grid.setColor(Color.BLUE);
		appView.tmpl.removeAll();
		appView.tmpl.repaint();
		appView.tmpl.revalidate();
		
	}

}
