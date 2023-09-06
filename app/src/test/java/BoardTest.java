import static org.testng.Assert.*;
import static org.mockito.Mockito.*;
import java.io.PrintStream;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test for Board class
 */
@Test(groups = { "Board" })
public class BoardTest {
  private Board board;
  private Cell[][] cells;
  private PrintStream mockOut;

  @BeforeMethod
  public void beforeMethod() {
    Cell cell1 = mock(Cell.class);
    Cell cell2 = mock(Cell.class);
    Cell cell3 = mock(Cell.class);
    Cell cell4 = mock(Cell.class);
    Cell cell5 = mock(Cell.class);
    Cell cell6 = mock(Cell.class);
    Cell cell7 = mock(Cell.class);
    Cell cell8 = mock(Cell.class);
    Cell cell9 = mock(Cell.class);
    cells = new Cell[][] { { cell1, cell2, cell3 }, { cell4, cell5, cell6 }, { cell7, cell8, cell9 } };
    board = new Board(cells);
    mockOut = mock(PrintStream.class);
  }

  @Test(groups = { "display" })
  public void printsTheBoardToTerminal() {
    // Set up mocks
    int value = 1;
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        when(cell.getValue()).thenReturn(Integer.toString(value));
        value++;
      }
    }

    String line1 = "     |     |\n";
    String line2 = "  1  |  2  |  3\n";
    String line3 = "_____|_____|_____\n";
    String line4 = "     |     |\n";
    String line5 = "  4  |  5  |  6\n";
    String line6 = "_____|_____|_____\n";
    String line7 = "     |     |\n";
    String line8 = "  7  |  8  |  9\n";
    String line9 = "     |     |\n";
    String displayString = line1 + line2 + line3 + line4 + line5 + line6 + line7 + line8 + line9;

    board.display(mockOut);
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        verify(cell).getValue();
      }
    }
    verify(mockOut).println(displayString);
  }

  @Test(groups = {"makemove"})
  public void fillsFirstCell() {
    when(cells[0][0].fill(1, mockOut)).thenReturn(true);
    assertTrue(board.makeMove("1", 1, mockOut));
    verify(cells[0][0]).fill(1, mockOut);
  }

  @Test(groups = {"makemove"})
  public void fillsSecondCell() {
    when(cells[0][1].fill(1, mockOut)).thenReturn(true);
    assertTrue(board.makeMove("2", 1, mockOut));
    verify(cells[0][1]).fill(1, mockOut);
  }

  @Test(groups = {"makemove"})
  public void fillsThirdCell() {
    when(cells[0][2].fill(1, mockOut)).thenReturn(true);
    assertTrue(board.makeMove("3", 1, mockOut));
    verify(cells[0][2]).fill(1, mockOut);
  }

  @Test(groups = {"makemove"})
  public void fillsFourthCell() {
    when(cells[1][0].fill(1, mockOut)).thenReturn(true);
    assertTrue(board.makeMove("4", 1, mockOut));
    verify(cells[1][0]).fill(1, mockOut);
  }

  @Test(groups = {"makemove"})
  public void fillsFifthCell() {
    when(cells[1][1].fill(1, mockOut)).thenReturn(true);
    assertTrue(board.makeMove("5", 1, mockOut));
    verify(cells[1][1]).fill(1, mockOut);
  }

  @Test(groups = {"makemove"})
  public void fillsSixthCell() {
    when(cells[1][2].fill(1, mockOut)).thenReturn(true);
    assertTrue(board.makeMove("6", 1, mockOut));
    verify(cells[1][2]).fill(1, mockOut);
  }

  @Test(groups = {"makemove"})
  public void fillsSeventhCell() {
    when(cells[2][0].fill(1, mockOut)).thenReturn(true);
    assertTrue(board.makeMove("7", 1, mockOut));
    verify(cells[2][0]).fill(1, mockOut);
  }

  @Test(groups = {"makemove"})
  public void fillsEigthCell() {
    when(cells[2][1].fill(1, mockOut)).thenReturn(true);
    assertTrue(board.makeMove("8", 1, mockOut));
    verify(cells[2][1]).fill(1, mockOut);
  }

  @Test(groups = {"makemove"})
  public void fillsNinthCell() {
    when(cells[2][2].fill(1, mockOut)).thenReturn(true);
    assertTrue(board.makeMove("9", 1, mockOut));
    verify(cells[2][2]).fill(1, mockOut);
  }

  @Test(groups = { "makemove" })
  public void printsInvalidNumber() {
    assertFalse(board.makeMove("25", 1, mockOut));
    verify(mockOut).println("Invalid cell number!");
  }

  @Test(groups = {"winningtriplefound"})
  public void forFirstRow() {
    when(cells[0][0].getValue()).thenReturn("X");
    when(cells[0][1].getValue()).thenReturn("X");
    when(cells[0][2].getValue()).thenReturn("X");
    when(cells[1][0].getValue()).thenReturn("4");
    when(cells[1][1].getValue()).thenReturn("5");
    when(cells[1][2].getValue()).thenReturn("6");
    when(cells[2][0].getValue()).thenReturn("7");
    when(cells[2][1].getValue()).thenReturn("8");
    when(cells[2][2].getValue()).thenReturn("9");

    assertTrue(board.winningTripleFound());
    verify(cells[0][0], times(4)).getValue();
    verify(cells[0][1], times(2)).getValue();
    verify(cells[0][2], times(3)).getValue();
    verify(cells[1][0], times(2)).getValue();
    verify(cells[1][1], times(4)).getValue();
    verify(cells[1][2]).getValue();
    verify(cells[2][0]).getValue();
    verify(cells[2][1]).getValue();
    verify(cells[2][2], times(0)).getValue();
  }

  @Test(groups = {"winningtriplefound"})
  public void forSecondRow() {
    when(cells[0][0].getValue()).thenReturn("1");
    when(cells[0][1].getValue()).thenReturn("2");
    when(cells[0][2].getValue()).thenReturn("3");
    when(cells[1][0].getValue()).thenReturn("X");
    when(cells[1][1].getValue()).thenReturn("X");
    when(cells[1][2].getValue()).thenReturn("X");
    when(cells[2][0].getValue()).thenReturn("7");
    when(cells[2][1].getValue()).thenReturn("8");
    when(cells[2][2].getValue()).thenReturn("9");

    assertTrue(board.winningTripleFound());
    verify(cells[0][0], times(3)).getValue();
    verify(cells[0][1], times(2)).getValue();
    verify(cells[0][2], times(2)).getValue();
    verify(cells[1][0], times(3)).getValue();
    verify(cells[1][1], times(4)).getValue();
    verify(cells[1][2], times(2)).getValue();
    verify(cells[2][0]).getValue();
    verify(cells[2][1]).getValue();
    verify(cells[2][2], times(0)).getValue();
  }

  @Test(groups = {"winningtriplefound"})
  public void forThirdRow() {
    when(cells[0][0].getValue()).thenReturn("1");
    when(cells[0][1].getValue()).thenReturn("2");
    when(cells[0][2].getValue()).thenReturn("3");
    when(cells[1][0].getValue()).thenReturn("4");
    when(cells[1][1].getValue()).thenReturn("5");
    when(cells[1][2].getValue()).thenReturn("6");
    when(cells[2][0].getValue()).thenReturn("X");
    when(cells[2][1].getValue()).thenReturn("X");
    when(cells[2][2].getValue()).thenReturn("X");

    assertTrue(board.winningTripleFound());
    verify(cells[0][0], times(3)).getValue();
    verify(cells[0][1], times(2)).getValue();
    verify(cells[0][2], times(2)).getValue();
    verify(cells[1][0], times(2)).getValue();
    verify(cells[1][1], times(4)).getValue();
    verify(cells[1][2]).getValue();
    verify(cells[2][0], times(2)).getValue();
    verify(cells[2][1]).getValue();
    verify(cells[2][2]).getValue();
  }

  @Test(groups = {"winningtriplefound"})
  public void forFirstColumn() {
    when(cells[0][0].getValue()).thenReturn("X");
    when(cells[0][1].getValue()).thenReturn("2");
    when(cells[0][2].getValue()).thenReturn("3");
    when(cells[1][0].getValue()).thenReturn("X");
    when(cells[1][1].getValue()).thenReturn("5");
    when(cells[1][2].getValue()).thenReturn("6");
    when(cells[2][0].getValue()).thenReturn("X");
    when(cells[2][1].getValue()).thenReturn("8");
    when(cells[2][2].getValue()).thenReturn("9");

    assertTrue(board.winningTripleFound());
    verify(cells[0][0], times(4)).getValue();
    verify(cells[0][1], times(2)).getValue();
    verify(cells[0][2], times(2)).getValue();
    verify(cells[1][0], times(2)).getValue();
    verify(cells[1][1], times(4)).getValue();
    verify(cells[1][2]).getValue();
    verify(cells[2][0], times(2)).getValue();
    verify(cells[2][1]).getValue();
    verify(cells[2][2], times(0)).getValue();
  }

  @Test(groups = {"winningtriplefound"})
  public void forSecondColumn() {
    when(cells[0][0].getValue()).thenReturn("1");
    when(cells[0][1].getValue()).thenReturn("X");
    when(cells[0][2].getValue()).thenReturn("3");
    when(cells[1][0].getValue()).thenReturn("4");
    when(cells[1][1].getValue()).thenReturn("X");
    when(cells[1][2].getValue()).thenReturn("6");
    when(cells[2][0].getValue()).thenReturn("7");
    when(cells[2][1].getValue()).thenReturn("X");
    when(cells[2][2].getValue()).thenReturn("9");

    assertTrue(board.winningTripleFound());
    verify(cells[0][0], times(3)).getValue();
    verify(cells[0][1], times(3)).getValue();
    verify(cells[0][2], times(2)).getValue();
    verify(cells[1][0], times(2)).getValue();
    verify(cells[1][1], times(4)).getValue();
    verify(cells[1][2]).getValue();
    verify(cells[2][0]).getValue();
    verify(cells[2][1], times(2)).getValue();
    verify(cells[2][2], times(0)).getValue();
  }

  @Test(groups = {"winningtriplefound"})
  public void forThirdColumn() {
    when(cells[0][0].getValue()).thenReturn("1");
    when(cells[0][1].getValue()).thenReturn("2");
    when(cells[0][2].getValue()).thenReturn("X");
    when(cells[1][0].getValue()).thenReturn("4");
    when(cells[1][1].getValue()).thenReturn("5");
    when(cells[1][2].getValue()).thenReturn("X");
    when(cells[2][0].getValue()).thenReturn("7");
    when(cells[2][1].getValue()).thenReturn("8");
    when(cells[2][2].getValue()).thenReturn("X");

    assertTrue(board.winningTripleFound());
    verify(cells[0][0], times(3)).getValue();
    verify(cells[0][1], times(2)).getValue();
    verify(cells[0][2], times(3)).getValue();
    verify(cells[1][0], times(2)).getValue();
    verify(cells[1][1], times(4)).getValue();
    verify(cells[1][2]).getValue();
    verify(cells[2][0]).getValue();
    verify(cells[2][1]).getValue();
    verify(cells[2][2]).getValue();
  }

  @Test(groups = {"winningtriplefound"})
  public void forFirstDiagonal() {
    when(cells[0][0].getValue()).thenReturn("X");
    when(cells[0][1].getValue()).thenReturn("2");
    when(cells[0][2].getValue()).thenReturn("3");
    when(cells[1][0].getValue()).thenReturn("4");
    when(cells[1][1].getValue()).thenReturn("X");
    when(cells[1][2].getValue()).thenReturn("6");
    when(cells[2][0].getValue()).thenReturn("7");
    when(cells[2][1].getValue()).thenReturn("8");
    when(cells[2][2].getValue()).thenReturn("X");

    assertTrue(board.winningTripleFound());
    verify(cells[0][0], times(4)).getValue();
    verify(cells[0][1], times(2)).getValue();
    verify(cells[0][2], times(2)).getValue();
    verify(cells[1][0], times(2)).getValue();
    verify(cells[1][1], times(4)).getValue();
    verify(cells[1][2]).getValue();
    verify(cells[2][0]).getValue();
    verify(cells[2][1]).getValue();
    verify(cells[2][2]).getValue();
  }

  @Test(groups = {"winningtriplefound"})
  public void forSecondDiagonal() {
    when(cells[0][0].getValue()).thenReturn("1");
    when(cells[0][1].getValue()).thenReturn("2");
    when(cells[0][2].getValue()).thenReturn("X");
    when(cells[1][0].getValue()).thenReturn("4");
    when(cells[1][1].getValue()).thenReturn("X");
    when(cells[1][2].getValue()).thenReturn("6");
    when(cells[2][0].getValue()).thenReturn("X");
    when(cells[2][1].getValue()).thenReturn("8");
    when(cells[2][2].getValue()).thenReturn("9");

    assertTrue(board.winningTripleFound());
    verify(cells[0][0], times(3)).getValue();
    verify(cells[0][1], times(2)).getValue();
    verify(cells[0][2], times(3)).getValue();
    verify(cells[1][0], times(2)).getValue();
    verify(cells[1][1], times(4)).getValue();
    verify(cells[1][2]).getValue();
    verify(cells[2][0], times(2)).getValue();
    verify(cells[2][1]).getValue();
    verify(cells[2][2], times(0)).getValue();
  }

  @Test(groups = {"winningtriplefound"})
  public void notFound() {
    when(cells[0][0].getValue()).thenReturn("1");
    when(cells[0][1].getValue()).thenReturn("2");
    when(cells[0][2].getValue()).thenReturn("3");
    when(cells[1][0].getValue()).thenReturn("4");
    when(cells[1][1].getValue()).thenReturn("5");
    when(cells[1][2].getValue()).thenReturn("6");
    when(cells[2][0].getValue()).thenReturn("7");
    when(cells[2][1].getValue()).thenReturn("8");
    when(cells[2][2].getValue()).thenReturn("9");

    assertFalse(board.winningTripleFound());
    verify(cells[0][0], times(3)).getValue();
    verify(cells[0][1], times(2)).getValue();
    verify(cells[0][2], times(2)).getValue();
    verify(cells[1][0], times(2)).getValue();
    verify(cells[1][1], times(4)).getValue();
    verify(cells[1][2]).getValue();
    verify(cells[2][0]).getValue();
    verify(cells[2][1]).getValue();
    verify(cells[2][2], times(0)).getValue();
  }

  @Test(groups = { "havecellsbeenfilled" })
  public void trueForAllCellsFilled() {
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        when(cell.hasBeenFilled()).thenReturn(true);
      }
    }

    assertTrue(board.haveCellsBeenFilled());
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        verify(cell).hasBeenFilled();
      }
    }
  }

  @Test(groups = { "havecellsbeenfilled" })
  public void falseForNoCellsFilled() {
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        when(cell.hasBeenFilled()).thenReturn(false);
      }
    }

    assertFalse(board.haveCellsBeenFilled());
    verify(cells[0][0]).hasBeenFilled();
    verify(cells[0][1], times(0)).hasBeenFilled();
    verify(cells[0][2], times(0)).hasBeenFilled();
    verify(cells[1][0], times(0)).hasBeenFilled();
    verify(cells[1][1], times(0)).hasBeenFilled();
    verify(cells[1][2], times(0)).hasBeenFilled();
    verify(cells[2][0], times(0)).hasBeenFilled();
    verify(cells[2][1], times(0)).hasBeenFilled();
    verify(cells[2][2], times(0)).hasBeenFilled();
  }

  @Test(groups = {"havecellsbeenfilled"})
  public void falseForSomeCellsFilled() {
    when(cells[0][0].hasBeenFilled()).thenReturn(true);
    when(cells[0][1].hasBeenFilled()).thenReturn(false);
    when(cells[0][2].hasBeenFilled()).thenReturn(true);
    when(cells[1][0].hasBeenFilled()).thenReturn(true);
    when(cells[1][1].hasBeenFilled()).thenReturn(false);
    when(cells[1][2].hasBeenFilled()).thenReturn(false);
    when(cells[2][0].hasBeenFilled()).thenReturn(true);
    when(cells[2][1].hasBeenFilled()).thenReturn(false);
    when(cells[2][2].hasBeenFilled()).thenReturn(false);

    assertFalse(board.haveCellsBeenFilled());
    verify(cells[0][0]).hasBeenFilled();
    verify(cells[0][1]).hasBeenFilled();
    verify(cells[0][2], times(0)).hasBeenFilled();
    verify(cells[1][0], times(0)).hasBeenFilled();
    verify(cells[1][1], times(0)).hasBeenFilled();
    verify(cells[1][2], times(0)).hasBeenFilled();
    verify(cells[2][0], times(0)).hasBeenFilled();
    verify(cells[2][1], times(0)).hasBeenFilled();
    verify(cells[2][2], times(0)).hasBeenFilled();
  }
}
