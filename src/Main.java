public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello world");
        Board board = new Board();
        //board.printBoard();
        Move move = new Move('X');
        move.setCol(7);
        System.out.println(board.isValid(move));
    }
}
