package formation.xp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
	/**
	 * Vérification que la board créée n'est pas nulle
	 */
	@Test
	public void testCreation() {
		Board board = new Board();
		
		Assertions.assertNotNull(board);
	}
	
	/**
	 * Vérification de la mise en place d'un nombre
	 */
	@Test
	public void testPutNumber() {
		Board board = new Board();
		
		board.putNumber(2, 1, 2);
		Assertions.assertEquals(board.getBoard()[1][2], 2);
	}
	
	/**
	 * Vérification que la taille est bien égale à 4
	 */
	@Test
	public void testSize() {
		Board board = new Board();
		
		Assertions.assertEquals(board.getBoard().length, 4);
		Assertions.assertEquals(board.getBoard()[0].length, 4);
	}
}