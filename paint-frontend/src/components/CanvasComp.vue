<!-- eslint-disable vue/no-mutating-props -->

<template>
    <div id="canvas-component">
      <canvas id="canvas"></canvas>
    </div>
</template>
  
<script>
  import {Line, Rectangle, Triangle, Circle, Square, Ellipse} from '../models/shapes.js'
  const port = 8080
  //let shapes = []
  export default{
    props:{
      shapes: Array
    },
    async setup(){
      // eslint-disable-next-line vue/no-mutating-props
      this.getShapes()
    },
    methods:{
      drawShapes(context, shapes){
        for (let shape in shapes){
          shape.draw(context);
        }
      },
    },
    async getShapes(){
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
        // eslint-disable-next-line vue/no-mutating-props
        this.shapes.push(newShape)
      }
    },

    mounted() {
      
      const c = document.getElementById("canvas")
      let ctx = c.getContext("2d")
      this.width = 1000
      this.height = 600
      this.drawShapes(ctx, this.shapes)
    },
    
   
  }

  
</script>
  
<style scoped>
    #canvas{
        width: auto;
    }
</style>
  