<template>
  <div id="tool-bar">
    <div class="grid-container">
      <button
        v-for="option in allOptions"
        :key="option"
        @click="changeOption(option)"
        :class="{ 'grid-item': true, selected: option === selectedOption }"
      >
        {{ option }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ToolBar",
  props: {
    selectedOption: String,
  },
  methods: {
    changeOption(option) {
      this.$emit("changeOption", option);
    },
  },
  computed: {
    allOptions() {
      return ["color", "move", "rotate", "resize", "copy", "delete"];
    },
  },
};
</script>

<style scoped>
#tool-bar {
  background: #ac5353;
  width: 100%;
  height: 60px;
  padding: 10px;
  margin: 10px 0px;
}
.grid-container {
  grid-row: 1;
}

.grid-item {
  text-align: center;
  margin: 5px;
  padding: 10px 15px;
  background: #fcb1a6;
  font-weight: bold;
  font-size: 18px;
  border-radius: 12px;
}
.selected {
  background: rgb(224, 97, 24);
  color: #fff;
}
</style>
