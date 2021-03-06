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
        boolean gameFinished = true;

        do {
            int state = Game.checkWinner(node);
            boolean tieState = Game.canMoveTile(node);

            if (state == +100) {
                gameFinished = false;
                System.out.println("X Win Game !");
            } else if (state == -100) {
                gameFinished = false;
                System.out.println("O Win Game !");
            } else if (!tieState) {
                gameFinished = false;
                System.out.println("Tie !");
            } else {
                if (gameMode == 0) {
                    getCoordinateFromUser(node, sc, false);
                    printGameBoard(node);
                    Game.findBestMove(node);
                    printGameBoard(node);
                } else {
                    gameMode = 0;
                    aiRandomMove(node);
                    printGameBoard(node);
                    getCoordinateFromUser(node, sc, false);
                    printGameBoard(node);
                    Game.findBestMove(node);
                    printGameBoard(node);
                }
            }

        } while (gameFinished);
    }

    public static void aiRandomMove(Node node) {
        int i_x_random = (int) (Math.random() * (3 - 0 + 1) + 0);
        int j_x_random = (int) (Math.random() * (3 - 0 + 1) + 0);

        if ((i_x_random + 1) >= 0 && (i_x_random + 1) < 4 && (j_x_random) < 4 && (j_x_random) >= 0) {

            node.nodeInfo[i_x_random][j_x_random] = Game.ai;
            node.nodeInfo[i_x_random + 1][j_x_random] = Game.me;

        } else if ((i_x_random - 1) >= 0 && (i_x_random - 1) < 4 && (j_x_random) < 4 && (j_x_random) > 0) {

            node.nodeInfo[i_x_random][j_x_random] = Game.ai;
            node.nodeInfo[i_x_random - 1][j_x_random] = Game.me;

        } else if ((i_x_random) >= 0 && (i_x_random) < 4 && (j_x_random + 1) < 4 && (j_x_random + 1) >= 0) {

            node.nodeInfo[i_x_random][j_x_random] = Game.ai;
            node.nodeInfo[i_x_random][j_x_random + 1] = Game.me;

        } else if ((i_x_random) >= 0 && (i_x_random) < 4 && (j_x_random - 1) < 4 && (j_x_random - 1) >= 0) {

            node.nodeInfo[i_x_random][j_x_random] = Game.ai;
            node.nodeInfo[i_x_random][j_x_random - 1] = Game.me;

        }
    }

    public static void welcomeMethod() {
        System.out.println();
        System.out.println("********************************************");
        System.out.println("*********** Welcome To XO Game *************");
        System.out.println("********************************************");
        System.out.println();
    }

    public static void printGameBoard(Node node) {
        System.out.println("- Game Board :");
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(node.nodeInfo[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void getCoordinateFromUser(Node node, Scanner sc, boolean maxPlayer) {
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
        submitMove(node, oOrigin, xOrigin, maxPlayer);
    }

    public static void submitMove(Node node, Origin oOrigin, Origin xOrigin, boolean maxPlayer) {
        if (!maxPlayer) {
            node.nodeInfo[oOrigin.x][oOrigin.y] = Game.me;
            node.nodeInfo[xOrigin.x][xOrigin.y] = Game.ai;
        } else {
            node.nodeInfo[oOrigin.x][oOrigin.y] = Game.me;
            node.nodeInfo[xOrigin.x][xOrigin.y] = Game.ai;
        }
    }

}