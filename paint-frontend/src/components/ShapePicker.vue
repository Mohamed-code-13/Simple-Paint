<template>
  <div id="shapePicker">
    <div class="grid-container">
      <button v-for="shape in allShapes" :key="shape" class="grid-item">
        {{ shape }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ShapePicker",
  computed: {
    allShapes() {
      return ["Line", "Square", "Rectangle", "Circle", "Ellipse"];
    },
  },
};
</script>

<style scoped>
#shapePicker {
  background: #ac5353;
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
</style>
