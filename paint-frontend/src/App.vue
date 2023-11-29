<template>
  <ShapePicker />
</template>

<script>
import ShapePicker from "./components/ShapePicker.vue";

export default {
  name: "App",
  components: {
    ShapePicker,
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
}
html,
body {
  background-color: #eee;
  height: 100%;
}
</style>
