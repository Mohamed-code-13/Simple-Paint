package com.mohamedcode13.paintbackend.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.stereotype.Component;

@Component
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Circle.class,    name = "circle"),
        @JsonSubTypes.Type(value = Ellipse.class,   name = "ellipse"),
        @JsonSubTypes.Type(value = Line.class,      name = "line"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Square.class,    name = "square"),
        })
public abstract class AbstractShape {
    private int id;
    private int x;
    private int y;
    private int rotate;
    private String color;
    private final String type;

    public AbstractShape(){}
    public AbstractShape(int id, int x, int y, int rotate, String color, String type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rotate = rotate;
        this.color = color;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRotate() {
        return rotate;
    }

    public String getColor() {
        return color;
    }

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

    public abstract AbstractShape clone();
}



