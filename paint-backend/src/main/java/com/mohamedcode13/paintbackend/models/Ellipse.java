package com.mohamedcode13.paintbackend.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Ellipse")
@JsonTypeName("ellipse")
public class Ellipse extends AbstractShape {
    private int radiusX;
    private int radiusY;

    public Ellipse() {}

    public Ellipse(int id, int x, int y, String borderColor, String filledColor, boolean filled, String type, int bigRadius, int smallRadius) {
        super(id, x, y, borderColor, filledColor, filled, type);
        this.radiusX = bigRadius;
        this.radiusY = smallRadius;
    }

    @XmlElement
    public int getRadiusX() {
        return radiusX;
    }

    @XmlElement
    public int getRadiusY() {
        return radiusY;
    }

    public void setRadiusX(int radiusX) {
        this.radiusX = radiusX;
    }

    public void setRadiusY(int radiusY) {
        this.radiusY = radiusY;
    }

    @Override
    public AbstractShape clone() {
        return new Ellipse(getId(), getX(), getY(), getBorderColor(), getFilledColor(), isFilled(), getType(), getRadiusX(), getRadiusY());
    }
}
