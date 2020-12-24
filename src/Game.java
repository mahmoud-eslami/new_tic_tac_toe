
public class Game {

    // character symbol
    public static String ai = "x";
    public static String me = "o";
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

    public static int checkWinner(Node node) {
        // check row winner
        for (int i = 0; i < 4; i++) {
            if (node.nodeInfo[i][0] == node.nodeInfo[i][1] && node.nodeInfo[i][1] == node.nodeInfo[i][2]
                    || node.nodeInfo[i][1] == node.nodeInfo[i][2] && node.nodeInfo[i][2] == node.nodeInfo[i][3]) {
                if (node.nodeInfo[i][1] == me) {
                    return -20;
                } else if (node.nodeInfo[i][1] == ai) {
                    return +20;
                }
            }
        }
        // check column winner
        for (int j = 0; j < 4; j++) {
            if (node.nodeInfo[0][j] == node.nodeInfo[1][j] && node.nodeInfo[1][j] == node.nodeInfo[2][j]
                    || node.nodeInfo[1][j] == node.nodeInfo[2][j] && node.nodeInfo[2][j] == node.nodeInfo[3][j]) {
                if (node.nodeInfo[1][j] == me) {
                    return -20;
                } else if (node.nodeInfo[1][j] == ai) {
                    return +20;
                }
            }
        }
        // check diametric winner
        if (node.nodeInfo[0][0] == node.nodeInfo[1][1] && node.nodeInfo[1][1] == node.nodeInfo[2][2]
                || node.nodeInfo[1][1] == node.nodeInfo[2][2] && node.nodeInfo[2][2] == node.nodeInfo[3][3]) {

            if (node.nodeInfo[1][1] == me) {
                return -20;
            } else if (node.nodeInfo[1][1] == ai) {
                return +20;
            }

        } else if (node.nodeInfo[0][3] == node.nodeInfo[1][2] && node.nodeInfo[1][2] == node.nodeInfo[2][1]
                || node.nodeInfo[1][2] == node.nodeInfo[2][1] && node.nodeInfo[2][1] == node.nodeInfo[3][0]) {

            if (node.nodeInfo[1][2] == me) {
                return -20;
            } else if (node.nodeInfo[1][2] == ai) {
                return +20;
            }

        } else if (node.nodeInfo[1][0] == node.nodeInfo[2][1] && node.nodeInfo[2][1] == node.nodeInfo[3][2]) {

            if (node.nodeInfo[2][1] == me) {
                return -20;
            } else if (node.nodeInfo[2][1] == ai) {
                return +20;
            }

        } else if (node.nodeInfo[0][1] == node.nodeInfo[1][2] && node.nodeInfo[1][2] == node.nodeInfo[2][3]) {

            if (node.nodeInfo[1][2] == me) {
                return -20;
            } else if (node.nodeInfo[1][2] == ai) {
                return +20;
            }
        } else if (node.nodeInfo[1][3] == node.nodeInfo[2][2] && node.nodeInfo[2][2] == node.nodeInfo[3][1]) {
            if (node.nodeInfo[2][2] == me) {
                return -20;
            } else if (node.nodeInfo[2][2] == ai) {
                return +20;
            }
        }
        // no one win so game status is tie
        return 0;
    }

    // minmax method
    public static int minmax(Boolean isMaxUser, int depth, Node node) {
        int count = checkWinner(node);

        if (count == +20) {
            return count;
        } else if (count == -20) {
            return count;
        }

        if (canMoveTile(node) == false) {
            return 0;
        }

        // here complete minmax method

        if (isMaxUser == true) {

            int bestVal = alpha;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (node.nodeInfo[i][j] == "-") {
                        node.nodeInfo[i][j] = ai;
                        bestVal = Math.max(bestVal, minmax(!isMaxUser, depth + 1, node));
                        node.nodeInfo[i][j] = "-";
                    }
                }
            }
            return bestVal;
        } else {

            int bestVal = beta;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (node.nodeInfo[i][j] == "-") {
                        node.nodeInfo[i][j] = me;
                        bestVal = Math.min(bestVal, minmax(!isMaxUser, depth + 1, node));
                        node.nodeInfo[i][j] = "-";
                    }
                }
            }
            return bestVal;
        }
    }

    public static Origin findAiMoveX(Node node) {

        int alphaVal = alpha;

        Origin moveOrigin = new Origin();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (node.nodeInfo[i][j] == "-") {
                    node.nodeInfo[i][j] = ai;

                    int moveVal = minmax(false, 0, node);

                    node.nodeInfo[i][j] = "-";

                    if (moveVal > alphaVal) {
                        moveOrigin.x = i;
                        moveOrigin.y = j;
                        alphaVal = moveVal;
                    }
                }
            }
        }
        return moveOrigin;
    }

    public static Origin findAiMoveO(Origin origin){

        int betaVal = beta;

        int[] xItrable = {0,1,0,-1};
        int[] yItrable = {1,0,-1,0};
        Origin oOrigin = new Origin();

        for (int i = 0; i < xItrable.length; i++) {
            for (int j = 0; j < yItrable.length; j++) {
                
            }
        }
        return oOrigin;
    }
}