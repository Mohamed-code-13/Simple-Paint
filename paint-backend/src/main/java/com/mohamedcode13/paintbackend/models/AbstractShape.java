package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Component
@XmlRootElement(name = "AbstractShape")
@XmlType
public abstract class AbstractShape {
    private int id;
    private int x;
    private int y;
    private String borderColor;
    private String filledColor;
    private boolean filled;
    private String type;

    public AbstractShape() {}

    public AbstractShape(int id, int x, int y, String borderColor, String filledColor, boolean isFilled, String type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.borderColor = borderColor;
        this.filledColor = filledColor;
        this.filled = isFilled;
        this.type = type;
    }

    @XmlElement
    public int getId() {
        return this.id;
    }

    @XmlElement
    public int getX() {
        return x;
    }

    @XmlElement
    public int getY() {
        return y;
    }

    @XmlElement
    public String getBorderColor() {
        return borderColor;
    }

    @XmlElement
    public String getFilledColor() {
        return filledColor;
    }

    @XmlElement
    public boolean isFilled() {
        return filled;
    }

    @XmlElement
    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void movePosition(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public void setFilledColor(String filledColor) {
        this.filledColor = filledColor;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract AbstractShape clone();
}

