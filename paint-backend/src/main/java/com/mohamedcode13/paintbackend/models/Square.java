package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

//@Component
public class Square extends AbstractShape {
    private int width;

    public Square(int id, int x, int y) {
        super(id, x, y, 0, "EEE", "square");
        this.width = 50;
    }

    public int getWidth() {
        return width;
    }
}
