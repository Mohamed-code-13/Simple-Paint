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
    private int rotate;
    private String color;
    private String type;

    public AbstractShape() {}

    public AbstractShape(int id, int x, int y, int rotate, String color, String type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rotate = rotate;
        this.color = color;
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
    public int getRotate() {
        return rotate;
    }

    @XmlElement
    public String getColor() {
        return color;
    }

    @XmlElement
    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract AbstractShape clone();
}

