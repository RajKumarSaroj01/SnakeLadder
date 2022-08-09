package com.java.dto;

public class ShortestPathDTO {
    private Integer currentPoint;
    private Integer moveCount;
    private String path;

    public ShortestPathDTO(Integer currentPoint, Integer moveCount, String path) {
        this.currentPoint = currentPoint;
        this.moveCount = moveCount;
        this.path = path;
    }

    public Integer getCurrentPoint() {
        return currentPoint;
    }

    public Integer getMoveCount() {
        return moveCount;
    }

    public String getPath() {
        return path;
    }
}
