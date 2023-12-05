package com.mohamedcode13.paintbackend.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Line")
public class Line extends AbstractShape {
    private int width;

    public Line() {}

    public Line(int id, int x, int y) {
        super(id, x, y, 0, "EEE", "line");
        this.width = 50;
    }

    public Line(int id, int x, int y, int rotate, String color, String type, int width) {
        super(id, x, y, rotate, color, type);
        this.width = width;
    }

    @XmlElement
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public AbstractShape clone() {
        return new Line(getId(), getX(), getY(), getRotate(), getColor(), getType(), getWidth());
    }
}
