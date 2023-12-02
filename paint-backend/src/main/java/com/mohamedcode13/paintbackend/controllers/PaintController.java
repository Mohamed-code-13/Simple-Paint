package com.mohamedcode13.paintbackend.controllers;

import com.mohamedcode13.paintbackend.controllers.actions.Action;
import com.mohamedcode13.paintbackend.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@RestController
@RequestMapping("/")
public class PaintController {

    private int id = 0;
    @Autowired
    private ShapeFactory shapeFactory;
    private List<AbstractShape> allShapes = new ArrayList<>();

    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();

    @PostMapping(path = "/create")
    public AbstractShape create(@RequestBody Map<String, Object> body) {


        int x = (int)body.get("x");
        int y = (int)body.get("y");
        String type = (String)body.get("type");

        Action action = new Action();
        action.addBefore(null);

        AbstractShape shape = shapeFactory.createShape(id++, x, y, type);

        action.addAfter(shape);
        undoStack.push(action);

        allShapes.add(shape);
        return shape;
    }

    @GetMapping(path = "/all-shapes")
    public List<AbstractShape> getAllShapes() {
        return allShapes;
    }

    @PostMapping(path = "/color")
    public AbstractShape color(@RequestBody Map<String, Object> body) {
        int curId = (int)body.get("id");
        String color = (String)body.get("color");
        int index = getShapeIndex(curId);

        Action action = new Action();
        action.addBefore(allShapes.get(index));

        allShapes.get(index).setColor(color);

        action.addAfter(allShapes.get(index));
        undoStack.push(action);

        return allShapes.get(index);
    }

    @PostMapping(path = "/move")
    public AbstractShape move(@RequestBody Map<String, Object> body) {
        int curId = (int)body.get("id");
        int x = (int)body.get("x");
        int y = (int)body.get("y");
        int index = getShapeIndex(curId);

        Action action = new Action();
        action.addBefore(allShapes.get(index));

        allShapes.get(index).setPosition(x, y);

        action.addAfter(allShapes.get(index));
        undoStack.push(action);

        return allShapes.get(index);
    }

    @PostMapping(path = "/delete")
    public boolean delete(@RequestBody Map<String, Object> body) {
        int curId = (int)body.get("id");
        int index = getShapeIndex(curId);

        Action action = new Action();
        action.addBefore(allShapes.get(index));

        allShapes.remove(allShapes.get(index));

        action.addAfter(null);
        undoStack.push(action);

        return true;
    }

    @PostMapping(path = "/rotate")
    public AbstractShape rotate(@RequestBody Map<String, Object> body) {
        int curId = (int)body.get("id");
        int rotate = (int)body.get("rotate");
        int index = getShapeIndex(curId);

        Action action = new Action();
        action.addBefore(allShapes.get(index));

        allShapes.get(index).setRotate(rotate);

        action.addAfter(allShapes.get(index));
        undoStack.push(action);

        return allShapes.get(index);
    }

    @PostMapping(path = "/resize")
    public boolean resize(@RequestBody Map<String, Object> body) {
        int curId = (int)body.get("id");
        int index = getShapeIndex(curId);

        Action action = new Action();
        action.addBefore(allShapes.get(index));

        int width, height, radius, bigRadius, smallRadius;
        switch(allShapes.get(index).getType()) {

            case "square":
                width = (int)body.get("width");
                ((Square)allShapes.get(index)).setWidth(width);
                break;
            case "rectangle":
                width = (int)body.get("width");
                height = (int)body.get("height");

                ((Rectangle)allShapes.get(index)).setWidth(width);
                ((Rectangle)allShapes.get(index)).setHeight(height);
                break;
            case "line":
                width = (int)body.get("width");
                ((Line)allShapes.get(index)).setWidth(width);
                break;
            case "circle":
                radius = (int)body.get("radius");
                ((Circle)allShapes.get(index)).setRadius(radius);
                break;
            case "ellipse":
                bigRadius = (int)body.get("bigRadius");
                smallRadius = (int)body.get("smallRadius");
                ((Ellipse)allShapes.get(index)).setBigRadius(bigRadius);
                ((Ellipse)allShapes.get(index)).setSmallRadius(smallRadius);
                break;
            default:
                throw new IllegalArgumentException("Unhandled shape");
        }

        action.addAfter(allShapes.get(index));
        undoStack.push(action);

        return true;
    }

    @PostMapping(path = "/copy")
    public AbstractShape copy(@RequestBody Map<String, Object> body) {
        int curId = (int)body.get("id");
        int index = getShapeIndex(curId);

        AbstractShape shape = allShapes.get(index).clone();
        shape.setId(id++);
        allShapes.add(shape);
        return shape;
    }

    private int getShapeIndex(int curId) {
        for (int i = 0; i < allShapes.size(); ++i) {
            if (allShapes.get(i).getId() == curId) {
                return i;
            }
        }
        return -1;
    }

}
