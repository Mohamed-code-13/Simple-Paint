package com.mohamedcode13.paintbackend.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Circle")
public class Circle extends AbstractShape {
    private int radius;

    public Circle() {}

    public Circle(int id, int x, int y, String borderColor, String filledColor, String type, int radius) {
        super(id, x, y, borderColor, filledColor, type);
        this.radius = radius;
    }

    @XmlElement
    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public AbstractShape clone() {
        return new Circle(getId(), getX(), getY(), getBorderColor(), getFilledColor(), getType(), getRadius());
    }
}
