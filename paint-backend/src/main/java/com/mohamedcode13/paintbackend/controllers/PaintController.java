package com.mohamedcode13.paintbackend.controllers;

import com.mohamedcode13.paintbackend.models.AbstractShape;
import com.mohamedcode13.paintbackend.models.ShapeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaintController {

    private int id = 0;
    @Autowired
    private ShapeFactory shapeFactory;
    private List<AbstractShape> allShapes = new ArrayList<>();

    @PostMapping(path = "/create")
    public AbstractShape create(@RequestBody Map<String, Object> body) {
        int x = (int)body.get("x");
        int y = (int)body.get("y");
        String type = (String)body.get("type");
        AbstractShape shape = shapeFactory.createShape(id++, x, y, type);
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

        allShapes.get(index).setColor(color);
        return allShapes.get(index);
    }

    @PostMapping(path = "/move")
    public AbstractShape move(@RequestBody Map<String, Object> body) {
        int curId = (int)body.get("id");
        int x = (int)body.get("x");
        int y = (int)body.get("y");
        int index = getShapeIndex(curId);

        allShapes.get(index).setPosition(x, y);
        return allShapes.get(index);
    }

    @PostMapping(path = "/delete")
    public boolean delete(@RequestBody Map<String, Object> body) {
        int curId = (int)body.get("id");
        int index = getShapeIndex(curId);

        allShapes.remove(allShapes.get(index));
        return true;
    }

    @PostMapping(path = "/rotate")
    public AbstractShape rotate(@RequestBody Map<String, Object> body) {
        int curId = (int)body.get("id");
        int rotate = (int)body.get("rotate");
        int index = getShapeIndex(curId);

        allShapes.get(index).setRotate(rotate);
        return allShapes.get(index);
    }

    @GetMapping(path = "/clear")
    public boolean clear() {
        allShapes.clear();
        return true;
    }

    @PostMapping(path = "/resize")
    public boolean resize(@RequestBody Map<String, Object> body) {
//        int curId = (int)body.get("id");
//        int index = getShapeIndex(curId);

        return true;
    }

    @GetMapping(path = "/undo")
    public boolean undo() {
        return true;
    }

    @GetMapping(path = "/redo")
    public boolean redo() {
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
