public class App {
    public static void main(String[] args) throws Exception {

        String[][] item = { { "o", "x", "x", "o" },
                            { "o", "x", "x", "o" },
                            { "-", "o", "o", "-" },
                            { "-", "o", "-", "-" } };

        Node node = new Node(item);

        welcomeMethod();
        printGameBoard(node);
        int state = Game.checkWinner(node);

        if (state == +20) {
            System.out.println("X is winner");

        } else if (state == -20) {
            System.out.println("O is winner");

        } else {
            Origin origin = Game.findAiMoveX(node);
            System.out.println("x = " + origin.x + " y = " + origin.y);
        }

    }

    public static void welcomeMethod() {
        System.out.println("********************************************");
        System.out.println("*********** Welcome To XO Game *************");
        System.out.println("********************************************");
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
}