package com.shao;

/**
 * 棋盘
 *
 * @author shaojunying
 */
public class ChessBoard {

    /**
     * 胜利需要的几子连珠
     */
    private static final int WIN_NUM = 5;

    /**
     * 棋盘的矩阵
     */
    private final Chess[][] chessesMatrix;
    /**
     * 当前已落子的棋子数
     */
    private int chesses = 0;
    /**
     * 上一个落子的棋子的位置
     */
    private Coordinate previousCoordinate;

    /**
     * @param size 棋盘的大小
     */
    public ChessBoard(int size) {
        this.chessesMatrix = new Chess[size][size];
    }

    /**
     * 向棋盘上放一颗棋子, 如果要放置的位置已有棋子，则放置失败，返回false；否则返回true。
     */
    public boolean putChess(Coordinate coordinate, Chess chess) {
        if (getChess(coordinate) != null) {
            return false;
        }
        setChess(coordinate, chess);
        previousCoordinate = coordinate;
        chesses++;
        return true;
    }

    /**
     * 判断是否有五子连珠
     */
    public boolean hasWinner() {
        int n = chessesMatrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hasWinner(new Coordinate(i, j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final Coordinate[] DIRS = new Coordinate[]{
            Coordinate.getCoordinate(0, -1),
            Coordinate.getCoordinate(-1, -1),
            Coordinate.getCoordinate(-1, 0),
            Coordinate.getCoordinate(-1, 1)
    };

    /**
     * 对于每个子，分别向其左、左上、上、右上四个方向延伸，判断是否有五子连珠
     */
    private boolean hasWinner(Coordinate coordinate) {
        Chess targetChess = getChess(coordinate);
        if (targetChess == null) {
            return false;
        }
        for (Coordinate dir : DIRS) {
            boolean success = true;
            Coordinate curCoordinate = coordinate;
            for (int i = 0; i < WIN_NUM - 1; i++) {
                curCoordinate = curCoordinate.add(dir);
                // 坐标是否合法
                if (!isValid(curCoordinate)) {
                    success = false;
                    break;
                }
                if (getChess(curCoordinate) == null || !targetChess.equals(getChess(curCoordinate))) {
                    success = false;
                    break;
                }
            }
            if (success) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取棋盘上某个位置的棋子
     * @param coordinate
     * @return
     */
    public Chess getChess(Coordinate coordinate) {
        check(coordinate);
        return chessesMatrix[coordinate.getX()][coordinate.getY()];
    }

    /**
     * 设置棋盘上某个位置的棋子
     * @param coordinate
     * @param chess
     */
    public void setChess(Coordinate coordinate, Chess chess) {
        check(coordinate);
        chessesMatrix[coordinate.getX()][coordinate.getY()] = chess;
    }

    /**
     * 判断棋盘是否满了
     */
    public boolean isFull() {
        return chesses == chessesMatrix.length * chessesMatrix.length;
    }

    /**
     * 校验坐标是否合法，不合法则抛出异常
     */
    private void check(Coordinate coordinate) {
        if (!isValid(coordinate)) {
            throw new IllegalArgumentException("输入的坐标为" + coordinate + "，不在棋盘上");
        }
    }

    /**
     * 判断坐标是否合法
     */
    public boolean isValid(Coordinate coordinate) {
        return isValidIndex(coordinate.getX()) && isValidIndex(coordinate.getY());
    }


    private boolean isValidIndex(int index) {
        return index >= 0 && index < chessesMatrix.length;
    }

    /**
     * 获取棋盘的大小
     */
    public int getSize() {
        return chessesMatrix.length;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("当前棋盘：\n");
        // 第一行绘制数字
        StringBuilder builder = new StringBuilder("   ");
        for (int i = 0; i < chessesMatrix.length; i++) {
            builder.append(String.format("%3d", i + 1));
        }
        res.append(builder).append("\n");
        for (int i = 0; i < chessesMatrix.length; i++) {
            // 第i行
            builder = new StringBuilder(String.format("%3d", i + 1));
            for (int j = 0; j < chessesMatrix.length; j++) {
                builder.append("| ");
                if (chessesMatrix[i][j] == null) {
                    builder.append(" ");
                } else {
                    builder.append(Chess.CHESS_TYPE.get(chessesMatrix[i][j].getColor()));
                }
            }
            builder.append("|");
            res.append(builder).append("\n");
        }
        return res.toString();
    }

    /**
     * 获取上一步落子的坐标
     */
    public Coordinate getPreviousCoordinate() {
        return previousCoordinate;
    }

    public boolean isOccupied(Coordinate coordinate) {
        return chessesMatrix[coordinate.getX()][coordinate.getY()] != null;
    }
}
