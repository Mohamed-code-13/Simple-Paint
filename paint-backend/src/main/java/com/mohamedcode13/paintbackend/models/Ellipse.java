package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

@Component
public class Ellipse extends AbstractShape {
    private int bigRadius;
    private int smallRadius;

    public Ellipse(int id, int x, int y) {
        super(id, x, y,  0, "#EEE", "ellipse");
        this.bigRadius = 100;
        this.smallRadius = 50;
    }

    public int getBigRadius() {
        return bigRadius;
    }

    public int getSmallRadius() {
        return smallRadius;
    }
}
