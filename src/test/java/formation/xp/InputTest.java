package formation.xp;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputTest {
	/**
	 * Test de la fonction BoardInput()
	 */
	@Test
	public void boardInputTestLeftA() {
		System.setIn(new ByteArrayInputStream("a".getBytes()));
		String result = Input.BoardInput();
		Assertions.assertEquals(result, "left");
	}
	@Test
	public void boardInputTestLeftQ() {
		System.setIn(new ByteArrayInputStream("q".getBytes()));
		String result = Input.BoardInput();
		Assertions.assertEquals(result, "left");
	}
	@Test
	public void boardInputTestRight() {
		System.setIn(new ByteArrayInputStream("d".getBytes()));
		String result = Input.BoardInput();
		Assertions.assertEquals(result, "right");
	}
	@Test
	public void boardInputTestUpZ() {
		System.setIn(new ByteArrayInputStream("z".getBytes()));
		String result = Input.BoardInput();
		Assertions.assertEquals(result, "up");
	}
	@Test
	public void boardInputTestUpW() {
		System.setIn(new ByteArrayInputStream("w".getBytes()));
		String result = Input.BoardInput();
		Assertions.assertEquals(result, "up");
	}
	@Test
	public void boardInputTestDown() {
		System.setIn(new ByteArrayInputStream("s".getBytes()));
		String result = Input.BoardInput();
		Assertions.assertEquals(result, "down");
	}
}
