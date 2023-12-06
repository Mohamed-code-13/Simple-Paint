package com.mohamedcode13.paintbackend.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rectangle")
@JsonTypeName("rectangle")
public class Rectangle extends AbstractShape {
    private int width;
    private int height;

    public Rectangle() {}

    public Rectangle(int id, int x, int y, String borderColor, String filledColor, boolean filled, String type, int width, int height) {
        super(id, x, y, borderColor, filledColor, filled, type);
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
        return new Rectangle(getId(), getX(), getY(), getBorderColor(), getFilledColor(), isFilled(), getType(), getWidth(), getHeight());
    }
}
