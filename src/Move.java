public class Move {

  private char sign;
  private int col;
  private int val;

  Move(char sign) {
    this.sign = sign;
    this.val = 0;
  }

  char getSign() {
    return sign;
  }

  int getCol() {
    return col;
  }

  int getVal() {
    return val;
  }

  public void setSign(char sign) {
    this.sign = sign;
  }

  void setCol(int col) {
    this.col = col;
  }

  void setVal(int val) {
    this.val = val;
  }
}
