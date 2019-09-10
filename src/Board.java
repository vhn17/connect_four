import java.util.LinkedList;

public class Board {

  private final int ROW = 6;
  final int COL = 7;
  private char[][] board;
  private LinkedList<Move> moves;
  static final char firstP = 'X';
  static final char secondP = 'O';
  private final char EMPTY = '.';
  private int[] availableRow;
  private int[] weights = {1, 10, 100};

  Board() {
    board = new char[ROW][COL];
    for (int row = 0; row < ROW; row++) {
      for (int col = 0; col < COL; col++) {
        board[row][col] = EMPTY;
      }
    }
    availableRow = new int[COL];
    moves = new LinkedList<>();
  }

  boolean isValid(Move move) {
    int col = move.getCol();
    return (col >= 0 && col < COL && availableRow[col] <= ROW - 1);
  }

  void makeMove(Move move) {
    moves.add(move);
    int col = move.getCol();
    board[availableRow[col]++][col] = move.getSign();
  }

  void undoMove() {
    Move lastMove = moves.pollLast();
    assert lastMove != null;
    int col = lastMove.getCol();
    availableRow[col]--;
    board[availableRow[col]][col] = EMPTY;

  }

  private int countNInRow(char sign, int n) {
    int counter = 0;

    // n in rows, horizontals
    for (int row = 0; row < ROW; row++) {
      for (int col = 0; col <= COL - n; col++) {
        boolean flag = true;

        for (int i = 1; i < n; i++) {
          flag &= (board[row][col + i - 1] == board[row][col + i]);
        }

        flag &= (board[row][col + n - 1] == sign);

        if (flag) counter++;
      }
    }

    // n in rows, verticals
    for (int row = 0; row <= ROW - n; row++) {
      for (int col = 0; col < COL; col++) {
        boolean flag = true;

        for (int i = 1; i < n; i++) {
          flag &= (board[row + i - 1][col] == board[row + i][col]);
        }

        flag &= (board[row + n - 1][col] == sign);

        if (flag) counter++;
      }
    }

    // n in rows, ascending diagonals
    for (int row = 0; row <= ROW - n; row++) {
      for (int col = 0; col <= COL - n; col++) {
        boolean flag = true;

        for (int i = 1; i < n; i++) {
          flag &= (board[row + i - 1][col + i - 1] == board[row + i][col + i]);
        }

        flag &= (board[row + n - 1][col + n - 1] == sign);
        if (flag) counter++;
      }
    }

    // n in rows, descending diagonals
    for (int row = 0; row <= ROW - n; row++) {
      for (int col = n - 1; col < COL; col++ ) {
        boolean flag = true;

        for (int i = 1; i < n; i++) {
          flag &= (board[row + i - 1][col - (i - 1)] == board[row + i][col - i]);
        }

        flag &= (board[row + n - 1][col - (n - 1)] == sign);

        if (flag) counter++;
      }
    }

    return counter;
  }

  boolean hasWinner() {
    return countNInRow(firstP, 4) > 0 || countNInRow(secondP, 4) > 0;
  }

  int evaluate() {
    int firstScore = 0;
    int secondScore = 0;
    if (hasWinner()) {
      if (getLastMark() == firstP) {
        firstScore += weights[2];
      } else {
        secondScore += weights[2];
      }
    }
    firstScore += (weights[1] * countNInRow(firstP, 3) + countNInRow(firstP, 2));
    secondScore += (weights[1] * countNInRow(secondP, 3) + countNInRow(secondP, 2));
    return firstScore - secondScore;
  }

  boolean isFinished() {
    // we have a winner
    if (hasWinner()) {
      return true;
    }

    // We don't have a winner. If the board is not full, we are not done yet
    for (int row = 0; row < ROW; row++) {
      for (int col = 0; col < COL; col++) {
        if (board[row][col] == EMPTY) {
          return false;
        }
      }
    }

    //The table is full (note: we have a draw)
    return true;
  }

  char getLastMark() {
    return moves.peekLast().getSign();
  }

  void printBoard() {
    for (int row = ROW - 1; row >= 0; row--) {
      for (int i = 0; i < COL; i++) {
        System.out.print(" -");
      }
      System.out.println();
      for (int col = 0; col < COL; col++) {
        System.out.print("|" + board[row][col]);
      }
      System.out.println("|");
    }
    for (int i = 0; i < COL; i++) {
      System.out.print(" -");
    }
    System.out.println();
    for (int i = 0; i < COL; i++) {
      System.out.print(" " + (i + 1));
    }
    System.out.println();
  }
}
