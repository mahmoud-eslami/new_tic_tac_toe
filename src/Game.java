
public class Game {

    // character symbol
    public static String ai = "x";
    public static String me = "o";
    public static String playerTurn = ai;

    // alpha and beta count
    // ! alpha is best max value for maximize player
    // {fa} بزرگترین مقدار برای بازیکن بیشینه
    static int staticAlpha = -1000;
    // ! beta is best min value for minimize player
    // {fa} کوچکترین مقدار برای بازیکن کمینه
    static int staticBeta = +1000;

    // check move remaining
    // ! if this method return false that mean game is finished s
    // {fa} در این متد چک میکنیم ایا خانه از صفحه برای ادامه بازی خالی هست یا خیر
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

    // ! check winner of the game
    // {fa} در این متد یک استیت از بازی رو میگیرم چک میکنیم کسی برنده هست یا نه
    public static int checkWinner(Node node) {

        // ! check row winner
        // {fa} به صورت سطری تموم سطر های ممکن صفحه رو تست میکنیم
        for (int i = 0; i < 4; i++) {
            if (node.nodeInfo[i][0] == node.nodeInfo[i][1] && node.nodeInfo[i][1] == node.nodeInfo[i][2]
                    || node.nodeInfo[i][1] == node.nodeInfo[i][2] && node.nodeInfo[i][2] == node.nodeInfo[i][3]) {
                if (node.nodeInfo[i][1] == me) {
                    return -100;
                } else if (node.nodeInfo[i][1] == ai) {
                    return +100;
                }
            }
        }
        // ! check column winner
        // {fa} به صورت ستونی تموم ستون های ممکن صفحه رو تست میکنیم
        for (int j = 0; j < 4; j++) {
            if (node.nodeInfo[0][j] == node.nodeInfo[1][j] && node.nodeInfo[1][j] == node.nodeInfo[2][j]
                    || node.nodeInfo[1][j] == node.nodeInfo[2][j] && node.nodeInfo[2][j] == node.nodeInfo[3][j]) {
                if (node.nodeInfo[1][j] == me) {
                    return -100;
                } else if (node.nodeInfo[1][j] == ai) {
                    return +100;
                }
            }
        }
        // ! check diametric winner
        // {fa} به صورت قطری تموم قطر های ممکن صفحه رو تست میکنیم
        if (node.nodeInfo[0][0] == node.nodeInfo[1][1] && node.nodeInfo[1][1] == node.nodeInfo[2][2]
                || node.nodeInfo[1][1] == node.nodeInfo[2][2] && node.nodeInfo[2][2] == node.nodeInfo[3][3]) {

            if (node.nodeInfo[1][1] == me) {
                return -100;
            } else if (node.nodeInfo[1][1] == ai) {
                return +100;
            }

        } else if (node.nodeInfo[0][3] == node.nodeInfo[1][2] && node.nodeInfo[1][2] == node.nodeInfo[2][1]
                || node.nodeInfo[1][2] == node.nodeInfo[2][1] && node.nodeInfo[2][1] == node.nodeInfo[3][0]) {

            if (node.nodeInfo[1][2] == me) {
                return -100;
            } else if (node.nodeInfo[1][2] == ai) {
                return +100;
            }

        } else if (node.nodeInfo[1][0] == node.nodeInfo[2][1] && node.nodeInfo[2][1] == node.nodeInfo[3][2]) {

            if (node.nodeInfo[2][1] == me) {
                return -100;
            } else if (node.nodeInfo[2][1] == ai) {
                return +100;
            }

        } else if (node.nodeInfo[0][1] == node.nodeInfo[1][2] && node.nodeInfo[1][2] == node.nodeInfo[2][3]) {

            if (node.nodeInfo[1][2] == me) {
                return -100;
            } else if (node.nodeInfo[1][2] == ai) {
                return +100;
            }
        } else if (node.nodeInfo[1][3] == node.nodeInfo[2][2] && node.nodeInfo[2][2] == node.nodeInfo[3][1]) {
            if (node.nodeInfo[2][2] == me) {
                return -100;
            } else if (node.nodeInfo[2][2] == ai) {
                return +100;
            }
        }
        // ! no one win so game status is tie
        // {fa} کسی بازی رو نبرده هنوز
        return 0;
    }

