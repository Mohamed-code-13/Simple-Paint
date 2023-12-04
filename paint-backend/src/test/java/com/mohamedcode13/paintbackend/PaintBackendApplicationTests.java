package com.mohamedcode13.paintbackend;

import com.mohamedcode13.paintbackend.controllers.PaintController;
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
	PaintController paintController;

	@Test
	void contextLoads() throws IOException {

		Map<String, Object> body = new HashMap<>();
		body.put("x", 10); body.put("y", 10); body.put("type", "circle");
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

 */