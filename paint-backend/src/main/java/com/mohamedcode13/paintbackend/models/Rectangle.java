package com.mohamedcode13.paintbackend.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rectangle")
public class Rectangle extends AbstractShape {
    private int width;
    private int height;

    public Rectangle() {}

    public Rectangle(int id, int x, int y) {
        super(id, x, y,0,"EEE", "rectangle");
        this.width = 100;
        this.height = 50;
    }

    public Rectangle(int id, int x, int y, int rotate, String color, String type, int width, int height) {
        super(id, x, y, rotate, color, type);
        this.width = width;
        this.height = height;
    }

    @XmlElement
    public int getWidth() {
        return width;
    }

    @XmlElement
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public AbstractShape clone() {
        return new Rectangle(getId(), getX(), getY(), getRotate(), getColor(), getType(), getWidth(), getHeight());
    }
}
