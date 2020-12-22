
public class Game {

    // player in this game
    static enum playerEnum {
        ai, me, tie,
    }

    // character symbol
    public static String ai = "X";
    public static String me = "O";
    public static String playerTurn = ai;

    // alpha and beta count
    static int alpha = -10000;
    static int beta = +10000;

    // check move remaining
    public static boolean canMoveTile(Node node) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (node.nodeInfo[i][j] == "-") {
                    return true;
                }
            }
        }
        return false;
    }

    public static playerEnum determineWinnerPerCharacter(String character) {
        if (character == ai) {
            return playerEnum.ai;
        } else if (character == me) {
            return playerEnum.me;
        } else {
            return playerEnum.tie;
        }
    }

    public static playerEnum checkWinner(Node node) {
        playerEnum defultPlayerEnum = playerEnum.tie;
        // check row winner
        for (int i = 0; i < 4; i++) {
            if (node.nodeInfo[i][0] == node.nodeInfo[i][1] && node.nodeInfo[i][1] == node.nodeInfo[i][2]
                    || node.nodeInfo[i][1] == node.nodeInfo[i][2] && node.nodeInfo[i][2] == node.nodeInfo[i][3]) {
                // call determine user winner per character in array
                defultPlayerEnum = determineWinnerPerCharacter(node.nodeInfo[i][1]);
                return defultPlayerEnum;
            }
        }
        // check column winner
        for (int j = 0; j < 4; j++) {
            if (node.nodeInfo[0][j] == node.nodeInfo[1][j] && node.nodeInfo[1][j] == node.nodeInfo[2][j]
                    || node.nodeInfo[1][j] == node.nodeInfo[2][j] && node.nodeInfo[2][j] == node.nodeInfo[3][j]) {
                // call determine user winner per character in array
                defultPlayerEnum = determineWinnerPerCharacter(node.nodeInfo[1][j]);
                return defultPlayerEnum;
            }
        }
        // check diametric winner
        if (node.nodeInfo[0][0] == node.nodeInfo[1][1] && node.nodeInfo[1][1] == node.nodeInfo[2][2]
                || node.nodeInfo[1][1] == node.nodeInfo[2][2] && node.nodeInfo[2][2] == node.nodeInfo[3][3]) {
            // call determine user winner per character in array
            defultPlayerEnum = determineWinnerPerCharacter(node.nodeInfo[1][1]);
            return defultPlayerEnum;

        } else if (node.nodeInfo[0][3] == node.nodeInfo[1][2] && node.nodeInfo[1][2] == node.nodeInfo[2][1]
                || node.nodeInfo[1][2] == node.nodeInfo[2][1] && node.nodeInfo[2][1] == node.nodeInfo[3][0]) {
            // call determine user winner per character in array
            defultPlayerEnum = determineWinnerPerCharacter(node.nodeInfo[1][2]);
            return defultPlayerEnum;
        }
        // no one win so game status is tie
        return defultPlayerEnum;
    }

    // minmax method
    public static void minmax(Boolean isMaxUser, int depth, int alpha, int beta, int[] values, int nodeIndex) {
    }
}