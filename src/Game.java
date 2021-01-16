
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
        int[] iIteration = { 0, 1, 0, -1 };
        int[] jIteration = { 1, 0, -1, 0 };

        if (winner == +100) {
            return 100 - depth;
        } else if (winner == -100) {
            return -100 + depth;
        }

        if (!canMoveTile(node)) {
            return 0;
        }

        if (isMax) {
            int value = staticAlpha;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (node.nodeInfo[i][j] == "-") {
                        node.nodeInfo[i][j] = ai;
                        for (int k = 0; k < 4; k++) {
                            if (i + iIteration[k] >= 0 && i + iIteration[k] < 4 && j + jIteration[k] >= 0
                                    && j + jIteration[k] < 4) {
                                if (node.nodeInfo[i + iIteration[k]][j + jIteration[k]] == "-") {

                                    node.nodeInfo[i + iIteration[k]][j + jIteration[k]] = me;

                                    value = Math.max(value, minimax(!isMax, depth + 1, node, myBeta, myAlpha));
                                    myAlpha = Math.max(myAlpha, value);

                                    node.nodeInfo[i + iIteration[k]][j + jIteration[k]] = "-";

                                    if (myAlpha >= myBeta) {
                                        break;
                                    }
                                }
                            }
                        }
                        node.nodeInfo[i][j] = "-";
                    }
                }
            }
            return value;
        } else {
            int value = staticBeta;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (node.nodeInfo[i][j] == "-") {
                        node.nodeInfo[i][j] = me;
                        for (int k = 0; k < 4; k++) {
                            if (i + iIteration[k] >= 0 && i + iIteration[k] < 4 && j + jIteration[k] >= 0
                                    && j + jIteration[k] < 4) {
                                if (node.nodeInfo[i + iIteration[k]][j + jIteration[k]] == "-") {

                                    node.nodeInfo[i + iIteration[k]][j + jIteration[k]] = ai;

                                    value = Math.min(value, minimax(!isMax, depth + 1, node, myBeta, myAlpha));
                                    myBeta = Math.min(myBeta, value);

                                    node.nodeInfo[i + iIteration[k]][j + jIteration[k]] = "-";

                                    if (myAlpha >= myBeta) {
                                        break;
                                    }
                                }
                            }
                        }
                        node.nodeInfo[i][j] = "-";
                    }
                }
            }
            return value;
        }
    }

    public static void findBestMove(Node node) {
        int value = staticAlpha;
        Origin xOrig = new Origin();
        Origin oOrig = new Origin();

        int[] iIteration = { 0, 1, 0, -1 };
        int[] jIteration = { 1, 0, -1, 0 };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (node.nodeInfo[i][j] == "-") {
                    node.nodeInfo[i][j] = ai;
                    for (int k = 0; k < 4; k++) {
                        if (i + iIteration[k] >= 0 && i + iIteration[k] < 4 && j + jIteration[k] >= 0
                                && j + jIteration[k] < 4) {
                            if (node.nodeInfo[i + iIteration[k]][j + jIteration[k]] == "-") {

                                node.nodeInfo[i + iIteration[k]][j + jIteration[k]] = me;

                                int movationValue = minimax(true, 0, node, staticAlpha, staticBeta);

                                node.nodeInfo[i + iIteration[k]][j + jIteration[k]] = "-";

                                if (movationValue > value) {
                                    oOrig.x = i + iIteration[k];
                                    oOrig.y = j + jIteration[k];
                                    xOrig.x = i;
                                    xOrig.y = j;
                                    value = movationValue;
                                }
                            }
                        }
                    }
                    node.nodeInfo[i][j] = "-";
                }
            }
        }
        node.nodeInfo[xOrig.x][xOrig.y] = ai;
        node.nodeInfo[oOrig.x][oOrig.y] = me;
    }
}