
public class main {

	public static void main(String[] args) {
		Teste t = new Teste();
		t.saida();
		t.rotularImagem();
		System.out.println();
		t.saida();
	}

}

class Teste {

	int width = 11;
	int height = 13;
	/*
	 * int[][] mat = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1,
	 * 1, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 1,
	 * 0, 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0 }, { 0,
	 * 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0
	 * }, { 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0 }, { 0, 1, 0, 0, 0, 0, 0, 1, 0,
	 * 0, 1, 0 }, { 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0,
	 * 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
	 */

	int[][] mat = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0 },
			{ 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0 }, { 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

	// Imprime saída
	public void saida() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++)
				if (mat[i][j] == 0)
					System.out.printf("  ");
				else
					System.out.printf("%d ", mat[i][j]);
			System.out.print("\n");
		}
	}

	// Rotula imagem
	public void rotularImagem() {
		int numeroLabel = 0;
		UnionFind uf = new UnionFind(30);

		// Varrendo imagem
		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width; j++) {
				if (mat[i][j] == 0) {
					continue;
				} else {
					if (mat[i - 1][j] == 0 && mat[i][j - 1] == 0) {
						mat[i][j] = ++numeroLabel;
					} else if (mat[i - 1][j] != 0 && mat[i][j - 1] == 0) {
						mat[i][j] = mat[i - 1][j];
					} else if (mat[i][j - 1] != 0 && mat[i - 1][j] == 0) {
						mat[i][j] = mat[i][j - 1];
					} else if (mat[i][j - 1] != 0 && mat[i - 1][j] != 0) {
						mat[i][j] = mat[i - 1][j];
						uf.union(mat[i][j - 1], mat[i - 1][j]);
					}
				}
			}
		}

		saida();
		System.out.println();

		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width; j++) {
				mat[i][j] = uf.find(mat[i][j]);
			}
		}
	}

}

class UnionFind {

	private int[] parent;
	private int[] rank;

	public int find(int i) {
		int p = parent[i];

		if (i == p)
			return i;

		// return parent[i] = find(p);
		return parent[i];

	}

	public void union(int i, int j) {
		int root1 = find(i);
		int root2 = find(j);

		if (root2 == root1)
			return;

		if (rank[root1] > rank[root2])
			parent[root2] = root1;
		else if (rank[root2] > rank[root1])
			parent[root1] = root2;
		else {
			parent[root2] = root1;
			rank[root1]++;
		}

	}

	public UnionFind(int max) {
		this.parent = new int[max];
		this.rank = new int[max];

		for (int i = 0; i < max; i++)
			parent[i] = i;
	}
}