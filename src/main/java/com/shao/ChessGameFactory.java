package com.shao;

public class ChessGameFactory {

    public static ChessGame getChessGame(int size, boolean humanVsHuman, boolean isFirst) {
        Player player1 = null;
        Player player2 = null;
        if (isFirst) {
            player1 = new Player.HumanPlayer("黑子");
            if (humanVsHuman) {
                player2 = new Player.HumanPlayer("白子");
            }else {
                player2 = new Player.RobotPlayer("白子");
            }
        }else {
            player2 = new Player.HumanPlayer("白子");
            if (humanVsHuman) {
                player1 = new Player.HumanPlayer("黑子");
            }else {
                player1 = new Player.RobotPlayer("黑子");
            }
        }
        return new ChessGame.CommandLineChessGame(size, player1, player2);
    }

}
