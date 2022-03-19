package formation.xp;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private Board board;
	
	public Game() {}
	
	public void setBoard(Board board) { this.board = board; }
	public Board getBoard() { return this.board; }
	
	/**
	 * Classe contenant le tableau des valeurs et une mémoire des cases fusionnées
	 */
	public class InterTable { 
		int[][] intTable;
		boolean[][] boolTable;
		public InterTable(int[][] intTable, boolean[][] boolTable) {
			this.intTable = intTable;
			this.boolTable = boolTable;
		}
	}
	
	public Game init() {
		board = new Board();
		return this;
	}
	
	
	public void run() {
		String dir = null;
		boolean change = false;
		
		// boolean done;
		int count = 0;
		do {
			generatePiece();
			System.out.println("");
			board.print();
			System.out.println("Indiquer la direction");
			do {
				dir = Input.BoardInput();
				change = move(dir);
				System.out.println(change);
			}while(!change);

			count++;
		}while(count < 16);
	}
	
	private InterTable localMoveRight(InterTable tables, int size, int i, int j) {
		int[][] values = tables.intTable;
		boolean[][] merged = tables.boolTable;


		if(j<size-1 && values[i][j+1] == 0) {
			//Cas ou la case à droite est libre et existe
			values[i][j+1] = values[i][j];
			values[i][j] = 0;

			merged[i][j+1] = merged[i][j];
			merged[i][j] = false;
			
			tables.intTable = values;
			return localMoveRight(tables, size, i, j+1);
		} else if (j<size-1) {
			//Cas où la case de droite existe mais n'est pas libre
			tables = mergeTile(tables, i, j+1, i, j);
//			On merge la case avec celle de droite
			return localMoveLeft(tables, i, j+1);
//			Fin de la récursion
		} else {
			//Cas où la case de droite n'existe pas
			return tables;
		}
	}
	
	private InterTable localMoveLeft(InterTable tables, int i, int j){
		int[][] values = tables.intTable;
		boolean[][] merged = tables.boolTable;
		
		if(j>0 && values[i][j-1] == 0) {
			values[i][j-1] = values[i][j];
			values[i][j] = 0;

			merged[i][j-1] = merged[i][j];
			merged[i][j] = false;
			
			tables.intTable = values;
			return localMoveLeft(tables, i, j-1);
		} else if (j>0) {
			tables = mergeTile(tables, i, j-1, i, j);
			return localMoveLeft(tables, i, j-1);
		} else {
			return tables;
		}
	}
	
	private InterTable localMoveUp(InterTable tables, int i, int j){
		int[][] values = tables.intTable;
		boolean[][] merged = tables.boolTable;

		if(i>0 && values[i-1][j] == 0) {
			values[i-1][j] = values[i][j];
			values[i][j] = 0;
			
			merged[i-1][j] = merged[i][j];
			merged[i][j] = false;
			
			tables.intTable = values;
			return localMoveUp(tables, i-1, j);
		} else if (i>0) {
			tables = mergeTile(tables, i-1, j, i, j);
			return localMoveUp(tables, i-1, j);
		} else {
			return tables;
		}
	}
	
	private InterTable localMoveDown(InterTable tables, int size, int i, int j){
		int[][] values = tables.intTable;
		boolean[][] merged = tables.boolTable;
		
		if(i<size-1 && values[i+1][j] == 0) {
			values[i+1][j] = values[i][j];
			values[i][j] = 0;
			
			merged[i+1][j] = merged[i][j];
			merged[i][j] = false;
			
			tables.intTable = values;
			tables.boolTable = merged;
			return localMoveDown(tables, size, i+1, j);
		} else if (i<size-1) {
			tables = mergeTile(tables, i+1, j, i, j);
			return localMoveDown(tables, size, i+1, j);
		} else {
			return tables;
		}
	}
	
	public boolean move(String dir) {
		int size = board.getSize();
		boolean[][] merged = new boolean[size][size];

		int[][] previousTable = new int[size][size];
		
		int[][] values = board.getBoard();

		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				merged[i][j]=false;
				//		Réinitialisation du tableau des merge
				previousTable[i][j] = values[i][j];
				//Sauvegarde
			}
		}
		
		InterTable tables = new InterTable(values, merged);
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(dir.equals("up")) {
					tables = localMoveUp(tables, i, j);
				} else if(dir.equals("down")) {
					tables = localMoveDown(tables, size, size-1-i, j); // dernier rang indexé 'size-1'
				} else if(dir.equals("left")) {
					tables = localMoveLeft(tables, i, j);
				} else if(dir.equals("right")) {
					tables = localMoveRight(tables, size, i, size-1-j);
				}
			}
		}
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				board.putNumber(tables.intTable[i][j],i,j);
			}
		}
		
		boolean change = false;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(previousTable[i][j] != tables.intTable[i][j]) {
					change = true;
				}
			}
		}
		return change;
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
	
	public InterTable mergeTile(InterTable tables, int x1, int y1, int x2, int y2) {
		int[][] values = tables.intTable;
		boolean[][] merged = tables.boolTable;
		
		if(!merged[x1][y1] && !merged[x2][y2] && values[x1][y1] == values[x2][y2]) {
//			la tableau merged vérifie si une case a déjà été merged durant le tour. Si oui, elle ne peut pas merge une seconde fois.
//			Les deux cases doivent être identiques
			values[x1][y1] += values[x2][y2];
			values[x2][y2] = 0;
			merged[x1][y1] = true;
		}
		
		tables.intTable = values;
		tables.boolTable = merged;
		return tables;
	}
}
