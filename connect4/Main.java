import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("connect4 Application");
        Scanner scan = new Scanner(System.in); //scanner object for user input
        menu(scan);
    }

    public static void menu(Scanner scan) {
        
        boolean quit = false;
        int input = -1; //input int respoonds for positive characters
        
        while (!quit)
        {
            System.out.println("CONNECT 4 - Choose from the options below");
            
            try {
                input = scan.nextInt();
            }
            

        }
    }
}