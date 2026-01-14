
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("connect4 Application");
        Scanner scan = new Scanner(System.in); //scanner object for user input
        menu(scan);
        scan.close();
    }

    public static void menu(Scanner scan) {
        
        boolean quit = false;
        int input; //input int respoonds for positive characters
        
        while (!quit)
        {
            System.out.println("CONNECT 4 - Choose from the options below" + '\n' + '\n' + "---------------------------------------------------------------------------------" + '\n');
            System.out.println("[1] Player vs Player" + '\n'+ '\n' + "[2] Player vs AI" + '\n'+ '\n' + "[3] Quit");
            try {
                input = scan.nextInt();
                
                switch (input) {
                    case 1 -> pvp(scan);
                    case 2 -> pvai();
                    case 3 -> quit = true;
                    default -> System.out.println("Please choose options: [1], [2] or [3]");
                }
            }
            catch (InputMismatchException ime) {
                System.out.println('\n' + "Input must be a number");
                scan.next(); //discard buffer
            }
            catch (Exception e) {
                System.out.println("Error (?)" + e.getMessage());
                scan.next(); //discard buffer
            }

        }

        System.out.println("You have quit, have a great day :)");
    }

    public static void pvp(Scanner scan) {
        System.out.println("Chosen player-versus-player mode" + '\n' + '\n');
        
        GameEngine game = new GameEngine();
        Board board = game.getBoard();

        int choice;
        boolean moveMade = false;
        String currentPlayer = "";
        
        while (!game.gameOver())
        {
            try {
                
                switch (game.getCurrentPlayer()) {
                case 1 -> currentPlayer = "Player 1";
                case -1 -> currentPlayer = "Player 2";
                
                }
                System.out.println(currentPlayer + ", Make your move!");
                board.printBoard();
                System.out.println("Select a number to drop your token");

                choice = scan.nextInt();

                if (choice < 0 || choice > 6) {
                    System.out.println("Invalid column input, choices from 0-6");
                }
                else if (!game.makeMove(choice)) { //if it returns true...
                    System.out.println("Chosen Column [" + choice + "] is full, try again!");
                }
                else {
                    moveMade = true;
                }

                if (moveMade && !game.gameOver()) {
                    game.switchPlayer();
                }
            }
            catch (InputMismatchException ime) {
                System.out.println('\n' + "Input must be a number 0-6");
                scan.next(); //discard buffer
            }
            catch (Exception e) {
                System.out.println("Error (?)" + e.getMessage());
                scan.next(); //discard buffer
            }
            
        }
        game.getBoard().printBoard();
        int winner = game.getWinner(); //assume has winner or has drawn
        switch (winner) {
            case 1:
                System.out.println("Player 1 has won!");
                break;
            case -1:
                System.out.println("Player 2 has won!");
                break;
            default:
                System.out.println("It's a draw!");
                break;
        }

    }

    public static void pvai() {
        System.out.println("Chosen player vs AI mode");
    }
}