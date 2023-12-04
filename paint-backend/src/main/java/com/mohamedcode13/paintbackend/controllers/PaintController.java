package com.mohamedcode13.paintbackend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohamedcode13.paintbackend.controllers.saveLoad.DataStored;
import com.mohamedcode13.paintbackend.controllers.saveLoad.Loader;
import com.mohamedcode13.paintbackend.controllers.saveLoad.Saver;
import com.mohamedcode13.paintbackend.models.actions.Action;
import com.mohamedcode13.paintbackend.models.*;
import com.mohamedcode13.paintbackend.models.actions.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:8081")
public class PaintController {

    private int id = 0;
    @Autowired
    private ShapeFactory shapeFactory;
    private List<AbstractShape> allShapes = new ArrayList<>();

    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();

    @PostMapping(path = "/create")
    public AbstractShape create(@RequestBody Map<String, Object> body) {
        int x = (int) body.get("x");
        int y = (int) body.get("y");
        String type = (String) body.get("type");

        Action action = new Action(ActionType.AddShape);
        action.addBefore(null);

        AbstractShape shape = shapeFactory.createShape(id++, x, y, type);

        action.addAfter(shape);
        undoStack.push(action);
        redoStack.clear();

        allShapes.add(shape);
        return shape;
    }

    @GetMapping(path = "/all-shapes")
    public List<AbstractShape> getAllShapes() {
        return allShapes;
    }

    @PostMapping(path = "/color")
    public AbstractShape color(@RequestBody Map<String, Object> body) {
        int curId = (int) body.get("id");
        String color = (String) body.get("color");
        int index = getShapeIndex(curId);

        Action action = new Action(ActionType.ChangeOneShape);
        action.addBefore(allShapes.get(index).clone());

        allShapes.get(index).setColor(color);

        action.addAfter(allShapes.get(index));
        undoStack.push(action);
        redoStack.clear();

        return allShapes.get(index);
    }

    @PostMapping(path = "/move")
    public AbstractShape move(@RequestBody Map<String, Object> body) {
        int curId = (int) body.get("id");
        int x = (int) body.get("x");
        int y = (int) body.get("y");
        int index = getShapeIndex(curId);

        Action action = new Action(ActionType.ChangeOneShape);
        action.addBefore(allShapes.get(index).clone());

        allShapes.get(index).setPosition(x, y);

        action.addAfter(allShapes.get(index));
        undoStack.push(action);
        redoStack.clear();

        return allShapes.get(index);
    }

    @PostMapping(path = "/delete")
    public boolean delete(@RequestBody Map<String, Object> body) {
        int curId = (int) body.get("id");
        int index = getShapeIndex(curId);

        Action action = new Action(ActionType.DeleteShape);
        action.addBefore(allShapes.get(index).clone());

        allShapes.remove(allShapes.get(index));

        action.addAfter(null);
        undoStack.push(action);
        redoStack.clear();

        return true;
    }

    @PostMapping(path = "/rotate")
    public AbstractShape rotate(@RequestBody Map<String, Object> body) {
        int curId = (int) body.get("id");
        int rotate = (int) body.get("rotate");
        int index = getShapeIndex(curId);

        Action action = new Action(ActionType.ChangeOneShape);
        action.addBefore(allShapes.get(index).clone());

        allShapes.get(index).setRotate(rotate);

        action.addAfter(allShapes.get(index));
        undoStack.push(action);
        redoStack.clear();

        return allShapes.get(index);
    }

    @PostMapping(path = "/resize")
    public boolean resize(@RequestBody Map<String, Object> body) {
        int curId = (int) body.get("id");
        int index = getShapeIndex(curId);

        Action action = new Action(ActionType.ChangeOneShape);
        action.addBefore(allShapes.get(index).clone());

        int width, height, radius, bigRadius, smallRadius;
        switch (allShapes.get(index).getType()) {

            case "square":
                width = (int) body.get("width");
                ((Square) allShapes.get(index)).setWidth(width);
                break;
            case "rectangle":
                width = (int) body.get("width");
                height = (int) body.get("height");

                ((Rectangle) allShapes.get(index)).setWidth(width);
                ((Rectangle) allShapes.get(index)).setHeight(height);
                break;
            case "line":
                width = (int) body.get("width");
                ((Line) allShapes.get(index)).setWidth(width);
                break;
            case "circle":
                radius = (int) body.get("radius");
                ((Circle) allShapes.get(index)).setRadius(radius);
                break;
            case "ellipse":
                bigRadius = (int) body.get("bigRadius");
                smallRadius = (int) body.get("smallRadius");
                ((Ellipse) allShapes.get(index)).setBigRadius(bigRadius);
                ((Ellipse) allShapes.get(index)).setSmallRadius(smallRadius);
                break;
            default:
                throw new IllegalArgumentException("Unhandled shape");
        }

        action.addAfter(allShapes.get(index));
        undoStack.push(action);
        redoStack.clear();

        return true;
    }

    @PostMapping(path = "/copy")
    public AbstractShape copy(@RequestBody Map<String, Object> body) {
        int curId = (int) body.get("id");
        int index = getShapeIndex(curId);

        AbstractShape shape = allShapes.get(index).clone();
        shape.setId(id++);
        allShapes.add(shape);
        return shape;
    }

    @GetMapping(path = "/clear")
    public boolean clear() {
        Action action = new Action(ActionType.ChangeAllShapes);
        action.setBefore(this.allShapes);

        allShapes = new ArrayList<>();

        action.setAfter(allShapes);
        undoStack.push(action);
        redoStack.clear();

        return true;
    }

    @GetMapping(path = "/undo")
    public boolean undo() {
        if (undoStack.isEmpty()) {
            return false;
        }
        Action action = undoStack.pop();
        redoStack.push(action.reversedCopy());
        performAction(action.reversedCopy());

        return true;
    }

    @GetMapping(path = "/redo")
    public boolean redo() {
        if (redoStack.isEmpty()) {
            return false;
        }
        Action action = redoStack.pop();
        undoStack.push(action.reversedCopy());
        performAction(action.reversedCopy());

        return true;
    }

    @GetMapping(path = "/save")
    public String save(@RequestBody Map<String, Object> body) throws JsonProcessingException {


        String extension = (String) body.get("extension");

        DataStored storedData = new DataStored();
        storedData.setAllShapes(this.allShapes);
        storedData.setUndoStack(this.undoStack);
        storedData.setRedoStack(this.redoStack);


        Saver saver = new Saver();

        if(extension.equals("json")){
            return saver.saveJson(storedData);
        }
        else if(extension.equals("xml")){
            return saver.saveXml(storedData);
        } else {
            throw new IllegalArgumentException("Invalid extension");
        }



    }


    @PostMapping(path = "/load")
public boolean load(@RequestBody Map<String, Object> body) throws JsonProcessingException {

        String json = (String) body.get("data");

        Loader loader = new Loader();
        DataStored storedData = Loader.loadJson(json);

        this.allShapes = storedData.getAllShapes();
        this.undoStack = storedData.getUndoStack();
        this.redoStack = storedData.getRedoStack();

        return true;
    }


    private void performAction(Action action) {
        switch (action.getActionType()) {
            case ChangeAllShapes:
                this.allShapes = action.getAfter();
                break;
            case ChangeOneShape:
                int index = getShapeIndex(action.getBefore().get(0).getId());
                this.allShapes.set(index, action.getAfter().get(0));
                break;
            case AddShape:
                this.allShapes.add(action.getAfter().get(0));
                break;
            case DeleteShape:
                this.allShapes.remove(action.getBefore().get(0));
                break;
        }
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
