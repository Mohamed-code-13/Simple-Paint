package com.mohamedcode13.paintbackend.service;

import com.mohamedcode13.paintbackend.models.*;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallerImpl;
import org.springframework.stereotype.Service;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveLoadXML {
    public void saveXML(String path, List<AbstractShape> shapes) {
        try {

            JAXBContext ctx = JAXBContext.newInstance(AbstractShapeWrapper.class,
                    AbstractShape.class, Circle.class,
                    Ellipse.class, Line.class,
                    Rectangle.class, Square.class);
            Marshaller marshaller = ctx.createMarshaller();
            File file = new File(path);

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            AbstractShapeWrapper wrapper = new AbstractShapeWrapper(shapes);
            marshaller.marshal(wrapper, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<AbstractShape> loadXml(String path) {
        try {

            JAXBContext ctx = JAXBContext.newInstance(AbstractShapeWrapper.class,
                    AbstractShape.class, Circle.class,
                    Ellipse.class, Line.class,
                    Rectangle.class, Square.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            File file = new File(path);

            AbstractShapeWrapper wrapper = (AbstractShapeWrapper) unmarshaller.unmarshal(file);

            return wrapper.getShapes();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
