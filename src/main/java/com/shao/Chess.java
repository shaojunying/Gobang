package com.shao;

import java.util.HashMap;
import java.util.Map;

/**
 * 棋子
 * @author shaojunying
 */
public class Chess {

    private final int color;
    public Chess(int color) {
        this.color = color;
    }

    public static final int BLACK = 0;
    public static final int WHITE = 1;

    public static final Map<Integer, String> CHESS_TYPE = new HashMap<>();
    static {
        CHESS_TYPE.put(BLACK, "○");
        CHESS_TYPE.put(WHITE, "●");
    }

    private static final Map<Integer, Chess> CACHE = new HashMap<>();

    /**
     * 尽可能使用该方法获取棋子，而不是构造器，利用缓存来提高性能
     */
    public static Chess getChess(int color) {
        if (CACHE.containsKey(color)) {
            return CACHE.get(color);
        }
        if (color == BLACK) {
            Chess chess = new BlackChess();
            CACHE.put(color, chess);
            return chess;
        }else if (color == WHITE) {
            Chess chess = new WhiteChess();
            CACHE.put(color, chess);
            return chess;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Chess{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Chess chess = (Chess) object;
        return color == chess.color;
    }

    public static class WhiteChess extends Chess {
        public WhiteChess() {
            super(Chess.WHITE);
        }
    }

    public static class BlackChess extends Chess {
        public BlackChess() {
            super(Chess.BLACK);
        }
    }

    public int getColor() {
        return color;
    }
}
