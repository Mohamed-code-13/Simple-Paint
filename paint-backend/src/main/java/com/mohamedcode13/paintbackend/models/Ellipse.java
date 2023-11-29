package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

@Component
public class Ellipse extends AbstractShape {
    private int bigRadius;
    private int smallRadius;

    public Ellipse(int id, int x, int y, int rotate, String color, int bigRadius, int smallRadius) {
        super(id, x, y, rotate, color, "ellipse");
        this.bigRadius = bigRadius;
        this.smallRadius = smallRadius;
    }

    public int getBigRadius() {
        return bigRadius;
    }

    public int getSmallRadius() {
        return smallRadius;
    }
}
