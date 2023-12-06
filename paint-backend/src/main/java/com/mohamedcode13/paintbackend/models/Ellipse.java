package com.mohamedcode13.paintbackend.models;

import javax.swing.event.EventListenerList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Ellipse")
public class Ellipse extends AbstractShape {
    private int bigRadius;
    private int smallRadius;

    public Ellipse() {}

    public Ellipse(int id, int x, int y) {
        super(id, x, y,  0, "#EEE", "ellipse");
        this.bigRadius = 100;
        this.smallRadius = 50;
    }

    public Ellipse(int id, int x, int y, int rotate, String color, String type, int bigRadius, int smallRadius) {
        super(id, x, y,  rotate, color, type);
        this.bigRadius = bigRadius;
        this.smallRadius = smallRadius;
    }

    @XmlElement
    public int getBigRadius() {
        return bigRadius;
    }

    @XmlElement
    public int getSmallRadius() {
        return smallRadius;
    }

    public void setBigRadius(int bigRadius) {
        this.bigRadius = bigRadius;
    }

    public void setSmallRadius(int smallRadius) {
        this.smallRadius = smallRadius;
    }

    @Override
    public AbstractShape clone() {
        return new Ellipse(getId(), getX(), getY(), getRotate(), getColor(), getType(), getBigRadius(), getSmallRadius());
    }
}
