import java.util.ArrayList;
import java.util.Random;

public class MiniMax {

  Move getMaxMove(Board board, int depth) {
    Move move = new Move(Board.firstP);
    if(board.isFinished() || depth == 0) {
      move.setVal(board.evaluate() - 1000 * depth);
      return move;
    }

    ArrayList<Integer> bestCols = new ArrayList<>();
    int maxValue = Integer.MIN_VALUE;
    for(int col = 0; col < board.COL; col++) {
      move.setCol(col);
      if(board.isValid(move)) {
        board.makeMove(move);
        Move childMove = getMinMove(board, depth - 1);
        board.undoMove();
        int childVal = childMove.getVal();
        if(childVal > maxValue) {
          maxValue = childVal;
          bestCols.clear();
          bestCols.add(col);
        } else if(childVal == maxValue) {
          bestCols.add(col);
        }
      }
    }
    move.setVal(maxValue);
    move.setCol(bestCols.get(new Random().nextInt(bestCols.size())));
    return move;
  }

  Move getMinMove(Board board, int depth) {
    Move move = new Move(Board.secondP);
    if(board.isFinished() || depth == 0) {
      move.setVal(board.evaluate() + 1000 * depth);
      return move;
    }

    ArrayList<Integer> bestCols = new ArrayList<>();
    int minValue = Integer.MAX_VALUE;
    for(int col = 0; col < board.COL; col++) {
      move.setCol(col);
      if(board.isValid(move)) {
        board.makeMove(move);
        Move childMove = getMaxMove(board, depth - 1);
        board.undoMove();
        int childVal = childMove.getVal();
        if(childVal < minValue) {
          minValue = childVal;
          bestCols.clear();
          bestCols.add(col);
        } else if(childVal == minValue) {
          bestCols.add(col);
        }
      }
    }
    move.setVal(minValue);
    move.setCol(bestCols.get(new Random().nextInt(bestCols.size())));
    return move;
  }

}
