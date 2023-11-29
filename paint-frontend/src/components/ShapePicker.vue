<template>
  <div id="shapePicker">
    <div class="grid-container">
      <button
        v-for="shape in allShapes"
        :key="shape"
        @click="changeShape(shape)"
        :class="{ 'grid-item': true, selected: shape === selectedShape }"
      >
        {{ shape }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ShapePicker",
  props: {
    selectedShape: String,
  },
  methods: {
    changeShape(shape) {
      this.$emit("changeShape", shape);
    },
  },
  computed: {
    allShapes() {
      return ["line", "square", "rectangle", "circle", "ellipse"];
    },
  },
};
</script>

<style scoped>
#shapePicker {
  background: #e08484;
  width: 100px;
  height: 100%;
}
.grid-container {
  display: grid;
  grid-template-columns: 1fr;
}

.grid-item {
  text-align: center;
  margin: 5px;
  padding: 10px 5px;
  background: #fcb1a6;
  font-weight: bold;
  border-radius: 8px;
}

.selected {
  background: rgb(224, 97, 24);
  color: #fff;
}
</style>
