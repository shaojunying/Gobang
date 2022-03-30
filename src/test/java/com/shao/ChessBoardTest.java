package com.shao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {

    /**
     * 测试只有一颗棋子的情况
     */
    @Test
    void testOnePiece() {
        ChessBoard chessBoard = new ChessBoard(10);
        chessBoard.setChess(Coordinate.getCoordinate(0, 0), Chess.getChess(Chess.BLACK));
        assertFalse(chessBoard.hasWinner());
    }

    /**
     * 测试有一个横向五子连珠的情况
     */
    @Test
    void testHorizontalFive() {
        ChessBoard chessBoard = new ChessBoard(10);

        chessBoard.setChess(new Coordinate(0, 0), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(1, 0), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(2, 0), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(3, 0), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(4, 0), Chess.getChess(Chess.BLACK));
        assertTrue(chessBoard.hasWinner());
    }

    /**
     * 测试有一个斜向五子连珠的情况
     */
    @Test
    void testDiagonalFive() {
        ChessBoard chessBoard = new ChessBoard(10);
        chessBoard.setChess(new Coordinate(0, 0), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(1, 1), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(2, 2), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(3, 3), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(4, 4), Chess.getChess(Chess.BLACK));
        assertTrue(chessBoard.hasWinner());
    }

    /**
     * 测试没有五子连珠的情况
     */
    @Test
    void testNoFive() {
        ChessBoard chessBoard = new ChessBoard(10);
        chessBoard.setChess(new Coordinate(0, 0), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(1, 1), Chess.getChess(Chess.WHITE));
        chessBoard.setChess(new Coordinate(2, 2), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(3, 3), Chess.getChess(Chess.WHITE));
        chessBoard.setChess(new Coordinate(4, 4), Chess.getChess(Chess.BLACK));
        assertFalse(chessBoard.hasWinner());
    }


    @Test
    void testToString() {
        ChessBoard chessBoard = new ChessBoard(10);
        chessBoard.setChess(new Coordinate(0, 0), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(1, 1), Chess.getChess(Chess.WHITE));
        chessBoard.setChess(new Coordinate(2, 2), Chess.getChess(Chess.BLACK));
        chessBoard.setChess(new Coordinate(3, 3), Chess.getChess(Chess.WHITE));
        chessBoard.setChess(new Coordinate(4, 4), Chess.getChess(Chess.BLACK));
        System.out.println(chessBoard.toString());
        String targetResult =
                "当前棋盘：\n" +
                "     1  2  3  4  5  6  7  8  9 10\n" +
                "  1  ●                           \n" +
                "  2     ○                        \n" +
                "  3        ●                     \n" +
                "  4           ○                  \n" +
                "  5              ●               \n" +
                "  6                              \n" +
                "  7                              \n" +
                "  8                              \n" +
                "  9                              \n" +
                " 10                              \n";
        assertEquals(targetResult, chessBoard.toString());
    }
}
