<template>
  <ToolBar />
  <div class="wrapper">
    <div class="leftcolumn">
      <ShapePicker :selectedShape="selectedShape" @changeShape="changeShape" />
    </div>
    <div class="rightcolumn">
      <CanvasComponent
        :shapes="shapes"
        :key="JSON.stringify(shapes)"
        @createShape="createShape"
      />
    </div>
  </div>
</template>

<script>
import ShapePicker from "./components/ShapePicker.vue";
import ToolBar from "./components/ToolBar.vue";
import CanvasComponent from "./components/CanvasComponent.vue";

const apiUrl = "http://localhost:8080";

export default {
  name: "App",
  data() {
    return {
      selectedShape: "line",
      shapes: [],
    };
  },
  methods: {
    changeShape(s) {
      this.selectedShape = s;
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
  },
  components: {
    ShapePicker,
    ToolBar,
    CanvasComponent,
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
  height: calc(100% - 110px);
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
