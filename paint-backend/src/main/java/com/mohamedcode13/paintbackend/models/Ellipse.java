package com.mohamedcode13.paintbackend.models;

public class Ellipse extends AbstractShape {
    private int bigRadius;
    private int smallRadius;

    public Ellipse(int id, int x, int y) {
        super(id, x, y,  0, "#EEE", "ellipse");
        this.bigRadius = 100;
        this.smallRadius = 50;
    }

    public Ellipse(int id, int x, int y, int rotate, String color, String type, int bigRadius, int smallRadius) {
        super(id, x, y,  rotate, color, type);
        this.bigRadius = bigRadius;
        this.smallRadius = smallRadius;
    }

    public int getBigRadius() {
        return bigRadius;
    }

    public int getSmallRadius() {
        return smallRadius;
    }

    @Override
    public AbstractShape clone() {
        return new Ellipse(getId(), getX(), getY(), getRotate(), getColor(), getType(), getBigRadius(), getSmallRadius());
    }
}
