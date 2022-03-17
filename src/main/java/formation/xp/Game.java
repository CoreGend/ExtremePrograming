package formation.xp;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private Board board;
	
	public Game() {}
	
	public void setBoard(Board board) { this.board = board; }
	public Board getBoard() { return this.board; }
	
	public Game init() {
		board = new Board();
		return this;
	}
	
	public void run() {
		String dir = null;
		
		// boolean done;
		int count = 0;
		do {
			System.out.println("");
			board.print();
			System.out.println("Indiquer la direction");
			dir = Input.BoardInput();
			move(dir);
			
			
			count++;
		}while(count < 10);
	}
	
	private int[][] localMoveRight(int[][] values, int size, int i, int j) {
		if(j<size-1 && values[i][j+1] == 0) {
			values[i][j+1] = values[i][j];
			values[i][j] = 0;
			return localMoveRight(values, size, i, j+1);
		} else {
			return values;
		}
	}
	
	private int[][] localMoveLeft(int[][] values, int i, int j){
		if(j>0 && values[i][j-1] == 0) {
			values[i][j-1] = values[i][j];
			values[i][j] = 0;
			return localMoveLeft(values, i, j-1);
		} else {
			return values;
		}
	}
	
	private int[][] localMoveUp(int[][] values, int i, int j){
		if(i>0 && values[i-1][j] == 0) {
			values[i-1][j] = values[i][j];
			values[i][j] = 0;
			return localMoveUp(values, i-1, j);
		} else {
			return values;
		}
	}
	
	private int[][] localMoveDown(int[][] values, int size, int i, int j){
		if(i<size-1 && values[i+1][j] == 0) {
			values[i+1][j] = values[i][j];
			values[i][j] = 0;
			return localMoveDown(values, size, i+1, j);
		} else {
			return values;
		}
	}
	
	public void move(String dir) {
		int size = board.getSize();
		int[][] values = board.getBoard();
		boolean mov = false;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				do {
					mov = false;
					if(dir.equals("up")) {
						values = localMoveUp(values, i, j);
					} else if(dir.equals("down")) {
						values = localMoveDown(values, size, size-1-i, j); // dernier rang indexé 'size-1'
					} else if(dir.equals("left")) {
						values = localMoveLeft(values, i, j);
					} else if(dir.equals("right")) {
						values = localMoveRight(values, size, i, size-1-j);
					}
				}while(mov);
			}
		}
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				board.putNumber(values[i][j],i,j);
			}
		}
	}
	
	public class Coord{ 
		int x; 
		int y; 
		public Coord(int x, int y) 
		{
			this.x=x;
			this.y=y;
		} 
	}
	
	/**
	 * 	Liste toutes les cases libres
	 */
	public List<Coord> openPlaces(int[][] values) {
		int size = values.length;
		List<Coord> openPlace = new ArrayList<Coord>();
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(values[i][j] == 0) {
					openPlace.add(new Coord(i,j));
				}
			}
		}
		return openPlace;
	}
	
	/**
	 * Génère une pièce aléatoire
	 * Présupposé : il y a une place de libre (fonction checkPlace appelée avant)
	 */
	public void generatePiece() {
		int[][] values = board.getBoard();
		
		List<Coord> openPlace = openPlaces(values);
		int nbPlaces = openPlace.size();
		
		int rand = (int) (Math.random()*nbPlaces); 
		
		Coord c = openPlace.get(rand);
		
		int newValue = 2;
		if (Math.random() > 0.80) { newValue = 4; }
	
		board.putNumber(newValue, c.x, c.y);
	}
}
