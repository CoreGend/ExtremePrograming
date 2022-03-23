package formation.xp;

public class Board implements IBoard {
	
	private int size = 4;
	private int[][] board;


	public int getSize() {
		return this.size;
	}
	
	public Board() {
		this.board = new int[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	public int[][] getBoard() { return this.board; }
	
	public void print() {
		for(int i=0; i<4; i++) {
			System.out.print("[ ");
			for(int j=0; j<4; j++) {
				System.out.print(board[i][j]);
				if(j<3) {
					System.out.print(", ");
				}
			}
			System.out.println(" ]");
		}


	}
	
	public void putNumber(int value, int x, int y) {
		board[x][y]=value;
	}
}
