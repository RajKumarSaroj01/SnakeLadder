package com.java;

public class Snake {
    private Integer head;
    private Integer tail;

    public Snake(Integer head, Integer tail) {
        this.head = head;
        this.tail = tail;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getTail() {
        return tail;
    }

    public void setTail(Integer tail) {
        this.tail = tail;
    }
}
