package com.mohamedcode13.paintbackend.models;

public class Circle extends Shape{
    private int radius;

    public Circle(int id, int x, int y, int rotate, String color, String type, int radius) {
        super(id, x, y, rotate, color, type);
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

}
