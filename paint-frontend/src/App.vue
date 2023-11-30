<template>
  <ToolBar />
  <div class="wrapper">
    <div class="leftcolumn">
      <ShapePicker :selectedShape="selectedShape" @changeShape="changeShape" />
      <EditBar @changeEdit="changeEdit" />
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
      shapes: [],
    };
  },
  mounted() {
    this.getAllShapes();
  },
  methods: {
    changeShape(s) {
      this.selectedShape = s;
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
