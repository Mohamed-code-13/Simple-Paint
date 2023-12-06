package com.mohamedcode13.paintbackend.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Square")
public class Square extends AbstractShape {
    private int width;

    public Square() {}

    public Square(int id, int x, int y, String borderColor, String filledColor, boolean filled, String type, int width) {
        super(id, x, y, borderColor, filledColor, filled, type);
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
        return new Square(getId(), getX(), getY(), getBorderColor(), getFilledColor(), isFilled(), getType(), getWidth());
    }
}
