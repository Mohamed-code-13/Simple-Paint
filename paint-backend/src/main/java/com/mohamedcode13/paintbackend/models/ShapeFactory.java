package com.mohamedcode13.paintbackend.models;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShapeFactory {
    public AbstractShape createShape(int id, Map<String, Object> body) {
        String type = (String) body.get("type");
        int x = getCorrectValue(body.get("x"));
        int y = getCorrectValue(body.get("y"));
        String borderColor = (String) body.get("color1");
        String filledColor = (String) body.get("color2");
        int length1 = getCorrectValue(body.get("length1"));
        int length2 = getCorrectValue(body.get("length2"));

        switch (type) {
            case "square":
                return new Square(id, x, y, borderColor, filledColor, false, "square", length1);
            case "rectangle":
                return new Rectangle(id, x, y, borderColor, filledColor, false, "rectangle", length1, length2);
            case "line":
                return new Line(id, x, y, borderColor, filledColor, false, "line", length1, length2);
            case "circle":
                return new Circle(id, x, y, borderColor, filledColor, false, "circle", length1);
            case "ellipse":
                return new Ellipse(id, x, y, borderColor, filledColor, false, "ellipse", length1, length2);
            case "triangle":
                return new Triangle(id, x, y, borderColor, filledColor, false, "triangle", length1, length2);
            default:
                throw new IllegalArgumentException("Unhandled shape");
        }
    }

    private int getCorrectValue(Object val) {
        if (val instanceof Integer) {
            return (int) val;
        } else if (val instanceof Double) {
            return ((Double) val).intValue();
        }
        throw new IllegalArgumentException("Invalid Input");
    }
}
