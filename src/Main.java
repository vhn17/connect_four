import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    boolean compFirst;
    char pSign;
    MiniMax miniMax = new MiniMax();
    int depth = 6;

    Board board = new Board();
    board.printBoard();

    System.out.println("Would you like to start? If yes, type yes");
    Scanner scanner = new Scanner(System.in);
    String answer = scanner.next();
    if (answer.equalsIgnoreCase("yes")) {
      compFirst = false;
      pSign = Board.firstP;
    } else {
      compFirst = true;
      pSign = Board.secondP;
      Move move = miniMax.getMaxMove(board, depth);
      System.out.println("The computer chose column " + (move.getCol() + 1));
      board.makeMove(move);
      board.printBoard();
    }

    while (!board.isFinished()) {
      System.out.println("Your turn");
      Move move = new Move(pSign);
      int col = scanner.nextInt() - 1;
      move.setCol(col);
      if (board.isValid(move)) {

        board.makeMove(move);
        board.printBoard();
        System.out.println();

        if (board.isFinished()) {
          break;
        }

        if(compFirst) {
          move = miniMax.getMaxMove(board, depth);
        } else {
          move = miniMax.getMinMove(board, depth);
        }

        board.makeMove(move);
        System.out.println("The computer chose column " + (move.getCol() + 1));
        board.printBoard();
        System.out.println();
      } else {
        System.out.println("Invalid move, try again please");
      }
    }

    if(board.hasWinner()) {
      char winnerMark = board.getLastMark();
      if(winnerMark == pSign) {
        System.out.println("You won");
      } else {
        System.out.println("You lost");
      }
    } else {
      System.out.println("It's a draw");
    }
  }
}
