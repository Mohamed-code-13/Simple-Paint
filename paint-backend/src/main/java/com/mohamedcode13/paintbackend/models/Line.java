package com.mohamedcode13.paintbackend.models;

public class Line extends AbstractShape {
    private int width;

    public Line(int id, int x, int y) {
        super(id, x, y, 0, "EEE", "line");
        this.width = 100;
    }

    public Line(int id, int x, int y, int rotate, String color, String type, int width) {
        super(id, x, y, rotate, color, type);
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public AbstractShape clone() {
        return new Line(getId(), getX(), getY(), getRotate(), getColor(), getType(), getWidth());
    }
}
