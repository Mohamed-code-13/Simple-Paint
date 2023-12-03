<script setup>
  import CanvasComp from './components/CanvasComp.vue'
  let selected = "none"

  
</script>

<template>
  <div id="container">
    <div id="toolbox">
      <li>
        <div class="tool" onclick="selected='line'">line</div>
      </li>
      <li>
        <div class="tool" onclick="selected='square'">square</div>
      </li>
      <li>
        <div class="tool">circle</div>
      </li>
      <li>
        <div class="tool">rectangle</div>
      </li>
      <li>
        <div class="tool">triangle</div>
      </li>
      <li>
        <div class="tool">ellipse</div>
      </li>
    </div>
    <div>
      
        <CanvasComp :selected="selected"/>
      
      
    </div>
  </div>

</template>

<style scoped>
#toolbox{
  height: 60px;
  width: 100%;
  background-color: gray;
}
li{
  float: left;
  list-style-type: none;
  
}
.tool{
  height: 10%;
  padding: 10px;
  margin-top: 10px;
  
}
.tool:hover{
  background-color: rgb(87, 87, 87);
}
.selected{
  background-color: rgb(87, 87, 87);
}
</style>
