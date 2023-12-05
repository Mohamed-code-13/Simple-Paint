package com.mohamedcode13.paintbackend.service;

import com.mohamedcode13.paintbackend.models.*;
import org.springframework.stereotype.Service;

import javax.xml.bind.*;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@Service
public class SaveLoadXML {
    public String saveXML(List<AbstractShape> shapes) {
        try {

            JAXBContext ctx = JAXBContext.newInstance(AbstractShapeWrapper.class,
                    AbstractShape.class, Circle.class,
                    Ellipse.class, Line.class,
                    Rectangle.class, Square.class);

            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter writer = new StringWriter();
            AbstractShapeWrapper wrapper = new AbstractShapeWrapper(shapes);

            marshaller.marshal(wrapper, writer);

            return writer.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<AbstractShape> loadXml(String xmlData) {
        try {

            JAXBContext ctx = JAXBContext.newInstance(AbstractShapeWrapper.class,
                    AbstractShape.class, Circle.class,
                    Ellipse.class, Line.class,
                    Rectangle.class, Square.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();

            StringReader reader = new StringReader(xmlData);
            AbstractShapeWrapper wrapper = (AbstractShapeWrapper) unmarshaller.unmarshal(reader);

            return wrapper.getShapes();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
