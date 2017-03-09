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

	/**
	 * Construtor da classe. Exibe a aplicação.
	 */
	public AppControl() {
		appView = new Tela(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Abrir Imagem")) {
			appView.abrirImagem();
		}

		if (e.getActionCommand().equals("Abrir Template")) {
			appView.abrirImagem();
		}

		if (e.getActionCommand().equals("Cor da Imagem")) {
			appView.setLabelColor(appView.corImg, appView.selecionarCor(Color.CYAN));
		}

		if (e.getActionCommand().equals("Cor do Template")) {
			appView.setLabelColor(appView.corTmpl, appView.selecionarCor(Color.MAGENTA));
		}

		if (e.getActionCommand().equals("Salvar Imagem")) {
			appView.salvarImagem();
		}

		if (e.getActionCommand().equals("Salvar Template")) {
			appView.salvarImagem();
		}

		if (e.getActionCommand().equals("Gerar Convolução")) {
			appConvolucao = new TelaConvolucao(this);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == appView.imgLinhas) {
			appView.setLabel(appView.lblImgLinhas, "Linhas [" + appView.imgLinhas.getValue() + "]:");
		}
		
		if (e.getSource() == appView.imgColunas) {
			appView.setLabel(appView.lblImgColunas, "Coluna [" + appView.imgColunas.getValue() + "]:");
		}
		
		if (e.getSource() == appView.tmplLinhas) {
			appView.setLabel(appView.lblTmplLinhas, "Linhas [" + appView.tmplLinhas.getValue() + "]:");
		}
		
		if (e.getSource() == appView.tmplColunas) {
			appView.setLabel(appView.lblTmplColunas, "Colunas [" + appView.tmplColunas.getValue() + "]:");
		}
	}

}
