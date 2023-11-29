package com.mohamedcode13.paintbackend.models;

public class Line extends Shape{
    private int width;

    public Line(int id, int x, int y, int rotate, String color, String type, int width) {
        super(id, x, y, rotate, color, type);
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}
