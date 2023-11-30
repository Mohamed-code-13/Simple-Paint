<template>
  <div id="canvas-component">
    <canvas id="myCanvas"></canvas>
  </div>
</template>

<script>
export default {
  name: "CanvasComponent",
  props: {
    shapes: Array,
    drawingMode: Boolean,
  },
  data() {
    return {
      cnvs: null,
      ctx: null,
    };
  },
  mounted() {
    this.cnvs = document.getElementById("myCanvas");
    this.ctx = this.cnvs.getContext("2d");
    this.ctx.canvas.width = window.innerWidth;
    this.ctx.canvas.height = window.innerHeight;

    this.cnvs.addEventListener("mousedown", this.mousedown);
    this.drawShapes();
  },
  methods: {
    drawShapes() {
      for (let i = 0; i < this.shapes.length; ++i) {
        const shape = this.shapes[i];
        const type = shape["type"];
        switch (type) {
          case "rectangle":
            this.drawRect(shape);
            break;
          case "square":
            this.drawSquare(shape);
            break;
          case "circle":
            this.drawCircle(shape);
            break;
          case "line":
            this.drawLine(shape);
            break;
          case "ellipse":
            this.drawEllipse(shape);
            break;
        }
      }
    },
    drawSquare(square) {
      this.ctx.strokeRect(
        square["x"],
        square["y"],
        square["width"],
        square["width"]
      );
      this.ctx.stroke();
    },
    drawRect(rect) {
      this.ctx.strokeRect(rect["x"], rect["y"], rect["width"], rect["height"]);
      this.ctx.stroke();
    },
    drawCircle(circle) {
      this.ctx.beginPath();
      this.ctx.arc(circle["x"], circle["y"], circle["radius"], 0, 2 * Math.PI);
      this.ctx.stroke();
    },
    drawLine(line) {
      this.ctx.beginPath();
      this.ctx.moveTo(line["x"], line["y"]);
      this.ctx.lineTo(line["x"] + line["width"], line["y"]);
      this.ctx.stroke();
    },
    drawEllipse(ellipse) {
      this.ctx.beginPath();
      this.ctx.ellipse(
        ellipse["x"],
        ellipse["y"],
        ellipse["bigRadius"],
        ellipse["smallRadius"],
        ellipse["rotate"],
        0,
        2 * Math.PI
      );
      this.ctx.stroke();
    },
    mousedown(e) {
      if (this.drawingMode) {
        this.$emit("createShape", [e.offsetX, e.offsetY]);
      } else {
        this.$emit("modifyShape", [e.offsetX, e.offsetY]);
      }
    },
  },
};
</script>

<style scoped>
#canvas-component #myCanvas {
  /* height: 100%; */
  /* width: 100%; */
  border: 2px solid red;
}
</style>
