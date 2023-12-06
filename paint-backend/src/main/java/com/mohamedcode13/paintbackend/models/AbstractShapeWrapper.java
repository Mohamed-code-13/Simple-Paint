package com.mohamedcode13.paintbackend.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class AbstractShapeWrapper {
    private List<AbstractShape> shapes;

    public AbstractShapeWrapper() {}

    public AbstractShapeWrapper(List<AbstractShape> shapes) {
        this.shapes = shapes;
    }

    @XmlElement
    public List<AbstractShape> getShapes() {
        return shapes;
    }

    public void setShapes(List<AbstractShape> shapes) {
        this.shapes = shapes;
    }
}
