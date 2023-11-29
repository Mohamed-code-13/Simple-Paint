package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

//@Component
public class Rectangle extends AbstractShape {
    private int width;
    private int height;

    public Rectangle(int id, int x, int y) {
        super(id, x, y,0,"EEE", "rectangle");
        this.width = 100;
        this.height = 50;
    }

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

    @Override
    public AbstractShape clone() {
        return new Rectangle(getId(), getX(), getY(), getRotate(), getColor(), getType(), getWidth(), getHeight());
    }
}
