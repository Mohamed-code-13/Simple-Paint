package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShapeFactory {
    public AbstractShape createShape(int id, Map<String, Object> body) {
        String type = (String) body.get("type");
        int x = (int) body.get("x");
        int y = (int) body.get("y");
        String borderColor = (String) body.get("borderColor");
        String filledColor = (String) body.get("filledColor");
        int length1 = (int) body.get("length1");
        int length2 = (int) body.get("length2");

        switch (type) {
            case "square":
                return new Square(id, x, y, borderColor, filledColor, "square", length1);
            case "rectangle":
                return new Rectangle(id, x, y, borderColor, filledColor, "rectangle", length1, length2);
            case "line":
                return new Line(id, x, y, borderColor, filledColor, "line", length1, length2);
            case "circle":
                return new Circle(id, x, y, borderColor, filledColor, "circle", length1);
            case "ellipse":
                return new Ellipse(id, x, y, borderColor, filledColor, "ellipse", length1, length2);
            case "triangle":
                return new Triangle(id, x, y, borderColor, filledColor, "triangle", length1, length2);
            default:
                throw new IllegalArgumentException("Unhandled shape");
        }
    }
}
