export class Shape {
  constructor(id, type, posx, posy, color1, color2 = '#FFFFFF', fill = false) {
    this.id = id
    this.type = type
    this.posx = posx
    this.posy = posy
    this.color1 = color1
    this.color2 = color2
    this.fill = fill
  }
}
export class Rectangle extends Shape {
  constructor(id, posx, posy, color1, color2 = '#FFFFFF', fill = false, width, height) {
    super(id, 'rectangle', posx, posy, color1, color2, fill)
    this.width = width
    this.height = height
  }
  draw(context) {
    context.beginPath()
    context.strokeStyle = this.color1
    context.fillStyle = this.color2

    context.rect(this.posx, this.posy, this.width, this.height)
    if (this.fill) {
      context.fill()
    }
    context.stroke()
  }
  isSelected(x, y) {
    if (
      x >= Math.min(this.posx, this.posx + this.width) &&
      x <= Math.max(this.posx, this.posx + this.width) &&
      y >= Math.min(this.posy, this.posy + this.height) &&
      y <= Math.max(this.posy, this.posy + this.height)
    )
      return true
    else return false
  }
}
export class Circle extends Shape {
  constructor(id, posx, posy, color1, color2 = '#FFFFFF', fill = false, radius) {
    super(id, 'circle', posx, posy, color1, color2, fill)
    this.radius = radius
  }
  draw(context) {
    context.beginPath()
    context.strokeStyle = this.color1
    context.fillStyle = this.color2

    context.arc(this.posx, this.posy, this.radius, 0, 2 * Math.PI)
    if (this.fill) {
      context.fill()
    }
    context.stroke()
  }
  isSelected(x, y) {
    const dist = Math.sqrt(Math.pow(this.posx - x, 2) + Math.pow(this.posy - y, 2))
    if (dist <= this.radius) return true
    else return false
  }
}
export class Square extends Shape {
  constructor(id, posx, posy, color1, color2 = '#FFFFFF', fill = false, length) {
    super(id, 'square', posx, posy, color1, color2, fill)
    this.length = length
  }
  draw(context) {
    context.beginPath()
    context.strokeStyle = this.color1
    context.fillStyle = this.color2

    context.rect(this.posx, this.posy, this.length, this.length)
    if (this.fill) {
      context.fill()
    }
    context.stroke()
  }
  isSelected(x, y) {
    if (
      x >= Math.min(this.posx, this.posx + this.length) &&
      x <= Math.max(this.posx, this.posx + this.length) &&
      y >= Math.min(this.posy, this.posy + this.length) &&
      y <= Math.max(this.posy, this.posy + this.length)
    )
      return true
    else return false
  }
}
export class Ellipse extends Shape {
  constructor(id, posx, posy, color1, color2 = '#FFFFFF', fill = false, radiusx, radiusy) {
    super(id, 'ellipse', posx, posy, color1, color2, fill)
    this.radiusx = radiusx
    this.radiusy = radiusy
  }
  draw(context) {
    context.beginPath()
    context.strokeStyle = this.color1
    context.fillStyle = this.color2

    context.ellipse(this.posx, this.posy, this.radiusx, this.radiusy, 0, 0, 2 * Math.PI)
    if (this.fill) {
      context.fill()
    }
    context.stroke()
  }
  isSelected(x, y) {
    if (
      x >= Math.min(this.posx + this.radiusx, this.posx - this.radiusx) &&
      x <= Math.max(this.posx + this.radiusx, this.posx - this.radiusx) &&
      y >= Math.min(this.posy + this.radiusy, this.posy - this.radiusy) &&
      y <= Math.max(this.posy + this.radiusy, this.posy - this.radiusy)
    )
      return true
    else return false
  }
}
export class Triangle extends Shape {
  constructor(id, posx, posy, color1, color2 = '#FFFFFF', fill = false, base, height) {
    super(id, 'triangle', posx, posy, color1, color2, fill)
    this.base = base
    this.height = height
  }
  draw(context) {
    context.beginPath()
    context.strokeStyle = this.color1
    context.fillStyle = this.color2
    context.moveTo(this.posx, this.posy + this.height)
    context.lineTo(this.posx + this.base, this.posy + this.height)
    context.lineTo(this.posx + this.base / 2, this.posy)
    context.lineTo(this.posx, this.posy + this.height)
    context.closePath()

    if (this.fill) {
      context.fill()
    }
    context.stroke()
  }
  isSelected(x, y) {
    if (
      x >= Math.min(this.posx, this.posx + this.base) &&
      x <= Math.max(this.posx, this.posx + this.base) &&
      y >= Math.min(this.posy, this.posy + this.height) &&
      y <= Math.max(this.posy, this.posy + this.height)
    )
      return true
    else return false
  }
}
export class Line extends Shape {
  constructor(id, posx, posy, color1, color2 = '#FFFFFF', fill = false, endx, endy) {
    super(id, 'line', posx, posy, color1, color2, fill)
    this.endx = endx
    this.endy = endy
  }
  draw(context) {
    context.beginPath()
    context.strokeStyle = this.color1
    context.moveTo(this.posx, this.posy)
    context.lineTo(this.endx, this.endy)
    context.closePath()
    context.stroke()
  }
  isSelected(x, y) {
    if (
      x >= Math.min(this.posx, this.endx) &&
      x <= Math.max(this.posx, this.endx) &&
      y >= Math.min(this.posy, this.endy) &&
      y <= Math.max(this.posy, this.endy)
    )
      return true
    else return false
  }
}
