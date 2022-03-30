package com.shao;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择人机对战还是人人对战：1.人机对战 2.人人对战（默认为1.人机模式）：");
        String mode = scanner.nextLine();
        boolean isHumanVsHuman = "2".equals(mode.trim());
        System.out.println("您的选择是：" + (isHumanVsHuman ? "人人对战" : "人机对战"));
        System.out.println("请选择自己是否先手：1.先手 2.后手（默认为1.先手）：");
        String sequence = scanner.nextLine();
        boolean isFirst = !"2".equals(sequence.trim());
        System.out.println("您的选择是：" + (isFirst ? "先手" : "后手"));

        ChessGame game = ChessGameFactory.getChessGame(10, isHumanVsHuman, isFirst);
        game.start();
    }
}
