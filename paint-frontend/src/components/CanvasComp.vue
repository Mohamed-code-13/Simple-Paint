<!-- eslint-disable vue/no-mutating-props -->

<template>
  <div id="canvas-component">
    <canvas id="canvas"></canvas>
  </div>
</template>

<script>
import { Line, Rectangle, Triangle, Circle, Square, Ellipse } from '../models/shapes.js'
import { ref } from 'vue'
const port = 8080

export default {
  props: {
    selected: String
  },
  setup(props) {
    console.log(props.selected)
  },
  data() {
    return {
      c: '',
      ctx: '',
      shapes: [],
      selectedShape: null,
      mouseDownState: false,
      startx: 0,
      starty: 0,
      endx: 0,
      endy: 0
    }
  },
  methods: {
    drawShapes() {
      this.ctx.clearRect(0, 0, this.c.width, this.c.height)
      for (let i = 0; i < this.shapes.length; i++) {
        let shape = this.shapes[i]
        shape.draw(this.ctx)
      }
    },
    getColor() {
      const color = document.getElementById('color-selector')
      return color.value
    },
    async getShapes() {
      const response = await fetch(`http://localhost:${port}/all-shapes`)
      const shapesList = await response.json()

      this.shapes = []
      for (let i = 0; i < shapesList.length; i++) {
        let shape = shapesList[i]
        let newShape
        if (shape.type == 'line') {
          newShape = new Line(
            shape.id,
            shape.x,
            shape.y,
            shape.borderColor,
            shape.filledColor,
            shape.filled,
            shape.endX,
            shape.endY
          )
        } else if (shape.type == 'square') {
          newShape = new Square(
            shape.id,
            shape.x,
            shape.y,
            shape.borderColor,
            shape.filledColor,
            shape.filled,
            shape.width
          )
        } else if (shape.type == 'rectangle') {
          newShape = new Rectangle(
            shape.id,
            shape.x,
            shape.y,
            shape.borderColor,
            shape.filledColor,
            shape.filled,
            shape.width,
            shape.height
          )
        } else if (shape.type == 'circle') {
          newShape = new Circle(
            shape.id,
            shape.x,
            shape.y,
            shape.borderColor,
            shape.filledColor,
            shape.filled,
            shape.radius
          )
        } else if (shape.type == 'triangle') {
          newShape = new Triangle(
            shape.id,
            shape.x,
            shape.y,
            shape.borderColor,
            shape.filledColor,
            shape.filled,
            shape.base,
            shape.height
          )
        } else {
          newShape = new Ellipse(
            shape.id,
            shape.x,
            shape.y,
            shape.borderColor,
            shape.filledColor,
            shape.filled,
            shape.radiusX,
            shape.radiusY
          )
        }
        this.shapes.push(newShape)
      }
    },
    async createShape(x, y, type, color1, length1, length2) {
      await fetch(`http://localhost:${port}/create`, {
        method: 'POST',
        body: JSON.stringify({
          x: x,
          y: y,
          type: type,
          color1: color1,
          color2: '#FFFFFF',
          length1: length1 | 0,
          length2: length2 | 0
        }),
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }
      })
      await this.getShapes()
      this.drawShapes()
    },
    createLine(x, y, tox, toy) {
      this.createShape(x, y, 'line', this.getColor(), tox, toy)
    },
    createSquare(x, y, endx) {
      let dx = endx - x
      this.createShape(x, y, 'square', this.getColor(), dx, 0)
    },
    createRectangle(x, y, endx, endy) {
      let dx = endx - x
      let dy = endy - y
      this.createShape(x, y, 'rectangle', this.getColor(), dx, dy)
    },
    createCircle(x, y, endx, endy) {
      let dx = endx - x
      let dy = endy - y
      let radius = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2))
      this.createShape(x, y, 'circle', this.getColor(), radius)
    },
    createEllipse(x, y, endx, endy) {
      let dx = endx - x
      let dy = endy - y
      let width = Math.abs(dx)
      let height = Math.abs(dy)
      this.createShape(x + dx / 2, y + dy / 2, 'ellipse', this.getColor(), width / 2, height / 2)
    },
    createTriangle(x, y, endx, endy) {
      let dx = endx - x
      let dy = endy - y
      this.createShape(x, y, 'triangle', this.getColor(), dx, dy)
    },
    async deleteShape(shape) {
      const id = shape.id
      console.log(id)
      await fetch(`http://localhost:${port}/delete`, {
        method: 'POST',
        body: JSON.stringify({
          id: id
        }),
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }
      })
      await this.getShapes()
      this.drawShapes()
    },
    async moveShape(shape, startx, starty, endx, endy) {
      const dx = endx - startx
      const dy = endy - starty
      const id = shape.id
      await fetch(`http://localhost:${port}/move`, {
        method: 'POST',
        body: JSON.stringify({
          id: id,
          dx: dx,
          dy: dy
        }),
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }
      })
      await this.getShapes()
      this.drawShapes()
    },
    async pasteShape(x, y, shape) {
      await fetch(`http://localhost:${port}/copy`, {
        method: 'POST',
        body: JSON.stringify({
          id: shape.id,
          x: x,
          y: y
        }),
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }
      })
      await this.getShapes()
      this.drawShapes()
    },
    openOptions() {
      const shape = this.selectedShape
      let parameters = ['Border color', 'Filled Color', 'Fill']

      let defaultValues = [shape.color1, shape.color2, shape.fill]

      switch (shape.type) {
        case 'square':
          parameters.push('Length')
          defaultValues.push(shape.length)
          break
        case 'circle':
          parameters.push('Radius')
          defaultValues.push(shape.radius)
          break
        case 'line':
          parameters.push('End X')
          parameters.push('End Y')
          defaultValues.push(shape.endx)
          defaultValues.push(shape.endy)
          break
        case 'rectangle':
          parameters.push('Width')
          parameters.push('Height')
          defaultValues.push(shape.width)
          defaultValues.push(shape.height)
          break
        case 'ellipse':
          parameters.push('Radius X')
          parameters.push('Radius Y')
          defaultValues.push(shape.radiusx)
          defaultValues.push(shape.radiusy)
          break
        case 'triangle':
          parameters.push('Base')
          parameters.push('Height')
          defaultValues.push(shape.base)
          defaultValues.push(shape.height)
          break
      }

      this.$emit('open-dialog', [parameters, defaultValues])
    },
    getSelectedShape(startx, starty) {
      for (let i = this.shapes.length - 1; i >= 0; i--) {
        if (this.shapes[i].isSelected(startx, starty)) {
          this.selectedShape = this.shapes[i]
          return
        }
      }
      this.selectedShape = null
    },
    mousedown(e) {
      e.preventDefault()
      this.startx = e.x - 10
      this.starty = e.y - 70
      if (this.selected == 'move') {
        this.getSelectedShape(this.startx, this.starty)
      }
      this.mouseDownState = true
    },
    mouseup(e) {
      if (this.mouseDownState) {
        this.endx = e.x - 10
        this.endy = e.y - 70
        if (this.selected == 'line') this.createLine(this.startx, this.starty, this.endx, this.endy)
        else if (this.selected == 'square') this.createSquare(this.startx, this.starty, this.endx)
        else if (this.selected == 'rectangle')
          this.createRectangle(this.startx, this.starty, this.endx, this.endy)
        else if (this.selected == 'circle')
          this.createCircle(this.startx, this.starty, this.endx, this.endy)
        else if (this.selected == 'ellipse')
          this.createEllipse(this.startx, this.starty, this.endx, this.endy)
        else if (this.selected == 'triangle')
          this.createTriangle(this.startx, this.starty, this.endx, this.endy)
        else if (this.selected == 'move')
          if (this.selectedShape)
            this.moveShape(this.selectedShape, this.startx, this.starty, this.endx, this.endy)
      }
      this.mouseDownState = false
    },
    clicked(e) {
      const x = e.x - 10
      const y = e.y - 70
      if (this.selected == 'copy') {
        this.getSelectedShape(x, y)
      } else if (this.selected == 'paste') {
        if (this.selectedShape) this.pasteShape(x, y, this.selectedShape)
      } else if (this.selected == 'delete') {
        this.getSelectedShape(x, y)
        if (this.selectedShape) this.deleteShape(this.selectedShape)
      } else if (this.selected == 'options') {
        this.getSelectedShape(x, y)
        if (this.selectedShape) this.openOptions()
      }
    },
    async undo() {
      const response = await fetch(`http://localhost:${port}/undo`)
      await this.getShapes()
      this.drawShapes()
    },
    async redo() {
      const response = await fetch(`http://localhost:${port}/redo`)
      await this.getShapes()
      this.drawShapes()
    },
    async clear() {
      await fetch(`http://localhost:${port}/clear`)
      await this.getShapes()
      this.drawShapes()
    },
    async applyChanges(newValues) {
      const shape = this.selectedShape

      await fetch(`http://localhost:${port}/color`, {
        method: 'POST',
        body: JSON.stringify({
          id: shape.id,
          color1: newValues[0],
          color2: newValues[1],
          fill: newValues[2]
        }),
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }
      })
      console.log(newValues[3])
      console.log(newValues[4])
      await fetch(`http://localhost:${port}/resize`, {
        method: 'POST',
        body: JSON.stringify({
          id: shape.id,
          length1: newValues[3],
          length2: newValues.length === 5 ? newValues[4] : 0
        }),
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }
      })

      await this.getShapes()
      this.drawShapes()
    }
  },
  expose: ['applyChanges', 'drawShapes', 'getShapes'],
  async mounted() {
    this.c = document.getElementById('canvas')
    this.c.addEventListener('mousedown', (e) => this.mousedown(e))
    this.c.addEventListener('mouseup', (e) => this.mouseup(e))
    this.c.addEventListener('click', (e) => this.clicked(e))
    const undo = document.getElementById('undo')
    undo.addEventListener('click', () => this.undo())
    const redo = document.getElementById('redo')
    redo.addEventListener('click', () => this.redo())
    const clear = document.getElementById('clear')
    clear.addEventListener('click', () => this.clear())
    document.body.addEventListener('mouseup', () => (this.mouseDownState = false))
    this.ctx = this.c.getContext('2d')
    this.width = window.innerWidth - 20
    this.height = window.innerHeight - 100
    this.c.width = this.width
    this.c.height = this.height
    await this.getShapes()
    this.drawShapes()
  }
}
</script>

<style scoped>
#canvas {
  width: auto;
  border-radius: 25px;
  border: 2px solid black;
  margin-top: 10px;
}
</style>
