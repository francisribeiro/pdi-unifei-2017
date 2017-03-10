package pacotes_28309.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import pacotes_28309.control.AppControl;

public class Grid {

	public JPanel panel, celula;
	private Color cor;
	/**
	 * 
	 * @param nLin
	 * @param nCol
	 * @param appControl
	 * @return
	 */
	public JPanel TestPane(int nLin, int nCol, AppControl appControl) {
		GridBagConstraints gbc;
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(new Color(33,33,33));

		gbc = new GridBagConstraints();

		for (int lin = 0; lin < nLin; lin++) {
			for (int col = 0; col < nCol; col++) {
				gbc.gridx = col;
				gbc.gridy = lin;

				celula = drawCelula();

				Border border = null;
				if (lin < nLin - 1) {
					if (col < nCol - 1)
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					else
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);

				} else {
					if (col < nCol - 1)
						border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
					else
						border = new MatteBorder(1, 1, 1, 1, Color.GRAY);

				}

				celula.setBorder(border);
				panel.add(celula, gbc);
			}
		}

		return panel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel drawCelula() {
		JPanel celula;

		celula = new JPanel();
		celula.setPreferredSize(new Dimension(25, 25));
		celula.setBackground(new Color(33,33,33));
		celula.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				celula.setBackground(cor);
			}

		});

		return celula;
	}
	
	public void setColor(Color cor){
		this.cor = cor;
	}
}
