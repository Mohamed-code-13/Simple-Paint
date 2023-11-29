package com.mohamedcode13.paintbackend.models;

public class Circle extends AbstractShape {
    private int radius;

    public Circle(int id, int x, int y, int rotate, String color, int radius) {
        super(id, x, y, rotate, color, "circle");
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

}
