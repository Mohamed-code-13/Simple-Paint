package com.mohamedcode13.paintbackend.controllers.actions;

import com.mohamedcode13.paintbackend.models.AbstractShape;

import java.util.ArrayList;
import java.util.List;

import static com.mohamedcode13.paintbackend.controllers.actions.Action.ActionType.*;

public class Action {


    private final ActionType ActionType;



    public enum ActionType {
        ChangeAllShapes,
        ChangeOneShape,
        AddShape,
        DeleteShape
    }

    private List<AbstractShape> before;

    private List<AbstractShape> after;

    public Action(ActionType actionType) {
        this.ActionType = actionType;
        this.before = new ArrayList<>();
        this.after  = new ArrayList<>();
    }
    public ActionType getActionType() {
        return ActionType;
    }


    public void setBefore(List<AbstractShape> before) {
        this.before = before;
    }
    public void setAfter(List<AbstractShape> after) {
        this.after = after;
    }

    public void addBefore(AbstractShape shape) {
        this.before.add(shape);
    }

    public void addAfter(AbstractShape shape) {
        this.after.add(shape);
    }


    public List<AbstractShape> getBefore() {
        return this.before;
    }
    public List<AbstractShape> getAfter() {
        return this.after;
    }


    public Action reversedCopy() {
        ActionType actionType = null;
        switch(this.ActionType) {
            case ChangeAllShapes:
                actionType = ChangeAllShapes;
                break;
            case ChangeOneShape:
                actionType = ChangeOneShape;
                break;
            case AddShape:
                actionType = DeleteShape;
                break;
            case DeleteShape:
                actionType = AddShape;
                break;
        }
        Action action = new Action(actionType);
        action.setBefore(this.after);
        action.setAfter(this.before);
        return action;
    }


}
