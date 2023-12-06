package com.mohamedcode13.paintbackend.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Triangle")
@JsonTypeName("triangle")
public class Triangle extends AbstractShape{
    private int base;
    private int height;

    public Triangle() {}

    public Triangle(int id, int x, int y, String borderColor, String filledColor, boolean filled, String type, int base, int height) {
        super(id, x, y, borderColor, filledColor, filled, type);
        this.base = base;
        this.height = height;
    }

    @XmlElement
    public int getBase() {
        return base;
    }

    @XmlElement
    public int getHeight() {
        return height;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public AbstractShape clone() {
        return new Rectangle(getId(), getX(), getY(), getBorderColor(), getFilledColor(), isFilled(), getType(), getBase(), getHeight());
    }
}
