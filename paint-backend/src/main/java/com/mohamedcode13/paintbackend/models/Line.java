package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

@Component
public class Line extends AbstractShape {
    private int width;

    public Line(int id, int x, int y) {
        super(id, x, y, 0, "EEE", "line");
        this.width = 50;
    }

    public int getWidth() {
        return width;
    }
}
