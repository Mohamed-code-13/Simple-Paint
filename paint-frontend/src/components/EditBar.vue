<template>
  <div id="editBar">
    <div class="title">Edit</div>
    <div class="grid-container">
      <button
        v-for="tool in allTools"
        :key="tool"
        @click="changeEdit(tool)"
        class="grid-item"
      >
        {{ tool }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "EditBar",
  methods: {
    changeEdit(tool) {
      this.$emit("changeEdit", tool);
    },
  },
  computed: {
    allTools() {
      return ["clear", "undo", "redo", "save", "load"];
    },
  },
};
</script>

<style scoped>
.title {
  color: #fff;
  font-weight: bold;
  padding: 10px;
  border-bottom: 2px solid black;
  border-top: 2px solid black;
}
#editBar {
  background: #e08484;
  width: 100px;
  height: 90%;
}
.grid-container {
  display: grid;
  grid-template-columns: 1fr;
}

.grid-item {
  text-align: center;
  margin: 5px;
  padding: 10px 5px;
  background: #c56a5c;
  color: white;
  font-weight: bold;
  border-radius: 8px;
}
</style>
