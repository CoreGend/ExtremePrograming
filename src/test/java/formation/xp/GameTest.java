package formation.xp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
