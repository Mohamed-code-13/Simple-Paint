package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

//@Component
public class Circle extends AbstractShape {
    private int radius;

    public Circle(int id, int x, int y) {
        super(id, x, y, 0, "#EEE", "circle");
        this.radius = 50;
    }

    public Circle(int id, int x, int y, int rotate, String color, String type, int radius) {
        super(id, x, y, rotate, color, type);
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }


    @Override
    public AbstractShape clone() {
        return new Circle(getId(), getX(), getY(), getRotate(), getColor(), getType(), getRadius());
    }
}
