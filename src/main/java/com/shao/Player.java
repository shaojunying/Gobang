package com.shao;

import java.util.Random;

/**
 * 玩家
 * @author shaojunying
 */
public abstract class Player {
    /**
     * 游戏本身
     */
    protected ChessGame chessGame;
    /**
     * 棋盘
     */
    protected ChessBoard chessBoard;
    /**
     * 玩家名
     */
    private String name;
    /**
     * 玩家的棋子颜色
     */
    private int color;

    public Player(String name) {
        this.name = name;
    }

    /**
     * 玩家以某种颜色加入一个棋局
     */
    public void join(ChessGame chessGame, int color) {
        this.name = name;
        this.chessGame = chessGame;
        this.chessBoard = chessGame.getChessBoard();
        this.color = color;
    }

    /**
     * 玩家执行一步落子操作
     */
    public void play() {
        beforePlay();
        Coordinate coordinate = getCoordinate();
        chessBoard.putChess(coordinate, Chess.getChess(color));
    }

    /**
     * 不同玩家可以重写该函数，从而在每次下棋前执行一些玩家相关操作
     */
    protected void beforePlay() {
        // do nothing
    }


    /**
     * 获取下一步落子的坐标
     */
    public abstract Coordinate getCoordinate();

    /**
     * 机器人玩家
     */
    public static class RobotPlayer extends Player {

        private final Random random = new Random();

        public RobotPlayer(String name) {
            super(name);
        }

        @Override
        public Coordinate getCoordinate() {
            int size = chessBoard.getSize();
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            Coordinate coordinate = Coordinate.getCoordinate(x, y);
            while (chessBoard.isOccupied(coordinate)) {
                x = random.nextInt(size);
                y = random.nextInt(size);
                coordinate = Coordinate.getCoordinate(x, y);
            }
            return coordinate;
        }
    }

    /**
     * 真人玩家
     */
    public static class HumanPlayer extends Player {

        public HumanPlayer(String name) {
            super(name);
        }

        @Override
        public Coordinate getCoordinate() {
            return chessGame.getCoordinateFromInput();
        }

        /**
         * 真人在每回合进行前需要提示用户操作
         */
        @Override
        protected void beforePlay() {
            chessGame.showPrompt(this.getName());
        }
    }

    /**
     * 玩家姓名
     * @return
     */
    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
