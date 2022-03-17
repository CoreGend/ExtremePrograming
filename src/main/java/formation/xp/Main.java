package formation.xp;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        
        board.print();
        
        System.out.println();
        
        board.putNumber(2, 1, 2);
        
        board.print();
        
        String input = null;
        
        input = Input.BoardInput();
            
        System.out.println(input);
        
        input = Input.BoardInput();
        
        System.out.println(input);
        
        input = Input.BoardInput();
        
        System.out.println(input);
        
        input = Input.BoardInput();
        
        System.out.println(input);
    }
}
