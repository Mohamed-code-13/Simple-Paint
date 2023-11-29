package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

@Component
public class Circle extends AbstractShape {
    private int radius;

    public Circle(int id, int x, int y) {
        super(id, x, y, 0, "#EEE", "circle");
        this.radius = 50;
    }

    public int getRadius() {
        return this.radius;
    }

}
