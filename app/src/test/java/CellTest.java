import static org.testng.Assert.*;
import static org.mockito.Mockito.*;
import java.io.PrintStream;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test for Cell class
 */
@Test(groups = { "Cell" })
public class CellTest {
  private Cell cell;
  private PrintStream outMock;

  @BeforeMethod
  public void beforeMethod() {
    cell = new Cell("4");
    outMock = mock(PrintStream.class);
  }

  @Test(groups = { "cellinstantiation" })
  public void isFilledIsFalse() {
    assertFalse(cell.hasBeenFilled());
  }

  @Test(groups = { "cellinstantiation" })
  public void valueIsSetToConstructorParameter() {
    assertEquals("4", cell.getValue());
  }

  @Test(groups = { "setvalue" })
  public void changesValueProperty() {
    cell.setValue("X");
    assertEquals("X", cell.getValue());
  }

  @Test(groups = { "fill" })
  public void setsValueToXForPlayer1Turn() {
    assertTrue(cell.fill(1, outMock));
    assertEquals("X", cell.getValue());
  }

  @Test(groups = { "fill" })
  public void setsValueToOForPlayer2Turn() {
    assertTrue(cell.fill(2, outMock));
    assertEquals("O", cell.getValue());
  }

  @Test(groups = { "fill" })
  public void setsIsFilledToTrue() {
    assertTrue(cell.fill(1, outMock));
    assertTrue(cell.hasBeenFilled());
  }

  @Test(groups = { "fill" })
  public void doesNotChangeValueIfIsFilledIsTrue() {
    cell.fill(1, outMock); // call fill initially to set isFilled to true

    assertFalse(cell.fill(2, outMock));
    verify(outMock).println("Cell already taken!");
  }
}
