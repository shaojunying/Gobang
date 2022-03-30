package com.shao;

import java.util.HashMap;
import java.util.Map;

/**
 * 坐标
 * @author shaojunying
 */
public class Coordinate {

    private final int x;
    private final int y;

    private static final Map<String, Coordinate> CACHE = new HashMap<>();

    private static final String SPLIT = ",";

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate getCoordinate(int x, int y) {
        if (CACHE.containsKey(x + SPLIT + y)) {
            return CACHE.get(x + SPLIT + y);
        }
        Coordinate coordinate = new Coordinate(x, y);
        CACHE.put(x + SPLIT + y, coordinate);
        return coordinate;
    }


    public static Coordinate getCoordinate(String coordinateStr) {
        String[] strings = coordinateStr.split("\\s+");
        return getCoordinate(Integer.parseInt(strings[0]) - 1, Integer.parseInt(strings[1]) - 1);
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x + 1, y + 1);
    }

    public Coordinate add(Coordinate coordinate) {
        return getCoordinate(x + coordinate.getX(), y + coordinate.getY());
    }

}
