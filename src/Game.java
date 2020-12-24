
public class Game {

    // character symbol
    public static String ai = "x";
    public static String me = "o";
    public static String playerTurn = ai;

    // alpha and beta count
    // ! alpha is best max value for maximize player
    // {fa} بزرگترین مقدار برای بازیکن بیشینه
    static int alpha = -10000;
    // ! beta is best min value for minimize player
    // {fa} کوچکترین مقدار برای بازیکن کمینه
    static int beta = +10000;

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
                    return -10;
                } else if (node.nodeInfo[i][1] == ai) {
                    return +10;
                }
            }
        }
        // ! check column winner
        // {fa} به صورت ستونی تموم ستون های ممکن صفحه رو تست میکنیم
        for (int j = 0; j < 4; j++) {
            if (node.nodeInfo[0][j] == node.nodeInfo[1][j] && node.nodeInfo[1][j] == node.nodeInfo[2][j]
                    || node.nodeInfo[1][j] == node.nodeInfo[2][j] && node.nodeInfo[2][j] == node.nodeInfo[3][j]) {
                if (node.nodeInfo[1][j] == me) {
                    return -10;
                } else if (node.nodeInfo[1][j] == ai) {
                    return +10;
                }
            }
        }
        // ! check diametric winner
        // {fa} به صورت قطری تموم قطر های ممکن صفحه رو تست میکنیم
        if (node.nodeInfo[0][0] == node.nodeInfo[1][1] && node.nodeInfo[1][1] == node.nodeInfo[2][2]
                || node.nodeInfo[1][1] == node.nodeInfo[2][2] && node.nodeInfo[2][2] == node.nodeInfo[3][3]) {

            if (node.nodeInfo[1][1] == me) {
                return -10;
            } else if (node.nodeInfo[1][1] == ai) {
                return +10;
            }

        } else if (node.nodeInfo[0][3] == node.nodeInfo[1][2] && node.nodeInfo[1][2] == node.nodeInfo[2][1]
                || node.nodeInfo[1][2] == node.nodeInfo[2][1] && node.nodeInfo[2][1] == node.nodeInfo[3][0]) {

            if (node.nodeInfo[1][2] == me) {
                return -10;
            } else if (node.nodeInfo[1][2] == ai) {
                return +10;
            }

        } else if (node.nodeInfo[1][0] == node.nodeInfo[2][1] && node.nodeInfo[2][1] == node.nodeInfo[3][2]) {

            if (node.nodeInfo[2][1] == me) {
                return -10;
            } else if (node.nodeInfo[2][1] == ai) {
                return +10;
            }

        } else if (node.nodeInfo[0][1] == node.nodeInfo[1][2] && node.nodeInfo[1][2] == node.nodeInfo[2][3]) {

            if (node.nodeInfo[1][2] == me) {
                return -10;
            } else if (node.nodeInfo[1][2] == ai) {
                return +10;
            }
        } else if (node.nodeInfo[1][3] == node.nodeInfo[2][2] && node.nodeInfo[2][2] == node.nodeInfo[3][1]) {
            if (node.nodeInfo[2][2] == me) {
                return -10;
            } else if (node.nodeInfo[2][2] == ai) {
                return +10;
            }
        }
        // ! no one win so game status is tie
        // {fa} کسی بازی رو نبرده هنوز
        return 0;
    }

    // minmax method
    // {fa}
    public static int minmax(int depth, Node node, boolean isMaxUser) {

        // {fa}
        int count = checkWinner(node);

        // {fa}
        if (count == +10) {
            return count;
        } else if (count == -10) {
            return count;
        }

        // {fa}
        if (canMoveTile(node) == false || depth > 5) {
            return 0;
        }

        // here complete minmax method
        // {fa}
        if (isMaxUser) {

            int bestVal = alpha;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (node.nodeInfo[i][j] == "-") {
                        node.nodeInfo[i][j] = ai;
                        bestVal = Math.max(bestVal, minmax(depth + 1, node, false));
                        node.nodeInfo[i][j] = "-";
                    }
                }
            }
            return bestVal;
        } else {
            // {fa}
            int bestVal = beta;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (node.nodeInfo[i][j] == "-") {
                        node.nodeInfo[i][j] = me;
                        bestVal = Math.min(bestVal, minmax(depth + 1, node, true));
                        node.nodeInfo[i][j] = "-";
                    }
                }
            }
            return bestVal;
        }
    }

    // {fa}
    public static Origin findAiMoveX(Node node) {

        int alphaVal = alpha;

        Origin moveOrigin = new Origin();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (node.nodeInfo[i][j] == "-") {
                    node.nodeInfo[i][j] = ai;

                    int moveVal = minmax(0, node, false);

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

    // {fa}
    public static Origin findAiMoveO(Origin origin, Node node) {

        int betaVal = beta;

        int[] xItrable = { 0, 1, 0, -1 };
        int[] yItrable = { 1, 0, -1, 0 };
        Origin oOrigin = new Origin();

        for (int i = 0; i < 4; i++) {
            if (origin.x + xItrable[i] >= 0 && origin.x + xItrable[i] <= 3 && origin.y + yItrable[i] >= 0
                    && origin.y + yItrable[i] <= 3) {
                if (node.nodeInfo[origin.x + xItrable[i]][origin.y + yItrable[i]] == "-") {
                    node.nodeInfo[origin.x + xItrable[i]][origin.y + yItrable[i]] = me;

                    int moveVal = minmax(0, node, true);

                    node.nodeInfo[origin.x + xItrable[i]][origin.y + yItrable[i]] = "-";

                    if (moveVal < betaVal) {
                        oOrigin.x = origin.x + xItrable[i];
                        oOrigin.y = origin.y + yItrable[i];
                        betaVal = moveVal;
                    }
                }
            }
        }
        return oOrigin;
    }
}