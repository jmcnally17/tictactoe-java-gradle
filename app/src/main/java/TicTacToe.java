import java.io.PrintStream;
import java.util.Scanner;

class TicTacToe {
  private Board board;
  private int playerTurn;
  private Scanner scanner;

  public TicTacToe(Board board, Scanner scanner) {
    this.board = board;
    this.playerTurn = 1;
    this.scanner = scanner;
  }

  public int getPlayerTurn() {
    return this.playerTurn;
  }

  public void displayBoard() {
    this.board.display(System.out);
  }

  public void switchTurn() {
    this.playerTurn = this.getPlayerTurn() % 2 + 1;
  }

  public boolean hasGameFinished() {
    return this.board.winningTripleFound() || this.board.haveCellsBeenFilled();
  }

  public String takePlayersMove(PrintStream out) {
    out.println("Player " + this.getPlayerTurn() + ", please pick a cell from 1 to 9:");
    return this.scanner.nextLine();
  }

  public void declareResult(PrintStream out) {
    if (this.board.winningTripleFound()) {
      out.println("Player " + (this.getPlayerTurn() % 2 + 1) + " wins!");
    } else {
      out.println("It's a draw");
    }
  }

  public void play() {
    System.out.println("Welcome to Tic-Tac-Toe!\n");
    this.displayBoard();

    while (!this.hasGameFinished()) {
      String move = this.takePlayersMove(System.out);
      if (this.board.makeMove(move, this.playerTurn, System.out)) {
        this.switchTurn();
      }
      this.displayBoard();
    }

    this.declareResult(System.out);
    this.scanner.close();
  }

  public static void main(String[] args) {
    Cell[][] cells = { { new Cell("1"), new Cell("2"), new Cell("3") },
        { new Cell("4"), new Cell("5"), new Cell("6") },
        { new Cell("7"), new Cell("8"), new Cell("9") } };
    Board board = new Board(cells);
    Scanner scanner = new Scanner(System.in);
    TicTacToe ticTacToe = new TicTacToe(board, scanner);
    ticTacToe.play();
  }
}
