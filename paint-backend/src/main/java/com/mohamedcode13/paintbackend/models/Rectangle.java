package com.mohamedcode13.paintbackend.models;

public class Rectangle extends Shape{
    private int width;
    private int height;

    public Rectangle(int id, int x, int y, int rotate, String color, String type, int width, int height) {
        super(id, x, y, rotate, color, type);
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
