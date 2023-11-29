package com.mohamedcode13.paintbackend.models;

public class Line extends AbstractShape {
    private int width;

    public Line(int id, int x, int y, int rotate, String color, int width) {
        super(id, x, y, rotate, color, "line");
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}
