public class App {
    public static void main(String[] args) throws Exception {

        String[][] item = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}};

        welcomeMethod();
        printGameBoard(item);

    }

    public static void welcomeMethod() {
        System.out.println("********************************************");
        System.out.println("*********** Welcome To XO Game *************");
        System.out.println("********************************************");
    }

    public static void printGameBoard(String[][] gameBoard) {
        System.out.println("- Game Board :");
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if(gameBoard[i][j] == ""){
                    gameBoard[i][j] = "-";
                }
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }
}