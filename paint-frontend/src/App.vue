<template>
  <ToolBar :selectedOption="selectedOption" @changeOption="changeOption" />
  <div class="wrapper">
    <div class="leftcolumn">
      <ShapePicker :selectedShape="selectedShape" @changeShape="changeShape" />
      <EditBar @changeEdit="changeEdit" />
    </div>
    <div class="rightcolumn">
      <CanvasComponent
        :shapes="shapes"
        :drawingMode="drawingMode"
        :key="JSON.stringify(shapes)"
        @createShape="createShape"
        @modifyShape="modifyShape"
      />
    </div>
  </div>
</template>

<script>
import ShapePicker from "./components/ShapePicker.vue";
import ToolBar from "./components/ToolBar.vue";
import CanvasComponent from "./components/CanvasComponent.vue";
import EditBar from "./components/EditBar.vue";

const apiUrl = "http://localhost:8080";

export default {
  name: "App",
  components: {
    ShapePicker,
    ToolBar,
    CanvasComponent,
    EditBar,
  },
  data() {
    return {
      selectedShape: "line",
      selectedOption: "",
      drawingMode: true,
      shapes: [],
    };
  },
  mounted() {
    this.getAllShapes();
  },
  methods: {
    changeShape(s) {
      this.selectedShape = s;
      this.selectedOption = "";
      this.drawingMode = true;
    },
    changeOption(o) {
      this.selectedOption = o;
      this.selectedShape = "";
      this.drawingMode = false;
    },
    changeEdit(e) {
      switch (e) {
        case "clear":
          this.deleteAll();
          break;
      }
    },
    async createShape(e) {
      const cUrl = apiUrl + "/create";

      await fetch(cUrl, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          type: this.selectedShape,
          x: e[0],
          y: e[1],
        }),
      });

      await this.getAllShapes();
    },
    async getAllShapes() {
      const cUrl = apiUrl + "/all-shapes";

      let response = await fetch(cUrl);

      this.shapes = await response.json();
    },
    async deleteAll() {
      const dUrl = apiUrl + "/clear";

      await fetch(dUrl);

      await this.getAllShapes();
    },
    async deleteShape(shapeId) {
      const dUrl = apiUrl + "/delete";

      await fetch(dUrl, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: shapeId,
        }),
      });

      await this.getAllShapes();
    },
    async copyShape(shapeId) {
      const dUrl = apiUrl + "/copy";

      await fetch(dUrl, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: shapeId,
        }),
      });

      await this.getAllShapes();
    },
    modifyShape(e) {
      const shapeID = this.shapeClicked(e[0], e[1]);
      if (shapeID == -1) {
        return;
      }
      switch (this.selectedOption) {
        case "copy":
          this.copyShape(shapeID);
          break;
        case "delete":
          this.deleteShape(shapeID);
          break;
      }
    },
    shapeClicked(x, y) {
      for (let i = this.shapes.length - 1; i >= 0; --i) {
        const shape = this.shapes[i];
        const res = this.detectShape(x, y, shape);

        if (res != -1) {
          return res;
        }
      }
      return -1;
    },
    detectShape(x, y, shape) {
      const type = shape["type"];
      switch (type) {
        case "square":
          if (this.detectSquare(x, y, shape)) {
            return shape["id"];
          }
          break;
        case "rectangle":
          if (this.detectRectangle(x, y, shape)) {
            return shape["id"];
          }
          break;
        case "line":
          if (this.detectLine(x, y, shape)) {
            return shape["id"];
          }
          break;
        case "circle":
          if (this.detectCircle(x, y, shape)) {
            return shape["id"];
          }
          break;
        case "ellipse":
          if (this.detectEllipse(x, y, shape)) {
            return shape["id"];
          }
          break;
      }
      return -1;
    },
    detectSquare(x, y, shape) {
      return (
        x >= shape["x"] &&
        x <= shape["x"] + shape["width"] &&
        y >= shape["y"] &&
        y <= shape["y"] + shape["width"]
      );
    },
    detectRectangle(x, y, shape) {
      return (
        x >= shape["x"] &&
        x <= shape["x"] + shape["width"] &&
        y >= shape["y"] &&
        y <= shape["y"] + shape["height"]
      );
    },
    detectLine(x, y, shape) {
      return (
        x >= shape["x"] && x <= shape["x"] + shape["width"] && y == shape["y"]
      );
    },
    detectCircle(x, y, shape) {
      return (
        Math.pow(x - shape["x"], 2) + Math.pow(y - shape["y"], 2) <=
        Math.pow(shape["radius"], 2)
      );
    },
    detectEllipse(x, y, shape) {
      return (
        Math.pow(x - shape["x"], 2) / Math.pow(shape["bigRadius"], 2) +
          Math.pow(y - shape["y"], 2) / Math.pow(shape["smallRadius"], 2) <=
        1
      );
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
  width: 100%;
}
html,
body {
  background-color: #eee;
  height: 100%;
  width: 100%;
  margin: 0;
}

.wrapper {
  margin: 0 auto;
  height: 70%;
  width: 100%;
  grid-row: 1;
}
.leftcolumn,
.rightcolumn {
  float: left;
}
.leftcolumn {
  height: 100%;
}
.rightcolumn {
  width: calc(100% - 120px);
  height: 100%;
}
</style>
