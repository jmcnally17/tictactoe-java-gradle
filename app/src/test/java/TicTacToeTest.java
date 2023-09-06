import static org.testng.Assert.*;
import static org.mockito.Mockito.*;
import java.io.PrintStream;
import java.util.Scanner;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit tests for TicTacToe class
 */
@Test(groups = { "TicTacToe" })
public class TicTacToeTest {
  private TicTacToe ticTacToe;
  private Board board;
  private PrintStream mockOut;
  private Scanner mockScanner;

  @BeforeMethod
  public void beforeMethod() {
    board = mock(Board.class);
    mockScanner = mock(Scanner.class);
    ticTacToe = new TicTacToe(board, mockScanner);
    mockOut = mock(PrintStream.class);
  }

  @Test(groups = { "tictactoeinstantiation" })
  public void playerTurnIs1() {
    assertEquals(1, ticTacToe.getPlayerTurn());
  }

  @Test(groups = { "displayboard" })
  public void callsDisplayOnBoard() {
    ticTacToe.displayBoard();
    verify(board).display(System.out);
  }

  @Test(groups = { "switchturn" })
  public void changesTurnBetween1And2() {
    ticTacToe.switchTurn();
    assertEquals(2, ticTacToe.getPlayerTurn());

    ticTacToe.switchTurn();
    assertEquals(1, ticTacToe.getPlayerTurn());
  }

  @Test(groups = {"hasgamefinished"})
  public void trueWhenPlayerHasWon() {
    when(board.winningTripleFound()).thenReturn(true);
    assertTrue(ticTacToe.hasGameFinished());
    verify(board).winningTripleFound();
  }

  @Test(groups = {"hasgamefinished"})
  public void trueWhenCellsFilled() {
    when(board.winningTripleFound()).thenReturn(false);
    when(board.haveCellsBeenFilled()).thenReturn(true);
    assertTrue(ticTacToe.hasGameFinished());
    verify(board).winningTripleFound();
    verify(board).haveCellsBeenFilled();
  }

  @Test(groups = {"hasgamefinished"})
  public void trueWhenPlayerWonAndCellsFilled() {
    when(board.winningTripleFound()).thenReturn(true);
    when(board.haveCellsBeenFilled()).thenReturn(true);
    assertTrue(ticTacToe.hasGameFinished());
    verify(board).winningTripleFound();
    verify(board, times(0)).haveCellsBeenFilled();
  }

  @Test(groups = {"hasgamefinished"})
  public void falseWhenPlayerHasWon() {
    when(board.winningTripleFound()).thenReturn(false);
    when(board.haveCellsBeenFilled()).thenReturn(false);
    assertFalse(ticTacToe.hasGameFinished());
    verify(board).winningTripleFound();
    verify(board).haveCellsBeenFilled();
  }

  @Test(groups = {"takeplayersmove"})
  public void asksPlayer1ForInputAndReturnsIt() {
    when(mockScanner.nextLine()).thenReturn("5");
    assertEquals("5", ticTacToe.takePlayersMove(mockOut));
    verify(mockOut).println("Player 1, please pick a cell from 1 to 9:");
    verify(mockScanner).nextLine();
  }

  @Test(groups = { "takeplayersmove" })
  public void asksPlayer2ForInputAndReturnsIt() {
    ticTacToe.switchTurn();

    when(mockScanner.nextLine()).thenReturn("5");
    assertEquals("5", ticTacToe.takePlayersMove(mockOut));
    verify(mockOut).println("Player 2, please pick a cell from 1 to 9:");
    verify(mockScanner).nextLine();
  }

  @Test(groups = {"declareresult"})
  public void printsPlayer2Winner() {
    when(board.winningTripleFound()).thenReturn(true);
    ticTacToe.declareResult(mockOut);
    verify(board).winningTripleFound();
    verify(mockOut).println("Player 2 wins!");
  }

  @Test(groups = { "declareresult" })
  public void printsPlayer1Winner() {
    ticTacToe.switchTurn();

    when(board.winningTripleFound()).thenReturn(true);
    ticTacToe.declareResult(mockOut);
    verify(board).winningTripleFound();
    verify(mockOut).println("Player 1 wins!");
  }

  @Test(groups = {"declareresult"})
  public void printsADraw() {
    when(board.winningTripleFound()).thenReturn(false);
    ticTacToe.declareResult(mockOut);
    verify(board).winningTripleFound();
    verify(mockOut).println("It's a draw");
  }
}
