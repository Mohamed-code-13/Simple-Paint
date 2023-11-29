<template>
  <ToolBar />
  <ShapePicker />
</template>

<script>
import ShapePicker from "./components/ShapePicker.vue";
import ToolBar from "./components/ToolBar.vue";

export default {
  name: "App",
  components: {
    ShapePicker,
    ToolBar,
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
}
</style>
