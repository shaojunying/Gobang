package com.shao;

import java.util.Scanner;

/**
 * 五子棋游戏
 *
 * @author shaojunying
 */
public abstract class ChessGame {

    protected final ChessBoard chessBoard;
    private final Player blackPlayer;
    private final Player whitePlayer;

    protected Player curPlayer;

    protected ChessGame(int size, Player blackPlayer, Player whitePlayer) {
        this.chessBoard = new ChessBoard(size);
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        blackPlayer.join(this, Chess.BLACK);
        whitePlayer.join(this, Chess.WHITE);
        curPlayer = blackPlayer;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    /**
     * 从输入获取一组坐标
     */
    public abstract Coordinate getCoordinateFromInput();

    /**
     * 真人玩家会在每次下棋之前调用这个函数给用户提示
     */
    public void showPrompt(String name) {
        // do nothing
    }

    /**
     * 主函数，游戏循环都在这个函数中进行
     */
    public void start() {
        init();
        while (!chessBoard.hasWinner() && !chessBoard.isFull()) {
            beforePlay();
            curPlayer.play();
            afterPlay();
            curPlayer = curPlayer == blackPlayer ? whitePlayer : blackPlayer;
        }
        if (chessBoard.hasWinner()) {
            System.out.println((curPlayer == blackPlayer ? whitePlayer : blackPlayer).getName() + " 胜利!");
        }
        if (chessBoard.isFull()) {
            System.out.println("棋盘已被占满，平局!");
        }
    }

    /**
     * 在开始游戏前的初始化工作
     */
    protected void init() {
        // do nothing
    }

    /**
     * 在每个玩家下棋之前要做的事情
     */
    protected void beforePlay() {
    }

    /**
     * 在每个玩家下棋之后要做的事情
     */
    protected void afterPlay() {
    }


    /**
     * 命令行贪吃蛇游戏
     */
    public static class CommandLineChessGame extends ChessGame {

        public CommandLineChessGame(int size, Player blackPlayer, Player whitePlayer) {
            super(size, blackPlayer, whitePlayer);
        }

        @Override
        public Coordinate getCoordinateFromInput() {
            System.out.println("请输入坐标，格式为：x y");
            String coordinateStr = new Scanner(System.in).nextLine();
            Coordinate coordinate = Coordinate.getCoordinate(coordinateStr);
            while (true) {
                if (!chessBoard.isValid(coordinate)) {
                    System.out.printf("请输入合法的坐标，范围是1~%d，格式为：x y%n", chessBoard.getSize());
                } else if (chessBoard.isOccupied(coordinate)) {
                    System.out.println("该位置已经有棋子了，请重新输入坐标，格式为：x y");
                } else {
                    return coordinate;
                }
                coordinateStr = new Scanner(System.in).nextLine();
                coordinate = Coordinate.getCoordinate(coordinateStr);
            }
        }

        @Override
        protected void init() {
            System.out.println("欢迎来到五子棋游戏！");
            System.out.println(chessBoard);
        }

        @Override
        protected void beforePlay() {
            System.out.println("--------------------------------------------------------");
        }

        @Override
        protected void afterPlay() {
            // 打印棋盘
            System.out.println(curPlayer.getName() + "在" + chessBoard.getPreviousCoordinate() + "落子" + Chess.CHESS_TYPE.get(curPlayer.getColor()) + "成功！");
            System.out.println(chessBoard);
        }


        @Override
        public void showPrompt(String name) {
            System.out.println("请" + name + "落子!");
        }

    }
}
