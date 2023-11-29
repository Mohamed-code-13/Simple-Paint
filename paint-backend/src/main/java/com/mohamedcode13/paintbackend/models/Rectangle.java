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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
