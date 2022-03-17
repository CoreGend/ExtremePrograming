package formation.xp;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import formation.xp.Game.Coord;
import formation.xp.Game.InterTable;

public class GameTest {
	/**
	 * Teste le bon fonctionnement des mouvements
	 */
	@Test
	public void moveTest() {
		Game game = new Game();
		game.init();
		
		Board b = game.getBoard();
		b.putNumber(2, 1, 2);
		game.setBoard(b);
		
		Assertions.assertEquals(b.getBoard()[1][2], 2);
		
		game.move("up");
		Assertions.assertEquals(b.getBoard()[0][2], 2);
		Assertions.assertEquals(b.getBoard()[1][2], 0);
		
		game.move("down");
		Assertions.assertEquals(b.getBoard()[3][2], 2);
		Assertions.assertEquals(b.getBoard()[0][2], 0);
		
		game.move("right");
		Assertions.assertEquals(b.getBoard()[3][3], 2);
		Assertions.assertEquals(b.getBoard()[3][2], 0);
		
		game.move("left");
		Assertions.assertEquals(b.getBoard()[3][0], 2);
		Assertions.assertEquals(b.getBoard()[3][3], 0);

		game.move("up");
		Assertions.assertEquals(b.getBoard()[0][0], 2);
		Assertions.assertEquals(b.getBoard()[3][0], 0);
	}
	
	/**
	 * Teste le bon fonctionnement du calcul des places libres
	 */
	public void testOpenPlaces() {
		Game game = new Game();
		
		int[][] values = new int[4][4];
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				values[i][j]=2;
			}
		}
		
		List<Coord> openPlace;
		openPlace = game.openPlaces(values);
		
		Assertions.assertEquals(openPlace.size(), 0);
		
		values[1][2] = 0;
		values[3][1] = 0;
		
		Assertions.assertEquals(openPlace.size(), 2);
		Assertions.assertEquals(openPlace.get(0).x, 1);
		Assertions.assertEquals(openPlace.get(0).y, 2);
		Assertions.assertEquals(openPlace.get(1).x, 3);
		Assertions.assertEquals(openPlace.get(1).y, 1);
	}
	
	/**
	 * Teste la génération des pièces
	 */
	public void testPieceGeneration() {
		Game game = new Game();
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				game.getBoard().putNumber(16, i, j);
			}
		}

		Assertions.assertEquals(game.getBoard().getBoard()[1][2], 16);
		
		game.getBoard().putNumber(0, 1, 2);
		
		Assertions.assertEquals(game.getBoard().getBoard()[1][2], 0);
		
		game.generatePiece();
		
		Assertions.assertNotEquals(game.getBoard().getBoard()[1][2], 0);
	}
	
	/**
	 * test de la fonction de fusion
	 */
	@Test
	public void mergeTest() {
		Game game = new Game();
		game.init();
		
		// fusion devant réussir
		int[][] values = game.getBoard().getBoard();
		
		values[0][0] = 2;
		values[0][1] = 2;		
		
		int size = values.length;
		boolean[][] merged = new boolean[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				merged[i][j] = false;
			}
		}
		
		InterTable tables = game.new InterTable(values, merged);
		tables = game.mergeTile(tables, 0, 0, 0, 1);
		
		Assertions.assertEquals(tables.intTable[0][0], 4);
		Assertions.assertEquals(tables.intTable[0][1], 0);
		Assertions.assertTrue(tables.boolTable[0][0]);
		Assertions.assertFalse(tables.boolTable[0][1]);
		
		// maintenant, on va essayer de fusionner deux cases de valeur différente
		// cas où c'est la case non fusionnée qui essaie de fusionner, valeurs diff
		tables.intTable[1][0] = 2;
		tables = game.mergeTile(tables, 1, 0, 0, 0);
		
		Assertions.assertEquals(tables.intTable[0][0], 4);
		Assertions.assertEquals(tables.intTable[1][0], 2);
		Assertions.assertTrue(tables.boolTable[0][0]);
		Assertions.assertFalse(tables.boolTable[1][0]);
		
		// cas où c'est la case fusionnée qui essaie de fusionner, valeurs diff
		tables = game.mergeTile(tables, 0, 0, 1, 0);
		
		Assertions.assertEquals(tables.intTable[0][0], 4);
		Assertions.assertEquals(tables.intTable[1][0], 2);
		Assertions.assertTrue(tables.boolTable[0][0]);
		Assertions.assertFalse(tables.boolTable[1][0]);
		
		// cas où c'est la case non fusionnée qui essaie de fusionner, valeurs égales
		tables.intTable[1][0] = 4;
		tables = game.mergeTile(tables, 1, 0, 0, 0);
				
		Assertions.assertEquals(tables.intTable[0][0], 4);
		Assertions.assertEquals(tables.intTable[1][0], 4);
		Assertions.assertTrue(tables.boolTable[0][0]);
		Assertions.assertFalse(tables.boolTable[1][0]);
				
		// cas où c'est la case fusionnée qui essaie de fusionner, valeurs égales
		tables = game.mergeTile(tables, 0, 0, 1, 0);
				
		Assertions.assertEquals(tables.intTable[0][0], 4);
		Assertions.assertEquals(tables.intTable[1][0], 4);
		Assertions.assertTrue(tables.boolTable[0][0]);
		Assertions.assertFalse(tables.boolTable[1][0]);
	}
	
	//TODO tester les déplacements avec fusion (intégration)
}
