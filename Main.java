package tictactoe;

import java.util.Scanner;

enum gameStatus {
    XWON, OWON, DRAW, ONGOING, NOTSTARTED
}
public class Main {

    int x = 0;
    int y = 0;
    int turn = 0; //keeps track of whose turn it is to play; even numbers, including 0, mean it is X's turn, for example
    Scanner scanner;
    char[][] gameState;
    gameStatus status = gameStatus.NOTSTARTED;

    public Main() {
        int inputIterator = 0;
        scanner = new Scanner(System.in);
        gameState = new char[3][3];
        status = gameStatus.ONGOING;

        System.out.printf("Enter the cells: ");
        String input = scanner.next();

        //Setting up the board for test purposes (and Stage 1 implementation)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (input.charAt(inputIterator) == '_') {
                    gameState[i][j] = ' ';
                } else {
                    turn++;
                    gameState[i][j] = input.charAt(inputIterator);
                }
                inputIterator++;
            }
        }

        outputTheBoard();

        while ((x == 0 || y == 0 ) && status == gameStatus.ONGOING){
            System.out.printf("Enter the coordinates: ");
            //Checking if user input are indeed two valid coordinates
            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
                y = scanner.nextInt();

                if (x > 3 || x < 1 || y > 3 || y < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    x = 0;
                    y = 0;
                } else {
                    if (takeCell(x,y)){
                        checkGameStatus();
                        outputTheBoard();
                        if (status == gameStatus.ONGOING) {
                            System.out.println("Game not finished");
                            //In a normal scenario we would keep going until the game is done, but Stage 1 only wants one turn to be taken,
                            //so resetting the coordinates is commented out for now
                            //x = 0;
                            //y = 0;
                        } else if (status == gameStatus.XWON) {
                            System.out.println("X wins");
                        } else if (status == gameStatus.OWON) {
                            System.out.println("O wins");
                        } else {
                            System.out.println("Draw");
                        }
                    }
                }

            } else {
                System.out.println("You should enter numbers!");
                scanner = new Scanner(System.in);
            }
        }
    }

    public void checkGameStatus() {
        //Let us first check if X has won yet
        //  horizontal win options:
        if (gameState[0][0] == 'X' && gameState[0][1] == 'X' && gameState[0][2] == 'X') {
            status = gameStatus.XWON;
        } else if (gameState[1][0] == 'X' && gameState[1][1] == 'X' && gameState[1][2] == 'X') {
            status = gameStatus.XWON;
        } else if (gameState[2][0] == 'X' && gameState[2][1] == 'X' && gameState[2][2] == 'X') {
            status = gameStatus.XWON;
        }
        //  vertical win options:
        if (gameState[0][0] == 'X' && gameState[1][0] == 'X' && gameState[2][0] == 'X') {
            status = gameStatus.XWON;
        } else if (gameState[0][1] == 'X' && gameState[1][1] == 'X' && gameState[2][1] == 'X') {
            status = gameStatus.XWON;
        } else if (gameState[0][2] == 'X' && gameState[1][2] == 'X' && gameState[2][2] == 'X') {
            status = gameStatus.XWON;
        }
        //  and finally diagonal win options:
        if (gameState[0][0] == 'X' && gameState[1][1] == 'X' && gameState[2][2] == 'X') {
            status = gameStatus.XWON;
        } else if (gameState[0][2] == 'X' && gameState[1][1] == 'X' && gameState[2][0] == 'X') {
            status = gameStatus.XWON;
        }

        //Now we check all the same stuff, but for O!
        //  horizontal win options:
        if (gameState[0][0] == 'O' && gameState[0][1] == 'O' && gameState[0][2] == 'O' ) {
            status = gameStatus.OWON;
        } else if (gameState[1][0] == 'O' && gameState[1][1] == 'O' && gameState[1][2] == 'O') {
            status = gameStatus.OWON;
        } else if (gameState[2][0] == 'O' && gameState[2][1] == 'O' && gameState[2][2] == 'O') {
            status = gameStatus.OWON;
        }
        //  vertical win options:
        if (gameState[0][0] == 'O' && gameState[1][0] == 'O' && gameState[2][0] == 'O') {
            status = gameStatus.OWON;
        } else if (gameState[0][1] == 'O' && gameState[1][1] == 'O' && gameState[2][1] == 'O') {
            status = gameStatus.OWON;
        } else if (gameState[0][2] == 'O' && gameState[1][2] == 'O' && gameState[2][2] == 'O') {
            status = gameStatus.OWON;
        }
        //  and finally diagonal win options:
        if (gameState[0][0] == 'O' && gameState[1][1] == 'O' && gameState[2][2] == 'O') {
            status = gameStatus.OWON;
        } else if (gameState[0][2] == 'O' && gameState[1][1] == 'O' && gameState[2][0] == 'O') {
            status = gameStatus.OWON;
        }

        if (status == gameStatus.ONGOING) {
            status = gameStatus.DRAW;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameState[i][j] == ' ') {
                        status = gameStatus.ONGOING;
                        break;
                    }
                }
            }
        }

    }

    public boolean takeCell(int x, int y) {
        if (status == gameStatus.NOTSTARTED) {
            System.out.println("You have not even started the game yet.");
            return false;
        } else if (gameState[x-1][y-1] != ' '){
            System.out.println("This cell is occupied! Choose another one!");
            this.x = 0;
            this.y = 0;
            return false;
        } else {
            if (turn % 2 == 0) {
                gameState[x-1][y-1] = 'X';
            } else {
                gameState[x-1][y-1] = 'O';
            }
            return true;
        }
    }

    public void outputTheBoard(){
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| ");
            for (int j = 0; j < 3; j++) {
                System.out.printf(gameState[i][j] + " ");
            }
            System.out.printf("|\n");
        }
        System.out.println("---------");
    }

    public static void main(String[] args) {
        Main newGame = new Main();
    }
}