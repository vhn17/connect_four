import java.util.LinkedList;

public class Board {

  private final int ROW = 6;
  private final int COL = 7;
  private char[][] board;
  private LinkedList<Move> moves;
  private static final char firstP = 'X';
  private static final char secondP = 'O';
  private final char EMPTY = '.';
  private int[] availableRow;

  char[][] board1 = {
      {firstP,firstP,firstP,firstP,firstP,firstP,firstP},
      {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
      {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
      {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
      {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
      {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
  };

  Board(){
    board = new char[ROW][COL];
    for(int row = 0; row < ROW; row++) {
      for(int col = 0; col < COL; col++) {
        board[row][col] = EMPTY;
      }
    }
    availableRow = new int[COL];
    moves = new LinkedList<>();
    //board = board1;
  }

  public boolean isValid(Move move) {
    int col = move.getCol();
    return (col >= 0 && col < COL && availableRow[col] < ROW - 1);
  }

  public void printBoard(){
    for(int row = ROW - 1; row >= 0; row--) {
      for(int i = 0; i < COL; i++) {
        System.out.print(" -");
      }
      System.out.println();
      for(int col = 0; col < COL; col++) {
        System.out.print("|" + board[row][col]);
      }
      System.out.println("|");
    }
    for(int i = 0; i < COL; i++) {
      System.out.print(" -");
    }
    System.out.println();
    for(int i = 0; i < COL; i++) {
      System.out.print(" " + (i + 1));
    }
    System.out.println();
  }
}
