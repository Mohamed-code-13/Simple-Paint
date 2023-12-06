package com.mohamedcode13.paintbackend.models.actions;

import com.mohamedcode13.paintbackend.models.AbstractShape;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private final ActionType actionType;
    private List<AbstractShape> before;
    private List<AbstractShape> after;

    public Action(ActionType actionType) {
        this.actionType = actionType;
        this.before = new ArrayList<>();
        this.after = new ArrayList<>();
    }

    public ActionType getActionType() {
        return actionType;
    }

    public List<AbstractShape> getBefore() {
        return before;
    }

    public void setBefore(List<AbstractShape> before) {
        this.before = before;
    }

    public List<AbstractShape> getAfter() {
        return after;
    }

    public void setAfter(List<AbstractShape> after) {
        this.after = after;
    }

    public void addBefore(AbstractShape shape) {
        before.add(shape);
    }

    public void addAfter(AbstractShape shape) {
        after.add(shape);
    }

    public Action reversedCopy() {
        ActionType reversedActionType = getReversedActionType();
        Action reversedAction = new Action(reversedActionType);

        reversedAction.setBefore(this.after);
        reversedAction.setAfter(this.before);

        return reversedAction;
    }

    private ActionType getReversedActionType() {
        switch (this.actionType) {
            case ChangeAllShapes:
                return ActionType.ChangeAllShapes;
            case ChangeOneShape:
                return ActionType.ChangeOneShape;
            case AddShape:
                return ActionType.DeleteShape;
            case DeleteShape:
                return ActionType.AddShape;
            default:
                throw new IllegalArgumentException("Invalid ActionType");
        }
    }
}
