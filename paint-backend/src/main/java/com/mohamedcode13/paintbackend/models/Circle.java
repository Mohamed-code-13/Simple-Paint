package com.mohamedcode13.paintbackend.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Circle")
@JsonTypeName("circle")
public class Circle extends AbstractShape {
    private int radius;

    public Circle() {}

    public Circle(int id, int x, int y, String borderColor, String filledColor, boolean filled, String type, int radius) {
        super(id, x, y, borderColor, filledColor, filled, type);
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
        return new Circle(getId(), getX(), getY(), getBorderColor(), getFilledColor(), isFilled(), getType(), getRadius());
    }
}
