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

  char[][] board1 = {
      {firstP, secondP, secondP, firstP, firstP, secondP, firstP},
      {EMPTY, firstP, firstP, EMPTY, EMPTY, firstP, EMPTY},
      {EMPTY, firstP, EMPTY, EMPTY, firstP, EMPTY, EMPTY},
      {EMPTY, EMPTY, firstP, EMPTY, EMPTY, EMPTY, EMPTY},
      {EMPTY, firstP, EMPTY, EMPTY, EMPTY, firstP, firstP},
      {firstP, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, firstP},
  };
  char[][] board2 = {
      {firstP, firstP, firstP, firstP, firstP, firstP, EMPTY},
      {firstP, firstP, firstP, firstP, firstP, firstP, firstP},
      {firstP, firstP, firstP, firstP, firstP, firstP, firstP},
      {firstP, firstP, firstP, firstP, firstP, firstP, firstP},
      {firstP, firstP, firstP, firstP, firstP, firstP, firstP},
      {firstP, firstP, firstP, firstP, firstP, firstP, firstP}
  };
  char[][] board3 = {
      {firstP, secondP, firstP, secondP, firstP, secondP, firstP},
      {secondP, firstP, secondP, firstP, secondP, firstP, secondP},
      {firstP, secondP, firstP, secondP, firstP, secondP, firstP},
      {secondP, firstP, secondP, firstP, secondP, firstP, secondP},
      {firstP, secondP, firstP, secondP, firstP, secondP, firstP},
      {secondP, firstP, secondP, firstP, secondP, firstP, EMPTY}
  };

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

  public boolean isValid(Move move) {
    int col = move.getCol();
    return (col >= 0 && col < COL && availableRow[col] <= ROW - 1);
  }

  public void makeMove(Move move) {
    moves.add(move);
    int col = move.getCol();
    board[availableRow[col]++][col] = move.getSign();
  }

  public void undoMove() {
    Move lastMove = moves.pollLast();
    int col = lastMove.getCol();
    board[--availableRow[col]][col] = EMPTY;

  }

  // counts the number of 2 in rows of the given character
  int count2inRow(char sign) {
    int counter = 0;

    // 2 in rows, horizontals
    for (int row = 0; row < ROW; row++) {
      for (int col = 0; col <= COL - 2; col++) {
        if (board[row][col] == board[row][col + 1] &&
            board[row][col + 1] == sign) {
          counter++;
        }
      }
    }

    // 2 in rows, verticals
    for (int row = 0; row <= ROW - 2; row++) {
      for (int col = 0; col < COL; col++) {
        if (board[row][col] == board[row + 1][col] &&
            board[row + 1][col] == sign) {
          counter++;
        }
      }
    }

    // 2 in rows, ascending diagonals
    for (int row = 0; row <= ROW - 2; row++) {
      for (int col = 0; col <= COL - 2; col++) {
        if (board[row][col] == board[row + 1][col + 1] &&
            board[row + 1][col + 1] == sign) {
          counter++;
        }
      }
    }

    // 2 in rows, descending diagonals
    for (int row = 0; row <= ROW - 2; row++) {
      for (int col = 1; col < COL; col++) {
        if (board[row][col] == board[row + 1][col - 1] &&
            board[row + 1][col - 1] == sign) {
          counter++;
        }
      }
    }
    return counter;
  }

  // counts the number of 3 in rows of the given character
  int count3inRow(char sign) {
    int counter = 0;
    // count 3 in rows horizontally
    for (int row = 0; row < ROW; row++) {
      for (int col = 0; col <= COL - 3; col++) {
        if (board[row][col] == board[row][col + 1] &&
            board[row][col + 1] == board[row][col + 2] &&
            board[row][col + 2] == sign) {
          counter++;
        }
      }
    }

    // count 3 in rows vertically
    for (int row = 0; row <= ROW - 3; row++) {
      for (int col = 0; col < COL; col++) {
        if (board[row][col] == board[row + 1][col] &&
            board[row + 1][col] == board[row + 2][col] &&
            board[row + 2][col] == sign) {
          counter++;
        }
      }
    }

    // 3 in rows, ascending diagonals
    for (int row = 0; row <= ROW - 3; row++) {
      for (int col = 0; col <= COL - 3; col++) {
        if (board[row][col] == board[row + 1][col + 1] &&
            board[row + 1][col + 1] == board[row + 2][col + 2] &&
            board[row + 2][col + 2] == sign) {
          counter++;
        }
      }
    }

    // 3 in rows, descending diagonals
    for (int row = 0; row <= ROW - 3; row++) {
      for (int col = 2; col < COL; col++) {
        if (board[row][col] == board[row + 1][col - 1] &&
            board[row + 1][col - 1] == board[row + 2][col - 2] &&
            board[row + 2][col - 2] == sign) {
          counter++;
        }
      }
    }
    return counter;
  }

  // checks, if we have 4 in a row of one kind
  boolean hasWinner() {
    // 4 in a row vertically
    for (int row = 0; row < ROW; row++) {
      for (int col = 0; col <= COL - 4; col++) {
        if (board[row][col] == board[row][col + 1] &&
            board[row][col + 1] == board[row][col + 2] &&
            board[row][col + 2] == board[row][col + 3] &&
            board[row][col + 3] != EMPTY) {
          return true;
        }
      }
    }

    // 4 in a row horizontally
    for (int row = 0; row <= ROW - 4; row++) {
      for (int col = 0; col < COL; col++) {
        if (board[row][col] == board[row + 1][col] &&
            board[row + 1][col] == board[row + 2][col] &&
            board[row + 2][col] == board[row + 3][col] &&
            board[row + 3][col] != EMPTY) {
          return true;
        }
      }
    }

    // 4 in a row, ascending diagonal
    for (int row = 0; row <= ROW - 4; row++) {
      for (int col = 0; col <= COL - 4; col++) {
        if (board[row][col] == board[row + 1][col + 1] &&
            board[row + 1][col + 1] == board[row + 2][col + 2] &&
            board[row + 2][col + 2] == board[row + 3][col + 3] &&
            board[row + 3][col + 3] != EMPTY) {
          return true;
        }
      }
    }

    // 4 in a row, descending diagonal
    for (int row = 0; row <= ROW - 4; row++) {
      for (int col = 3; col < COL; col++) {
        if (board[row][col] == board[row + 1][col - 1] &&
            board[row + 1][col - 1] == board[row + 2][col - 2] &&
            board[row + 2][col - 2] == board[row + 3][col - 3] &&
            board[row + 3][col - 3] != EMPTY) {
          return true;
        }
      }
    }

    return false;
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

    firstScore += (weights[1] * count3inRow(firstP) + count2inRow(firstP));
    secondScore += (weights[1] * count3inRow(secondP) + count2inRow(secondP));
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

  public void printBoard() {
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
