package com.java;

import com.java.dto.Turn;

public class Player {
    private String name;
    private Turn turn;
    private Integer moveCount = 0;
    private Integer currPosition = 0;
    private Board board;

    public Player(String name, Turn turn, Board board) {
        this.name = name;
        this.turn = turn;
        this.board = board;
    }

    public void play() throws InterruptedException {
        synchronized (turn) {
            while (Boolean.TRUE) {
                turn.wait();
                if (!turn.getLive())
                    break;
                if (turn.getPlayerTurn()) {
                    currPosition = board.getNextPosition(currPosition);
                    moveCount++;
                }
                System.out.println("player name " + name + " currPosition " + currPosition);
            }
        }
    }

    public Integer getMoveCount() {
        return moveCount;
    }

    public Integer getCurrPosition() {
        return currPosition;
    }

    public String getName() {
        return name;
    }
}
