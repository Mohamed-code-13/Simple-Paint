package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

@Component
public class ShapeFactory {
    public AbstractShape createShape(int id, int x, int y, String type) {
        switch (type) {
            case "square":
                return new Square(id, x, y);
            case "rectangle":
                return new Rectangle(id, x, y);
            case "line":
                return new Line(id, x, y);
            case "circle":
                return new Circle(id, x, y);
            case "ellipse":
                return new Ellipse(id, x, y);
            default:
                throw new IllegalArgumentException("Unhandled shape");
        }
    }
}
