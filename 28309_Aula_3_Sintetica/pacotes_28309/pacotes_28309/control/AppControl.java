package pacotes_28309.control;

import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.event.*;
import pacotes_28309.view.*;

public class AppControl implements ActionListener, ChangeListener {

	private AppView appView;
	private GridControl gridImg, gridImgTransformada;
	private Boolean imgSet;
	private Color imgLabelColor;
	private Transformacoes transformacoes;
	private BufferedImage img;

	public AppControl() {
		appView = new AppView(this);
		imgSet = false;
		transformacoes = new Transformacoes();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// Slider de linhas da Imagem
		if (e.getSource() == appView.sldrAltura) {
			imageGrid(appView.sldrAltura.getValue(), appView.sldrLargura.getValue());
		}

		// Slider de colunas da Imagem
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

		// Zoom +
		if (e.getSource() == appView.btnZoomMais) {
			img = gridImg.getImg();
			transformacoes.setImg(img);
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.escalar(true));
		}

		// Zoom -
		if (e.getSource() == appView.btnZoomMenos) {
			img = gridImg.getImg();
			transformacoes.setImg(img);
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.escalar(false));
		}

		// Girar Direita
		if (e.getSource() == appView.btnGirarDireita) {
			img = gridImg.getImg();
			transformacoes.setImg(img);
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.rotacionar(false));
		}

		// Girar Esquerda
		if (e.getSource() == appView.btnGirarEsquerda) {
			img = gridImg.getImg();
			transformacoes.setImg(img);
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.rotacionar(true));
		}

		// Espelhar
		if (e.getSource() == appView.btnEspelhar) {
			img = gridImg.getImg();
			transformacoes.setImg(img);
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.espelhar());
		}
		
		// Mover frente
		if (e.getSource() == appView.btnFrente) {
			img = gridImg.getImg();
			transformacoes.setImg(img);
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.transladar(-1, 0));
		}

		// Mover trás
		if (e.getSource() == appView.btnTras) {
			img = gridImg.getImg();
			transformacoes.setImg(img);
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.transladar(-1, 0));
		}

		// Mover cima
		if (e.getSource() == appView.btnCima) {
			img = gridImg.getImg();
			transformacoes.setImg(img);
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.transladar(0, -1));
		}

		// Mover baixo
		if (e.getSource() == appView.btnBaixo) {
			img = gridImg.getImg();
			transformacoes.setImg(img);
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.transladar(0, 1));
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

	/**
	 * Coloca a Imagem do painel da esquerda na direita
	 */
	private void imgTransformadaGrid(int lin, int col, BufferedImage img) {
		gridImgTransformada = new GridControl(lin, col, true, img);
		appView.rightImg.removeAll();
		appView.rightImg.repaint();
		appView.rightImg.revalidate();
		appView.addGrid(appView.rightImg, gridImgTransformada.criarGrid());
	}

}
