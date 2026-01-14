


public class GameEngine {
    private Board board;
    private int currentPlayer; //1 is player 1, -1 is player 2

    public GameEngine() {
        board = new Board();
        currentPlayer = 1; //start with 1
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        this.currentPlayer *= -1; //inverts
    }

    public boolean makeMove(int col) { //returns true if successful
        boolean placed = board.checkMove(col, currentPlayer);
        if (!placed) return false;
        else {
            return true;
        }
    }

    public boolean gameOver() { //return true when either win or board is full
        return board.checkBoardFull() || board.checkWinner() != 0;
    }

    public int getWinner() { //return -1 or 1
        return board.checkWinner();
    }
}