    public static int minimax(Boolean isMax, int depth, Node node, int myBeta, int myAlpha) {

        int winner = checkWinner(node);
        int[] xPosition = { 0, 1, 0, -1 };
        int[] yPosition = { 1, 0, -1, 0 };

        if (winner == +100) {
            return 100 - depth;
        } else if (winner == -100) {
            return -100 + depth;
        }

        if (!canMoveTile(node)) {
            return 0;
        }

        if (isMax) {
            int bestVal = staticAlpha;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    node.nodeInfo[i][j] = ai;

                    for (int k = 0; k < 3; k++) {
                        if (node.nodeInfo[xPosition[k] + i][yPosition[k] + j] == "-" && xPosition[k] + i >= 0
                        && xPosition[k] + i <= 3 && xPosition[k] + j >= 0 && xPosition[k] + j <= 3) {

                            node.nodeInfo[xPosition[k]][yPosition[k]] = me;
                            bestVal = Math.max(bestVal, minimax(false, depth + 1, node, myBeta, myAlpha));
                            myAlpha = Math.max(bestVal, myAlpha);
                            if (myBeta <= myAlpha) {
                                break;
                            }
                        }
                    }
                    node.nodeInfo[i][j] = "-";
                }
            }
            return bestVal;

        } else {
            int bestVal = staticBeta;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    node.nodeInfo[i][j] = me;

                    for (int k = 0; k < 3; k++) {
                        if (node.nodeInfo[xPosition[k] + i][yPosition[k] + j] == "-" && xPosition[k] + i >= 0
                                && xPosition[k] + i <= 3 && xPosition[k] + j >= 0 && xPosition[k] + j <= 3) {

                            node.nodeInfo[xPosition[k]][yPosition[k]] = ai;
                            bestVal = Math.min(bestVal, minimax(true, depth + 1, node, myBeta, myAlpha));
                            myBeta = Math.min(bestVal, myBeta);
                            node.nodeInfo[xPosition[k]][yPosition[k]] = "-";
                            if (myBeta <= myAlpha) {
                                break;
                            }
                        }
                    }

                    node.nodeInfo[i][j] = "-";

                }
            }
            return bestVal;
        }
    }

    // // minmax method
    // // {fa}
    // public static int minmax(int depth, Node node, boolean isMaxUser, int myBeta,
    // int myAlpha) {

    // int[] xIterable = { 0, 1, 0, -1 };
    // int[] yIterable = { 1, 0, -1, 0 };

    // // {fa}
    // int count = checkWinner(node);

    // // {fa}
    // if (count == +100) {
    // return count - depth;
    // } else if (count == -100) {
    // return count + depth;
    // }

    // // {fa}
    // if (canMoveTile(node) == false || depth > 5) {
    // return 0;
    // }

    // // here complete minmax method
    // // {fa}
    // if (isMaxUser) {

    // int bestVal = alpha;

    // for (int i = 0; i < 4; i++) {
    // for (int j = 0; j < 4; j++) {
    // if (node.nodeInfo[i][j] == "-") {
    // node.nodeInfo[i][j] = ai;

    // for (int k = 0; k < 4; k++) {

    // if (node.nodeInfo[i + xIterable[k]][j + yIterable[k]] == "-" && i +
    // xIterable[k] >= 0
    // && i + xIterable[k] <= 3 && j + yIterable[k] >= 0 && j + yIterable[k] <= 3) {

    // node.nodeInfo[i + xIterable[k]][j + yIterable[k]] = me;
    // bestVal = Math.max(bestVal, minmax(depth + 1, node, false, beta, alpha));
    // myAlpha = Math.max(myAlpha, bestVal);
    // node.nodeInfo[i + xIterable[k]][j + yIterable[k]] = "-";
    // if (myBeta <= myAlpha) {
    // break;
    // }

    // }
    // }
    // node.nodeInfo[i][j] = "-";
    // }
    // }
    // }
    // return bestVal;
    // } else {
    // // {fa}
    // int bestVal = beta;

    // for (int i = 0; i < 4; i++) {
    // for (int j = 0; j < 4; j++) {
    // if (node.nodeInfo[i][j] == "-") {
    // node.nodeInfo[i][j] = me;

    // for (int k = 0; k < 4; k++) {

    // if (node.nodeInfo[i + xIterable[k]][j + yIterable[k]] == "-" && i +
    // xIterable[k] >= 0
    // && i + xIterable[k] <= 3 && j + yIterable[k] >= 0 && j + yIterable[k] <= 3) {

    // node.nodeInfo[i + xIterable[k]][j + yIterable[k]] = ai;
    // bestVal = Math.min(bestVal, minmax(depth + 1, node, true, beta, alpha));
    // myBeta = Math.min(myAlpha, bestVal);
    // node.nodeInfo[i + xIterable[k]][j + yIterable[k]] = "-";
    // if (myBeta <= myAlpha) {
    // break;
    // }

    // }
    // }
    // node.nodeInfo[i][j] = "-";
    // }
    // }
    // }
    // return bestVal;
    // }
    // }

    // public static void findBestMoveForAi(Node node) {
    // int bestVal = alpha;
    // Origin xOrigin = new Origin();
    // Origin yOrigin = new Origin();

    // for (int i = 0; i < 4; i++) {
    // for (int j = 0; j < 4; j++) {
    // node.nodeInfo[i][j] = ai;

    // int movationValue = minmax(0, node, true, beta, alpha);

    // node.nodeInfo[i][j] = "-";

    // if(movationValue > bestVal) {
    // xOrigin.x = i;
    // xOrigin.y = j;
    // bestVal = movationValue;
    // }
    // }
    // }
    // node.nodeInfo[xOrigin.x][xOrigin.y] = ai;
    // }

}