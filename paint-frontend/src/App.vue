<script setup>
  import{ref} from 'vue'
  import CanvasComp from './components/CanvasComp.vue'
  let selected = ref("none")
  const save = ()=>{

  }
  const load = ()=>{
    
  }
</script>

<template>
  <div id="container">
    <div id="toolbox">
      <div id="tool-container">
        <li>
        <div class="tool" @click="selected='line'"><img width="25" height="25" src="https://img.icons8.com/ios/50/line--v1.png" alt="line--v1"/></div>
      </li>
      <li>
        <div class="tool" @click="selected='square'"><img width="25" height="25" src="https://img.icons8.com/ios/50/square-90.png" alt="square-90"/></div>
      </li>
      <li>
        <div class="tool" @click="selected='circle'"><img width="25" height="25" src="https://img.icons8.com/ios/50/circled.png" alt="circled"/></div>
      </li>
      <li>
        <div class="tool" @click="selected='rectangle'"><img width="25" height="25" src="https://img.icons8.com/material-sharp/24/rectangle-stroked.png" alt="rectangle-stroked"/></div>
      </li>
      <li>
        <div class="tool" @click="selected='triangle'"><img width="25" height="25" src="https://img.icons8.com/material-outlined/24/triangle-stroked.png" alt="triangle-stroked"/></div>
      </li>
      <li>
        <div class="tool" @click="selected='ellipse'"><img width="25" height="25" src="https://img.icons8.com/ios/50/ellipse-stroked--v1.png" alt="ellipse-stroked--v1"/></div>
      </li>
      <li>
        <div class="tool" @click="selected='copy'"><img width="25" height="25" src="https://img.icons8.com/material-outlined/24/copy.png" alt="copy"/></div>
      </li>
      <li>
        <div class="tool" @click="selected='paste'"><img width="25" height="25" src="https://img.icons8.com/material-outlined/24/paste.png" alt="paste"/></div>
      </li>
      <li>
        <div class="tool" @click="selected='delete'"><img width="25" height="25" src="https://img.icons8.com/ios/50/trash--v1.png" alt="trash--v1"/></div>
      </li>
      <li>
        <div class="tool" @click="selected='move'"><img width="25" height="25" src="https://img.icons8.com/ios/50/move.png" alt="move"/></div>
      </li>
      <li>
        <div class="tool" id="undo"><img width="25" height="25" src="https://img.icons8.com/material-outlined/24/undo.png" alt="undo"/></div>
      </li>
      <li>
        <div class="tool" id="redo"><img width="24" height="24" src="https://img.icons8.com/material-outlined/24/redo.png" alt="redo"/></div>
      </li>
      <li>
        <div class="tool" @click="selected='options'"><img width="25" height="25" src="https://img.icons8.com/material-rounded/24/sorting-options.png" alt="sorting-options"/></div>
      </li>
      <li>
        <div class="tool" @click="save"><img width="25" height="25" src="https://img.icons8.com/ios/50/save.png" alt="save"/></div>
      </li>
      <li>
        <div class="tool" @click="load"><img width="25" height="25" src="https://img.icons8.com/fluency-systems-regular/48/load-from-file.png" alt="load-from-file"/></div>
      </li>
      </div>
      <div><input type="color" name="color" id="color-selector" value="black"></div>
      <div id="active-box"><span>{{ selected }}</span></div>
      
    </div>
    <div>
      
        <CanvasComp :selected="selected"/>
      
      
    </div>
  </div>

</template>

<style scoped>

#color-selector{
  float: left;
  margin-top:10px
}
#toolbox{
  height: 50px;
  border-radius: 24px;
  width: 100%;
  background-color: rgb(8, 127, 141);
  
}
#active-box{
  float: right;
  margin-right: 100px;
  padding: 10px 10px 10px 10px ;
  height: 20px;
  margin-top:3px;
  border-radius: 5px;
  border: 2px solid black
}
#tool-container{
  margin-left: 24px;
}
li{
  float: left;
  list-style-type: none;
  
}
.tool{
  height: 10%;
  padding: 10px;
  
  
}
.tool:hover{
  background-color: rgb(6, 81, 90);
}
.selected{
  background-color: rgb(87, 87, 87);
}

</style>
