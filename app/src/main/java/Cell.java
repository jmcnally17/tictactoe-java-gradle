import java.io.PrintStream;

public class Cell {
  private String value;
  private boolean isFilled;

  public Cell(String value) {
    this.value = value;
    this.isFilled = false;
  }

  public String getValue() {
    return this.value;
  }

  public boolean hasBeenFilled() {
    return this.isFilled;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public boolean fill(int playerTurn, PrintStream out) {
    if (this.hasBeenFilled()) {
      out.println("Cell already taken!");
      return false;
    }

    if (playerTurn == 1) {
      this.setValue("X");
    } else if (playerTurn == 2) {
      this.setValue("O");
    }
    this.isFilled = true;
    return true;
  }
}
