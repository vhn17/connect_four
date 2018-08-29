public class Move {

  private char sign;
  private int col;
  private int val;

  Move(char sign) {
    this.sign = sign;
    this.val = 0;
  }

  public char getSign() {
    return sign;
  }

  public int getCol() {
    return col;
  }

  public int getVal() {
    return val;
  }

  public void setSign(char sign) {
    this.sign = sign;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public void setVal(int val) {
    this.val = val;
  }
}
