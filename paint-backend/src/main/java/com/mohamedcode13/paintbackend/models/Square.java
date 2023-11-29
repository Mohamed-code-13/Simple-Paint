package com.mohamedcode13.paintbackend.models;

public class Square extends AbstractShape {
    private int width;

    public Square(int id, int x, int y, int rotate, String color, int width) {
        super(id, x, y, rotate, color, "square");
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}
