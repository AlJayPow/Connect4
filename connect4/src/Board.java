

public class Board {
    private int rows = 6;
    private int cols = 7;
    private int[][] grid;


    public Board() {
        grid = new int[rows][cols];
        //cells default to 0
    }

    public boolean checkMove(int col, int player) {
        //takes column input and player and checks if this move is valid

        //check1: valid column
        if (col < 0 || col >= cols) return false; //invalid column
        //check2: make move - loop from bottom of board upwards
        for (int row = rows - 1; row >= 0 ; row--) {
            //if unoccupied
            if (this.grid[row][col] == 0) {
                this.grid[row][col] = player; //add player's token placeholder
                return true;
            }
        }
        return false; 
    }

    public boolean checkColumnFull(int col) {
        
        return grid[0][col] != 0; //top spot is full -> TRUE, else FALSE
    }

    public boolean checkBoardFull() {
        for (int col = 0; col < cols; col++) {
            if (!checkColumnFull(col)) {
                return false; //if any column has space, return false
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println(); //newline
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char symbol = ' ';
                if (grid[i][j] == 1) symbol = 'X'; //player1

                else if (grid[i][j] == -1) symbol = 'O'; //player2
                System.out.print("|" + symbol);
            }
            System.out.println("|");
        }
        System.out.println(" 0 1 2 3 4 5 6 "); // column numbers
        System.out.println();
    }

    public int checkWinner() { //NOT optimized, but I know this isnt expensive as the board is bounded
        //horizontal check, loop through rows
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols-4; j++) {
                int sum = grid[i][j] + grid[i][j+1] + grid[i][j+2] + grid[i][j+3];
                if (sum == 4) return 1;
                if (sum == -4) return -1;
            }
        }

        //vertical check, loop through columns
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i <= rows - 4; i++) { // ✅ <= 7-4 = 3
                int sum = grid[i][j] + grid[i+1][j] + grid[i+2][j] + grid[i+3][j];
                if (sum == 4) return 1;
                if (sum == -4) return -1;           // ✅ return -1, not 0
            }
        }
        //diagonal (\) - start search at the middle as diagonal win condition cannot be complete at some starting positions
        for (int i = 0; i <= rows - 4; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                int sum = grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2] + grid[i+3][j+3];
                if (sum == 4) return 1;
                if (sum == -4) return -1;
            }
        }

        // diagonal (/) likewise as above, but inverted
        for (int i = 3; i < rows; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                int sum = grid[i][j] + grid[i-1][j+1] + grid[i-2][j+2] + grid[i-3][j+3];
                if (sum == 4) return 1;
                if (sum == -4) return -1;
            }
        }

        return 0;
    }

    public int[][] getGrid() {
        return this.grid;
    }
}
