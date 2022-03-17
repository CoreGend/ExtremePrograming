package formation.xp;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import formation.xp.Game.Coord;

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
}
