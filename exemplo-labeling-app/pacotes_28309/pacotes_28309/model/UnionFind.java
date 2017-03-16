package pacotes_28309.model;

/**
 * Classe UnionFind: Modelo para uma estrutura de dados que guarda os valores em
 * uma tabela de equivalências.
 * 
 * @author Francis Ribeiro
 *
 */
public class UnionFind {

	private int[] parent;
	private int[] rank;

	/**
	 * Construtor da classe: Modela uma estrutura de dados do tipo Union-Find.
	 * 
	 * @param max
	 *            Tamanho máximo do array.
	 */
	public UnionFind(int max) {
		this.parent = new int[max];
		this.rank = new int[max];
	}

	// Getters e Setters

	public int getParent(int index) {
		return parent[index];
	}

	public void setParent(int index, int parent) {
		this.parent[index] = parent;
	}

	public int getRank(int index) {
		return rank[index];
	}

	public void setRank(int index, int rank) {
		this.rank[index] = rank;
	}

}
