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
  let selectedShape = ref(null)
  let id = ref(0)
  let mouseDownState = ref(false)
  let startx = ref(0)
  let starty = ref(0)
  let endx = ref(0)
  let endy = ref(0)
  export default{
    props:{
      selected: String
    },
    setup(props){
      console.log(props.selected)
    },
    data(){
      return {
        c: "",
        ctx:""

      }
    },
    methods:{
      drawShapes(context, shapes){
        this.ctx.clearRect(0,0, this.c.width, this.c.height)
        for (let i = 0; i < shapes.length;i++){
          let shape = shapes[i]
          shape.draw(context)
        }
      },
      getColor(){
        const color = document.getElementById('color-selector')
        return color.value
      },
      async getShapes(){
        console.log("getting shapes")
        const response = await fetch(`http://localhost:${port}/all-shapes`)
        const shapesList = response.json()
      
        for(let i = 0; i < shapesList.length; i++){
          let shape = shapesList[i]
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
      async createShape(x, y, type, color1, length1, length2){
        
        await fetch(`http://localhost:${port}/create`, {
          method : "POST",
          body: JSON.stringify({
            x : x,
            y : y,
            type : type,
            color1 : color1,
            color2 : "white",
            legnth1 : length1,
            legnth2 : length2
          }),
          headers:{
            "Content-Type": "application/json; charset=utf-8"
          }
        }).then((response)=> console.log(response.ok)).then(this.getShapes()).then(this.drawShapes(this.ctx, shapes.value))
        
      },
      createLine(x,y,tox,toy){
        const line = new Line(id.value, x, y, this.getColor(), "white", false,tox, toy)
        id.value +=  1
        line.draw(this.ctx)
        shapes.value.push(line)
      },
      createSquare(x,y,endx){
        let dx = endx - x
        const square = new Square(id.value, x, y, this.getColor(), "white", false,dx)
        id.value +=  1
        square.draw(this.ctx)
        shapes.value.push(square)
      },
      createRectangle(x,y,endx,endy){
        let dx = endx - x
        let dy = endy - y
        const rectangle = new Rectangle(id.value, x, y, this.getColor(), "white", false,dx, dy)
        id.value +=  1
        rectangle.draw(this.ctx)
        shapes.value.push(rectangle)
      },
      createCircle(x,y,endx,endy){
        let dx = endx - x
        let dy = endy - y
        let radius = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2))
        const circle = new Circle(id.value, x, y, this.getColor(), "white", false,radius)
        id.value +=  1
        circle.draw(this.ctx)
        shapes.value.push(circle)
      },
      createEllipse(x,y,endx,endy){
        let dx = endx - x
        let dy = endy - y
        let width = Math.abs(dx)
        let height = Math.abs(dy)
        const ellipse = new Ellipse(id.value, x + dx/2, y + dy/2, this.getColor(), "white", false, width / 2, height / 2)
        id.value +=  1
        ellipse.draw(this.ctx)
        shapes.value.push(ellipse)
      },
      createTriangle(x,y,endx,endy){
        let dx = endx - x
        let dy = endy - y
        const triangle = new Triangle(id.value, x, y, this.getColor(), "white", false, dx, dy)
        id.value +=  1
        triangle.draw(this.ctx)
        shapes.value.push(triangle)
      },
      async deleteShape(shape){
        const id = shape.id
        await fetch(`http://localhost:${port}/delete`, {
          method : "POST",
          body: JSON.stringify({
            id : id,
          }),
          headers:{
            "Content-Type": "application/json; charset=utf-8"
          }
        }).then((response)=> console.log(response.ok)).then(this.getShapes()).then(this.drawShapes(this.ctx, shapes.value))
      },
      async moveShape(shape, startx, starty, endx, endy){
        const dx = endx - startx
        const dy = endy- starty
        const id = shape.id
        await fetch(`http://localhost:${port}/move`, {
          method : "POST",
          body: JSON.stringify({
            id : id,
            x : dx,
            y : dy,
          }),
          headers:{
            "Content-Type": "application/json; charset=utf-8"
          }
        }).then((response)=> console.log(response.ok)).then(this.getShapes()).then(this.drawShapes(this.ctx, shapes.value))
      },
      async pasteShape(x,y, shape){
        await fetch(`http://localhost:${port}/copy`, {
          method : "POST",
          body: JSON.stringify({
            id : shape.id,
            x : x,
            y : y,
          }),
          headers:{
            "Content-Type": "application/json; charset=utf-8"
          }
        }).then((response)=> console.log(response.ok)).then(this.getShapes()).then(this.drawShapes(this.ctx, shapes.value))
      },
      openOptions(){

      },
      getSelectedShape(startx, starty){
        for(let i = shapes.value.length-1; i >= 0; i--){
          if(shapes.value[i].isSelected(startx, starty)){
            selectedShape.value = shapes.value[i]
            return
          }
        }
        selectedShape.value = null
      },
      mousedown(e){
        e.preventDefault();
        startx.value = e.x - 10
        starty.value = e.y - 70
        if(this.selected == "move"){
          this.getSelectedShape(startx.value, starty.value)
        }
        mouseDownState.value = true
        
      },
      mouseup(e){
        if(mouseDownState.value){
          endx.value = e.x - 10
          endy.value = e.y -70
          if(this.selected == "line") this.createLine(startx.value, starty.value, endx.value, endy.value)
          else if(this.selected == "square") this.createSquare(startx.value, starty.value, endx.value)
          else if(this.selected == "rectangle") this.createRectangle(startx.value, starty.value, endx.value, endy.value)
          else if(this.selected == "circle") this.createCircle(startx.value, starty.value, endx.value, endy.value)
          else if(this.selected == "ellipse") this.createEllipse(startx.value, starty.value, endx.value, endy.value)
          else if(this.selected == "triangle") this.createTriangle(startx.value, starty.value, endx.value, endy.value)
          else if(this.selected == "move") this.moveShape(selectedShape.value, startx.value, starty.value, endx.value, endy.value)
        }
        mouseDownState.value = false
      },
      clicked(e){
        const x = e.x - 10
        const y = e.y - 70
        if(this.selected == "copy"){
          this.getSelectedShape(x,y)
          console.log(selectedShape.value)
        }else if(this.selected == "paste"){
          this.pasteShape(x,y, selectedShape.value)
        }else if (this.selected == "delete"){
          this.getSelectedShape(x,y)
          this.deleteShape(selectedShape.value)
        }else if (this.selected == "options"){
          this.getSelectedShape(x,y)
          
        }
      },
      async undo(){
        const response = await fetch(`http://localhost:${port}/undo`).then(console.log(response.ok))

      },
      async redo(){
        const response = await fetch(`http://localhost:${port}/redo`).then(console.log(response.ok))
      }
      },
      mounted() {
        this.c = document.getElementById("canvas")
        this.c.addEventListener("mousedown", (e)=>this.mousedown(e))
        this.c.addEventListener("mouseup", (e)=>this.mouseup(e))
        this.c.addEventListener("click", (e)=>this.clicked(e))
        const undo = document.getElementById("undo")
        undo.addEventListener("click", ()=>this.undo())
        const redo = document.getElementById("redo")
        redo.addEventListener("click", ()=>this.redo())
        document.body.addEventListener("mouseup", ()=>mouseDownState.value = false)
        this.ctx = this.c.getContext("2d")
        this.width = window.innerWidth - 20
        this.height = window.innerHeight - 100
        this.c.width = this.width
        this.c.height = this.height
        console.log(this.getColor())
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
  