package com.java;

import com.java.dto.ShortestPathDTO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static com.java.Constants.*;

public class Board {

    private String id;
    private Integer shortestPath = Integer.MAX_VALUE;
    // (k,v) --> (head,tail)
    private Map<Integer, Snake> snakes;
    // (k,v) --> (tail,head)
    private Map<Integer, Ladder> ladders;
    private Dice dice;

    public Board(String id, Dice dice) {
        this.id = id;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.dice = dice;
    }

    public void generate(Integer numOfSnake, Integer numOfLadder) {
        int snakehead;
        int ladderTail;
        int i = numOfSnake;
        while (i > 0) {
            snakehead = getRandomNumber(LOWEST_POINT, HIGHEST_POINT);
            if (snakes.containsKey(snakehead))
                continue;
            snakes.put(snakehead, getSnake(snakehead, getRandomNumber(LOWEST_POINT, snakehead)));
            i--;
        }
        i = numOfLadder;
        while (i > 0) {
            ladderTail = getRandomNumber(LOWEST_POINT, HIGHEST_POINT);
            // snake's head and ladder's tail should not be same
            if (snakes.containsKey(ladderTail) || ladders.containsKey(ladderTail))
                continue;
            ladders.put(ladderTail, getLadder(getRandomNumber(ladderTail + 1, HIGHEST_POINT), ladderTail));
            i--;
        }
        System.out.println("--------- snake -----------");
        for (Map.Entry<Integer, Snake> entry : snakes.entrySet()) {
            System.out.println(" snake head " + entry.getKey() + " tail " + entry.getValue().getTail());
        }
        System.out.println("--------- ladder  -----------");
        for (Map.Entry<Integer, Ladder> entry : ladders.entrySet()) {
            System.out.println(" ladder tail " + entry.getKey() + " head " + entry.getValue().getHead());
        }

        findShortestPath();
    }

    private void findShortestPath() {
        System.out.println("Calculating shortest path......");
        // BFS
        Queue<ShortestPathDTO> queue = new LinkedList<>();
        queue.add(new ShortestPathDTO(ZERO, ZERO, String.valueOf(ZERO)));

        while (!queue.isEmpty()) {
            ShortestPathDTO shortestPathDTO = queue.poll();
            Integer temp = shortestPathDTO.getCurrentPoint();
            Integer prevMoveCount = shortestPathDTO.getMoveCount();

            if (temp >= HIGHEST_POINT) {
                System.out.println(" shortest move count " + prevMoveCount);
                System.out.println(" shortest path  " + shortestPathDTO.getPath());
                shortestPath = prevMoveCount;
                break;
            }
            for (int i = 1; i <= 6; i++) {
                if (ladders.containsKey(temp + i))
                    queue.add(getShortestPathDTO(ladders.get(temp + i).getHead(), prevMoveCount + 1, shortestPathDTO.getPath(), temp + i));
                else if (snakes.containsKey(temp + i))
                    queue.add(getShortestPathDTO(snakes.get(temp + i).getTail(), prevMoveCount + 1, shortestPathDTO.getPath(), temp + i));
                else
                    queue.add(getShortestPathDTO(temp + i, prevMoveCount + 1, shortestPathDTO.getPath(), null));

            }
        }

    }

    private ShortestPathDTO getShortestPathDTO(Integer currPosition, Integer moveCount, String prevPath, Integer jump) {
        return new ShortestPathDTO(currPosition, moveCount, prevPath + (jump == null ? "" : "-->" + jump) + "-->" + currPosition);

    }

    public Integer getShortestPath() {
        return shortestPath;
    }

    private int getRandomNumber(int min, int max) {
        int temp = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return temp;
    }

    public Integer getNextPosition(Integer currPosition) {
        Integer diceNumber = dice.rollDice();
        if (snakes.containsKey(currPosition + diceNumber))
            currPosition = snakes.get(currPosition + diceNumber).getTail();
        else if (ladders.containsKey(currPosition + diceNumber))
            currPosition = ladders.get(currPosition + diceNumber).getHead();
        else
            currPosition = currPosition + diceNumber;

        return currPosition;
    }

    private Snake getSnake(Integer head, Integer tail) {
        return new Snake(head, tail);
    }

    private Ladder getLadder(Integer head, Integer tail) {
        return new Ladder(head, tail);
    }
}
