package com.mohamedcode13.paintbackend.models;

public class Rectangle extends AbstractShape {
    private int width;
    private int height;

    public Rectangle(int id, int x, int y, int rotate, String color, int width, int height) {
        super(id, x, y, rotate, color, "rectangle");
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
