package formation.xp;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import formation.xp.Game.Coord;
import formation.xp.Game.InterTable;

public class GameTest {


	/**
	 * Teste si le board est bien créé lors de la création du jeu
	 */
	@Test
	public void gameBoardTest(){
		Game game = new Game();
		game.init();

		Assertions.assertNotNull(game.getBoard());

	}


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
		
		Assertions.assertEquals(2, b.getBoard()[1][2]);
		
		game.move("up");
		Assertions.assertEquals(2, b.getBoard()[0][2]);
		Assertions.assertEquals(0, b.getBoard()[1][2]);
		
		game.move("down");
		Assertions.assertEquals(2, b.getBoard()[3][2]);
		Assertions.assertEquals(0, b.getBoard()[0][2]);
		
		game.move("right");
		Assertions.assertEquals(2, b.getBoard()[3][3]);
		Assertions.assertEquals(0, b.getBoard()[3][2]);
		
		game.move("left");
		Assertions.assertEquals(2, b.getBoard()[3][0]);
		Assertions.assertEquals(0, b.getBoard()[3][3]);

		game.move("up");
		Assertions.assertEquals(2, b.getBoard()[0][0]);
		Assertions.assertEquals(0, b.getBoard()[3][0]);
	}
	
	/**
	 * Teste le bon fonctionnement du calcul des places libres
	 */
	@Test
	public void testOpenPlaces() {
		Game game = new Game();
		game.init();
		
		int[][] values = new int[4][4];
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				values[i][j]=2;
			}
		}
		
		List<Coord> openPlace;
		openPlace = game.openPlaces(values);
		
		Assertions.assertEquals(0, openPlace.size());
		
		values[1][2] = 0;
		values[3][1] = 0;

		openPlace = game.openPlaces(values);
		
		Assertions.assertEquals(2, openPlace.size());
		Assertions.assertEquals( 1, openPlace.get(0).x);
		Assertions.assertEquals(2, openPlace.get(0).y);
		Assertions.assertEquals(3, openPlace.get(1).x);
		Assertions.assertEquals(1, openPlace.get(1).y);
	}
	
	/**
	 * Teste la génération des pièces
	 */
	@Test
	public void testPieceGeneration() {
		Game game = new Game();
		game.init();
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				game.getBoard().putNumber(16, i, j);
			}
		}

		Assertions.assertEquals(16, game.getBoard().getBoard()[1][2]);
		
		game.getBoard().putNumber(0, 1, 2);
		
		Assertions.assertEquals(0, game.getBoard().getBoard()[1][2]);
		
		game.generatePiece();
		
		Assertions.assertNotEquals(0, game.getBoard().getBoard()[1][2]);
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
		
		Assertions.assertEquals(4, tables.intTable[0][0]);
		Assertions.assertEquals(0, tables.intTable[0][1]);
		Assertions.assertTrue(tables.boolTable[0][0]);
		Assertions.assertFalse(tables.boolTable[0][1]);
		
		// maintenant, on va essayer de fusionner deux cases de valeur différente
		// cas où c'est la case non fusionnée qui essaie de fusionner, valeurs diff
		tables.intTable[1][0] = 2;
		tables = game.mergeTile(tables, 1, 0, 0, 0);
		
		Assertions.assertEquals(4, tables.intTable[0][0]);
		Assertions.assertEquals(2, tables.intTable[1][0]);
		Assertions.assertTrue(tables.boolTable[0][0]);
		Assertions.assertFalse(tables.boolTable[1][0]);
		
		// cas où c'est la case fusionnée qui essaie de fusionner, valeurs diff
		tables = game.mergeTile(tables, 0, 0, 1, 0);
		
		Assertions.assertEquals(4, tables.intTable[0][0]);
		Assertions.assertEquals(2, tables.intTable[1][0]);
		Assertions.assertTrue(tables.boolTable[0][0]);
		Assertions.assertFalse(tables.boolTable[1][0]);
		
		// cas où c'est la case non fusionnée qui essaie de fusionner, valeurs égales
		tables.intTable[1][0] = 4;
		tables = game.mergeTile(tables, 1, 0, 0, 0);
				
		Assertions.assertEquals(4, tables.intTable[0][0]);
		Assertions.assertEquals(4, tables.intTable[1][0]);
		Assertions.assertTrue(tables.boolTable[0][0]);
		Assertions.assertFalse(tables.boolTable[1][0]);
				
		// cas où c'est la case fusionnée qui essaie de fusionner, valeurs égales
		tables = game.mergeTile(tables, 0, 0, 1, 0);
				
		Assertions.assertEquals(4, tables.intTable[0][0]);
		Assertions.assertEquals(4, tables.intTable[1][0]);
		Assertions.assertTrue(tables.boolTable[0][0]);
		Assertions.assertFalse(tables.boolTable[1][0]);
	}
	
	//TODO tester les déplacements avec fusion (intégration)
}
