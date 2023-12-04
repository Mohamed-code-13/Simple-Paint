package com.mohamedcode13.paintbackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mohamedcode13.paintbackend.controllers.PaintController;
import com.mohamedcode13.paintbackend.controllers.saveLoad.DataStored;
import com.mohamedcode13.paintbackend.models.AbstractShape;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class PaintBackendApplicationTests {

	@Autowired
	PaintController paintController = new PaintController();

	PaintBackendApplicationTests() throws JsonProcessingException {
	}

	@Test
	void contextLoads() throws IOException {

		Map<String, Object> body = new HashMap<>();
		body.put("x", 10);
		body.put("y", 10);
		body.put("type", "circle");
		paintController.create(body);
		paintController.create(body);
		paintController.create(body);

		//Tests for getAllShapes method
		System.out.println("after create");
		List<AbstractShape> allShapes = paintController.getAllShapes();
		for (AbstractShape shape : allShapes) {
			System.out.println(shape.getId());
		}

		//Tests for undo & redo methods
		paintController.undo();
		paintController.undo();

		System.out.println("after 2 undos");
		allShapes = paintController.getAllShapes();
		for (AbstractShape shape : allShapes) {
			System.out.println(shape.getId());
		}

		paintController.redo();

		System.out.println("after 1 redo");
		allShapes = paintController.getAllShapes();
		for (AbstractShape shape : allShapes) {
			System.out.println(shape.getId());
		}

		paintController.clear();

		System.out.println("after clear");
		allShapes = paintController.getAllShapes();
		for (AbstractShape shape : allShapes) {
			System.out.println(shape.getId());
		}

		paintController.undo();
		System.out.println("after undo");
		allShapes = paintController.getAllShapes();
		for (AbstractShape shape : allShapes) {
			System.out.println(shape.getId());
		}

		paintController.redo();
		System.out.println("after redo");

		paintController.undo();
		System.out.println("after undo");
		for(AbstractShape shape : paintController.getAllShapes()) {
			System.out.println(shape.getId());
		}



		String saved = paintController.save(new HashMap<String, Object>() {{
			put("extension", "json");
			// Add other key-value pairs as needed
		}});

		System.out.println("saving...");
		System.out.println(saved);

		paintController.clear();
		System.out.println("after clear");
		allShapes = paintController.getAllShapes();
		for (AbstractShape shape : allShapes) {
			System.out.println(shape.getId());
		}

		//after load
		paintController.load(new HashMap<String, Object>() {{
			put("extension", "json");
			put("data", saved);
			// Add other key-value pairs as needed
		}});

		System.out.println("after load");
		allShapes = paintController.getAllShapes();
		for (AbstractShape shape : allShapes) {
			System.out.println(shape.getId());


		}

	}

}

/*



after create
0
1
2
after 2 undos
0
after 1 redo
0
1
after clear
after undo
0
1
after redo
after undo
0
1
0
1
{"allShapes":[{"type":"circle","id":0,"x":10,"y":10,"rotate":0,"color":"#EEE","type":"circle","radius":50},{"type":"circle","id":1,"x":10,"y":10,"rotate":0,"color":"#EEE","type":"circle","radius":50}],"undoStack":[{"actionType":"AddShape","before":[null],"after":[{"type":"circle","id":0,"x":10,"y":10,"rotate":0,"color":"#EEE","type":"circle","radius":50}]},{"actionType":"AddShape","before":[null],"after":[{"type":"circle","id":1,"x":10,"y":10,"rotate":0,"color":"#EEE","type":"circle","radius":50}]}],"redoStack":[{"actionType":"ChangeAllShapes","before":[],"after":[{"type":"circle","id":0,"x":10,"y":10,"rotate":0,"color":"#EEE","type":"circle","radius":50},{"type":"circle","id":1,"x":10,"y":10,"rotate":0,"color":"#EEE","type":"circle","radius":50}]}]}
after clear
after load
0
1
 */
