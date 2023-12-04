package com.mohamedcode13.paintbackend.controllers.saveLoad;


import com.mohamedcode13.paintbackend.models.AbstractShape;
import com.mohamedcode13.paintbackend.models.actions.Action;

import java.util.List;
import java.util.Stack;

public class DataStored {

    List<AbstractShape> allShapes;

    Stack<Action> undoStack;

    Stack<Action> redoStack;




    public void setAllShapes(List<AbstractShape> allShapes) {
        this.allShapes = allShapes;
    }


    public void setUndoStack(Stack<Action> undoStack) {
        this.undoStack = undoStack;
    }

    public void setRedoStack(Stack <Action> redoStack) {
        this.redoStack = redoStack;

    }

    public List<AbstractShape> getAllShapes() {
        return allShapes;
    }

    public Stack<Action> getUndoStack() {
        return undoStack;
    }

    public Stack<Action> getRedoStack() {
        return redoStack;
    }

}
