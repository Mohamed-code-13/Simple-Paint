package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

//@Component
public class Square extends AbstractShape {
    private int width;


    public Square() {

    }

    public Square(int id, int x, int y) {
        super(id, x, y, 0, "EEE", "square");
        this.width = 50;
    }

    public Square(int id, int x, int y, int rotate, String color, String type, int width) {
        super(id, x, y, rotate, color, type);
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public AbstractShape clone() {
        return new Square(getId(), getX(), getY(), getRotate(), getColor(), getType(), getWidth());
    }
}
