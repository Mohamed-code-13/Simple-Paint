package com.mohamedcode13.paintbackend.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Line")
@JsonTypeName("line")
public class Line extends AbstractShape {
    private int endX;
    private int endY;

    public Line() {}

    public Line(int id, int x, int y, String borderColor, String filledColor, boolean filled, String type, int endX, int endY) {
        super(id, x, y, borderColor, filledColor, filled, type);
        this.endX = endX;
        this.endY = endY;
    }

    @XmlElement
    public int getEndX() {
        return endX;
    }

    @XmlElement
    public int getEndY() {
        return endY;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    @Override
    public AbstractShape clone() {
        return new Line(getId(), getX(), getY(), getBorderColor(), getFilledColor(), isFilled(), getType(), getEndX(), getEndY());
    }
}
