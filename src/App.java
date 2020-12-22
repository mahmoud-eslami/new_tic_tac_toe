public class App {
    public static void main(String[] args) throws Exception {

        String[][] item = {{"-","-","-","-"},{"-","-","-","-"},{"-","-","-","-"},{"-","-","-","-"}};

        Node node = new Node(item);

        welcomeMethod();
        printGameBoard(node);

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