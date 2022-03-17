package formation.xp;

public interface IBoard {
	/**
	 * Renvoie la taille du tableau
	 */
	int getSize();
	
	int[][] getBoard();
	
	void print();
	
	void putNumber(int value, int x, int y);
}

