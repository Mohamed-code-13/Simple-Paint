package com.mohamedcode13.paintbackend.models;

public class Shape {
    private int id;
    private int x;
    private int y;
    private int rotate;
    private String color;
    private String type;

    public Shape(int id, int x, int y, int rotate, String color, String type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rotate = rotate;
        this.color = color;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRotate() {
        return rotate;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

