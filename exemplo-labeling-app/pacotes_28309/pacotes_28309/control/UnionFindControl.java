package pacotes_28309.control;

import pacotes_28309.model.*;

/**
 * Classe UnionFindControl: Implementa os métodos da classe modelada
 * em @UnionFind.
 * 
 * @author Francis Ribeiro
 *
 */
public class UnionFindControl {
	private UnionFind uf;

	/**
	 * Contrutor da classe. Inicia um Union-Find para as equivalências.
	 * 
	 * @param max
	 *            Tamanho máximo da estrutura.
	 */
	public UnionFindControl(int max) {
		uf = new UnionFind(max);
		this.inicializar(max);
	}

	/**
	 * Inicializa o array de parents.
	 * 
	 * @param max
	 *            Tamanho máximo da estrutura.
	 */
	private void inicializar(int max) {
		for (int i = 0; i < max; i++)
			this.uf.setParent(i, i);
	}

	/**
	 * Método para buscar um elemento passado o índice.
	 * 
	 * @param i
	 *            Índice de busca
	 * @return find(p) Elemento encontrado
	 */
	public int find(int i) {
		int p = uf.getParent(i);

		if (i == p)
			return i;

		return find(p);
	}

	/**
	 * Método para unir dois valores equivalentes.
	 * 
	 * @param i
	 *            primeiro valor
	 * @param j
	 *            Segundo segundo
	 */
	public void union(int i, int j) {
		int root1 = find(i);
		int root2 = find(j);
		int newRank;

		if (root2 == root1)
			return;

		if (uf.getRank(root1) > uf.getRank(root2))
			uf.setParent(root2, root1);
		else if (uf.getRank(root2) > uf.getRank(root1))
			uf.setParent(root1, root2);
		else {
			uf.setParent(root2, root1);
			newRank = uf.getRank(root1);
			uf.setRank(root1, newRank++);
		}
	}

}
