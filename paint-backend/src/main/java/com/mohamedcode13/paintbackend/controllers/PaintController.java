package com.mohamedcode13.paintbackend.controllers;

import com.mohamedcode13.paintbackend.models.actions.Action;
import com.mohamedcode13.paintbackend.models.*;
import com.mohamedcode13.paintbackend.models.actions.ActionType;
import com.mohamedcode13.paintbackend.service.SaveLoadJSON;
import com.mohamedcode13.paintbackend.service.SaveLoadXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@RestController
@RequestMapping("/")
@CrossOrigin()
public class PaintController {

    private int id = 0;
    @Autowired
    private ShapeFactory shapeFactory;
    @Autowired
    private SaveLoadXML saveLoadXML;
    @Autowired
    private SaveLoadJSON saveLoadJSON;
    private List<AbstractShape> allShapes = new ArrayList<>();

    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();

    @PostMapping(path = "/create")
    public AbstractShape create(@RequestBody Map<String, Object> body) {
        Action action = new Action(ActionType.AddShape);
        action.addBefore(null);

        AbstractShape shape = shapeFactory.createShape(id++, body);

        action.addAfter(shape.clone());
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
        String borderColor = (String) body.get("color1");
        String filledColor = (String) body.get("color2");
        boolean filled = (boolean) body.get("fill");
        int index = getShapeIndex(curId);

        Action action = new Action(ActionType.ChangeOneShape);
        action.addBefore(allShapes.get(index).clone());

        allShapes.get(index).setBorderColor(borderColor);
        allShapes.get(index).setFilledColor(filledColor);
        allShapes.get(index).setFilled(filled);

        action.addAfter(allShapes.get(index).clone());
        undoStack.push(action);
        redoStack.clear();

        return allShapes.get(index);
    }

    @PostMapping(path = "/move")
    public AbstractShape move(@RequestBody Map<String, Object> body) {
        int curId = (int) body.get("id");
        int dx = (int) body.get("dx");
        int dy = (int) body.get("dy");
        int index = getShapeIndex(curId);

        Action action = new Action(ActionType.ChangeOneShape);
        action.addBefore(allShapes.get(index).clone());

        allShapes.get(index).movePosition(dx, dy);
        if (allShapes.get(index).getType().equals("line")) {
            Line line = (Line)allShapes.get(index);
            line.setEndX(line.getEndX() + dx);
            line.setEndY(line.getEndY() + dy);
        }

        action.addAfter(allShapes.get(index).clone());
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

    @PostMapping(path = "/resize")
    public boolean resize(@RequestBody Map<String, Object> body) {
        int curId = (int) body.get("id");
        int index = getShapeIndex(curId);

        Action action = new Action(ActionType.ChangeOneShape);
        action.addBefore(allShapes.get(index).clone());

        switch (allShapes.get(index).getType()) {

            case "square":
                ((Square) allShapes.get(index)).setWidth((int) body.get("length1"));
                break;
            case "rectangle":
                ((Rectangle) allShapes.get(index)).setWidth((int) body.get("length1"));
                ((Rectangle) allShapes.get(index)).setHeight((int) body.get("length2"));
                break;
            case "line":
                ((Line) allShapes.get(index)).setEndX((int)body.get("length1"));
                ((Line) allShapes.get(index)).setEndY((int)body.get("length2"));
                break;
            case "circle":
                ((Circle) allShapes.get(index)).setRadius((int) body.get("length1"));
                break;
            case "ellipse":
                ((Ellipse) allShapes.get(index)).setRadiusX((int)body.get("length1"));
                ((Ellipse) allShapes.get(index)).setRadiusY((int)body.get("length2"));
                break;
            case "triangle":
                ((Triangle) allShapes.get(index)).setBase((int)body.get("length1"));
                ((Triangle) allShapes.get(index)).setHeight((int)body.get("length2"));
                break;
            default:
                throw new IllegalArgumentException("Unhandled shape");
        }

        action.addAfter(allShapes.get(index).clone());
        undoStack.push(action);
        redoStack.clear();

        return true;
    }

    @PostMapping(path = "/copy")
    public AbstractShape copy(@RequestBody Map<String, Object> body) {
        int curId = (int) body.get("id");
        int x = (int) body.get("x");
        int y = (int) body.get("y");
        int index = getShapeIndex(curId);

        Action action = new Action(ActionType.AddShape);
        action.addBefore(null);

        AbstractShape shape = allShapes.get(index).clone();
        shape.setId(id++);
        shape.setX(x);
        shape.setY(y);

        action.addAfter(shape.clone());
        undoStack.push(action);
        redoStack.clear();

        allShapes.add(shape);
        return shape;
    }

    @GetMapping(path = "/clear")
    public boolean clear() {
        Action action = new Action(ActionType.ChangeAllShapes);
        action.setBefore(new ArrayList<>(this.allShapes));

        allShapes.clear() ;

        action.setAfter(new ArrayList<>(this.allShapes));
        undoStack.push(action);
        redoStack.clear();

        return true;
    }

    @GetMapping(path = "/save-xml")
    public ResponseEntity<String> saveXml() {
        return ResponseEntity.ok(saveLoadXML.saveXML(allShapes));
    }

    @PostMapping(path = "/load-xml")
    public List<AbstractShape> loadXml(@RequestBody String body) {
        this.allShapes = saveLoadXML.loadXml(body);
        this.undoStack.clear();
        this.redoStack.clear();
        return allShapes;
    }

    @GetMapping(path = "/save-json")
    public ResponseEntity<String> saveJson() {
        return ResponseEntity.ok(saveLoadJSON.saveJson(allShapes));
    }

    @PostMapping(path = "/load-json")
    public List<AbstractShape> loadJson(@RequestBody String body) {
        this.allShapes = saveLoadJSON.loadJson(body);
        this.undoStack.clear();
        this.redoStack.clear();
        return allShapes;
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
                int id = action.getBefore().get(0).getId();
                this.allShapes.remove(getShapeIndex(id));
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