<!-- eslint-disable vue/no-mutating-props -->

<template>
    <div id="canvas-component">
      <canvas id="canvas"></canvas>
    </div>
</template>
  
<script>
  import {Line, Rectangle, Triangle, Circle, Square, Ellipse} from '../models/shapes.js'
  import {ref} from 'vue'
  const port = 8080
  let shapes = ref([])
  let mouseDownState = ref(false)
  export default{
    props:{
      selected: String
    },
    setup(props){
      console.log(props.selected)
    },
    methods:{
      drawShapes(context, shapes){
        for (let key in shapes){
          let shape = shapes[key]
          shape.draw(context)
        }
      },
      async getShapes(){
        console.log("getting shapes")
        const response = await fetch(`https://localhost:${port}/all-shapes/`)
        const shapesList = response.json()
      
        for(let shape in shapesList ){
          let newShape
          if(shape.type == "line"){
            newShape = new Line(shape.id, shape.posx, shape.posy, shape.color1, shape.color2, shape.fill, shape.endx, shape.endy)
          }else if(shape.type == "square"){
            newShape = new Square(shape.id, shape.posx, shape.posy, shape.color1, shape.color2, shape.fill, shape.length)
          }else if(shape.type == "rectangle"){
            newShape = new Rectangle(shape.id, shape.posx, shape.posy, shape.color1, shape.color2, shape.fill, shape.width, this.height)
          }else if(shape.type == "circle"){
            newShape = new Circle(shape.id, shape.posx, shape.posy, shape.color1, shape.color2, shape.fill, shape.radius)
          }else if(shape.type == "triangle"){
            newShape = new Triangle(shape.id, shape.posx, shape.posy, shape.color1, shape.color2, shape.fill, shape.base, shape.height)
          }else{
            newShape = new Ellipse(shape.id, shape.posx, shape.posy, shape.color1, shape.color2, shape.fill, shape.radiusx, shape.radiusy)
          }
          console.log(newShape)
          shapes.value.push(newShape)
      }
    },
    mousedown(e){
      
      
      console.log(e)
      mouseDownState.value = true
    },
    mouseup(e){
      mouseDownState.value = false
      console.log(e)
    }
    },
    mounted() {
      const c = document.getElementById("canvas")
      c.addEventListener("mousedown", (e)=>this.mousedown(e))
      c.addEventListener("mouseup", (e)=>this.mouseup(e))
      document.body.addEventListener("mouseup", ()=>mouseDownState.value = false)
      let ctx = c.getContext("2d")
      this.width = window.innerWidth - 20
      this.height = window.innerHeight - 100
      c.width = this.width
      c.height = this.height
      //console.log(shapes)
      //this.getShapes()
      const square = new Rectangle(1, 150, 200, 'red', 'white', false, 200, 100)
      this.drawShapes(ctx, [square])
      
    },
    
   
  }

  
</script>
  
<style scoped>
    #canvas{
        width: auto;
        border-radius: 25px;
        border: 2px solid black;
        margin-top: 10px;
    }
</style>
  