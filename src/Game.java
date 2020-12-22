
public class Game {

    // player in this game
    static enum playerEnum{
        ai,
        me,
    }

    // character symbol 
    public static String ai = "X";
    public static String me = "O";
    public static String playerTurn = ai;

    // alpha and beta count
    int alpha = -10000;
    int beta = +10000;

    // minmax method 
    public static void minmax(Boolean isMaxUser , int depth) {}
}