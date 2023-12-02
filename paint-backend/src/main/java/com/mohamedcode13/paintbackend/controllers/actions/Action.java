package com.mohamedcode13.paintbackend.controllers.actions;

import com.mohamedcode13.paintbackend.models.AbstractShape;

import java.util.ArrayList;
import java.util.List;

public class Action {

    private List<AbstractShape> before;

    private List<AbstractShape> after;

    public Action(){
        this.before = new ArrayList<>();
        this.after  = new ArrayList<>();
    }


    public void setBefore(List<AbstractShape> before) {
        this.before = before;
    }
    public void setAfter(List<AbstractShape> after) {
        this.after= after;
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


}
