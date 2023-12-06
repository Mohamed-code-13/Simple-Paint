# Simple Paint - Full-Stack Web Application

Welcome to the Simple Paint App repository! This is a full-stack web application built with Java Spring Boot for the backend and Vue.js for the frontend. The application provides an interactive environment for drawing various shapes, coloring them, moving, resizing, undoing/redoing actions, deleting shapes, and saving/loading drawings in JSON or XML formats.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Postman Collection](#postman-collection)
- [License](#license)

## Features

- **Drawing Shapes:** Create and draw various shapes on the canvas.
- **Coloring Shapes:** Customize the color of each shape.
- **Move and Resize:** Interactively move and resize shapes on the canvas.
- **Undo and Redo:** Easily undo and redo drawing actions.
- **Delete Shapes:** Remove unwanted shapes from the canvas.
- **Clear Canvas:** Clear the entire canvas to start fresh.
- **Save/Load as JSON or XML:** Save the current drawing as a JSON or XML file and load drawings from JSON or XML files.

## Technologies

- **Backend:**

  - Java Spring Boot

- **Frontend:**

  - Vue.js

- **Build Tools:**
  - Maven (Backend)
  - Vue CLI (Frontend)

## Getting Started

To run the application locally, follow these steps:

1. **Clone the Repository:**

   ```bash
   https://github.com/Mohamed-code-13/Simple-Paint.git
   cd Simple-Paint
   ```

2. **Run Backend (Spring Boot)**
   - Run the project using an IDE (**Intellij** is recommended)
   - Or navigate to the **paint-backend** and run the project using the terminal
   ```
   cd paint-backend
   .\mvnw spring-boot:run
   ```
3. **Run Frontend (Vue.js)**

   ```
   cd paint-frontend
   npm install
   npm run dev
   ```

4. **Access the App**:
   Open your browser and navigate to http://localhost:your-port

## Usage

1. **Drawing Shapes**:

   - Select a shape from the toolbar and draw it on the canvas.

2. **Coloring Shapes**:

   - Choose **options** from the toolbar and choose an object to open the edit menu to choose the border color and the filled color you want.

3. **Resize**

   - Choose **options** from the toolbar and choose an object to open the edit menu then change its size

4. **Move**

   - Choose the **Move** icon from the toolbar
   - Drag and drop shapes to move them.

5. **Undo and Redo**:

   - Use the undo and redo buttons to navigate through drawing history.

6. **Delete Shapes**:

   - Select the delete button and press on a shape to delete it.

7. **Clear Canvas**:

   - Clear the entire canvas using the "Clear" button.

8. **Save/Load as JSON or XML**:

   - Save the current drawing as a JSON or XML file.
   - Load drawings from JSON or XML files.

## Postman Collection

For testing the API endpoints, you can use the provided Postman collection:
<a href="https://documenter.getpostman.com/view/31471191/2s9YeK39bJ">Postman Collection</a>

## License

This project is licensed under the MIT License.
