import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // define two dimantions array to store board state
        String[][] item = { { "-", "-", "-", "-" }, { "-", "-", "-", "-" }, { "-", "-", "-", "-" },
                { "-", "-", "-", "-" } };

        // define instance from node
        Node node = new Node(item);

        welcomeMethod();
        printGameBoard(node);

        System.out.println("Please enter 1 or 0 :\n(1 mean ai start game and 0 mean user start game)");
        // ! game mode 1 = ai and 0 = user
        int gameMode = sc.nextInt();

        int state = Game.checkWinner(node);
        boolean moveState = Game.canMoveTile(node);
        boolean gameFinished = false;

        if (!moveState) {
            gameFinished = true;

            System.out.println("Tie!");
        } else if (state == +20) {
            gameFinished = true;

            System.out.println("X is winner");
        } else if (state == -20) {
            gameFinished = true;

            System.out.println("O is winner");
        } else {

            if (gameMode == 0) {
                System.out.println("Please enter x position of O :");
                int xOfO = sc.nextInt();
                System.out.println("Please enter y position of O :");
                int yOfO = sc.nextInt();
                System.out.println("Please enter x position of X :");
                int xOfX = sc.nextInt();
                System.out.println("Please enter y position of X :");
                int yOfX = sc.nextInt();
                Origin oOrigin = new Origin(xOfO, yOfO);
                Origin xOrigin = new Origin(xOfX, yOfX);
                submitMove(node, oOrigin, xOrigin);
                printGameBoard(node);
                Origin origin = Game.findAiMoveX(node);
                System.out.println("x = " + origin.x + " y = " + origin.y);
            }

        }

    }

    public static void welcomeMethod() {
        System.out.println("********************************************");
        System.out.println("*********** Welcome To XO Game *************");
        System.out.println("********************************************");
        System.out.println();
    }

    public static void printGameBoard(Node node) {
        System.out.println("- Game Board :");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(node.nodeInfo[i][j]);
            }
            System.out.println();
        }
    }

    public static void submitMove(Node node, Origin oOrigin, Origin xOrigin) {
        node.nodeInfo[oOrigin.x][oOrigin.y] = Game.me;
        node.nodeInfo[xOrigin.x][xOrigin.y] = Game.ai;
    }
}