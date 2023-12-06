<!-- eslint-disable no-unused-vars -->
<script>
import { ref } from 'vue'
import CanvasComp from './components/CanvasComp.vue'
import DialogComp from './components/DialogComp.vue'

const port = 8080

const applyChangesRef = ref(0)

export default {
  data() {
    return {
      showDialog: false,
      selected: ref('none'),
      requirements: [],
      defaultValues: []
    }
  },
  components: {
    CanvasComp,
    DialogComp
  },
  methods: {
    changeParameters(values) {
      this.showDialog = false
      this.$refs.applyChangesRef.applyChanges(values)
    },
    openDialog(args) {
      this.requirements = args[0]
      this.defaultValues = args[1]
      this.showDialog = true
    },
    async load() {
      const file = document.getElementById('file-input').files[0]
      if (file != null && (file.name.endsWith('.json') || file.name.endsWith('.xml'))) {
        const fileReader = new FileReader(file)
        fileReader.onload = async (fileLoadedEvent) => {
          const fileContent = fileLoadedEvent.target.result
          if (file.name.endsWith('.json')) {
            await this.sendFile(fileContent, 'json')
          } else {
            await this.sendFile(fileContent, 'xml')
          }
        }
        fileReader.readAsText(file, 'UTF-8')
        await this.$refs.applyChanges.getShapes()
        this.$refs.applyChanges.drawShapes()
      }
    },
    async sendFile(content, format) {
      await fetch(`http://localhost:${port}/load-${format}`, {
        method: 'POST',
        body: content
      })
    },

    async save() {
      const jsonResponse = await fetch(`http://localhost:${port}/save-json`)
      let data = await jsonResponse.text()
      this.getFile('json', data)
      const xmlResponse = await fetch(`http://localhost:${port}/save-xml`)
      data = await xmlResponse.text()
      this.getFile('xml', data)
    },
    getFile(type, data) {
      const blob = new Blob([data], { type: `text/plain` })
      const e = document.createEvent('MouseEvents'),
        a = document.createElement('a')
      a.download = `savefile.${type}`
      a.href = window.URL.createObjectURL(blob)
      a.dataset.downloadurl = [`savefile/${type}`, a.download, a.href].join(':')
      e.initEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null)
      a.dispatchEvent(e)
    }
  }
}
</script>

<template>
  <div id="container">
    <DialogComp
      v-if="showDialog"
      :requirements="requirements"
      :default-values="defaultValues"
      @changeParameters="changeParameters"
    />
    <div id="toolbox">
      <div id="tool-container">
        <li>
          <div class="tool" @click="selected = 'line'">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/ios/50/line--v1.png"
              alt="line--v1"
            />
          </div>
        </li>
        <li>
          <div class="tool" @click="selected = 'square'">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/ios/50/square-90.png"
              alt="square-90"
            />
          </div>
        </li>
        <li>
          <div class="tool" @click="selected = 'circle'">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/ios/50/circled.png"
              alt="circled"
            />
          </div>
        </li>
        <li>
          <div class="tool" @click="selected = 'rectangle'">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/material-sharp/24/rectangle-stroked.png"
              alt="rectangle-stroked"
            />
          </div>
        </li>
        <li>
          <div class="tool" @click="selected = 'triangle'">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/material-outlined/24/triangle-stroked.png"
              alt="triangle-stroked"
            />
          </div>
        </li>
        <li>
          <div class="tool" @click="selected = 'ellipse'">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/ios/50/ellipse-stroked--v1.png"
              alt="ellipse-stroked--v1"
            />
          </div>
        </li>
        <li>
          <div class="tool" @click="selected = 'copy'">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/material-outlined/24/copy.png"
              alt="copy"
            />
          </div>
        </li>
        <li>
          <div class="tool" @click="selected = 'paste'">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/material-outlined/24/paste.png"
              alt="paste"
            />
          </div>
        </li>
        <li>
          <div class="tool" @click="selected = 'delete'">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/ios/50/trash--v1.png"
              alt="trash--v1"
            />
          </div>
        </li>
        <li>
          <div class="tool" id="clear">
            <img width="25" height="25" src="https://img.icons8.com/ios/50/broom.png" alt="broom" />
          </div>
        </li>
        <li>
          <div class="tool" @click="selected = 'move'">
            <img width="25" height="25" src="https://img.icons8.com/ios/50/move.png" alt="move" />
          </div>
        </li>
        <div><input type="color" name="color" id="color-selector" value="black" /></div>
        <li>
          <div class="tool" id="undo">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/material-outlined/24/undo.png"
              alt="undo"
            />
          </div>
        </li>
        <li>
          <div class="tool" id="redo">
            <img
              width="24"
              height="24"
              src="https://img.icons8.com/material-outlined/24/redo.png"
              alt="redo"
            />
          </div>
        </li>
        <li>
          <div class="tool" @click="selected = 'options'">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/material-rounded/24/sorting-options.png"
              alt="sorting-options"
            />
          </div>
        </li>
        <li>
          <div class="tool" @click="save">
            <img width="25" height="25" src="https://img.icons8.com/ios/50/save.png" alt="save" />
          </div>
        </li>
        <li>
          <div class="tool" @click="load">
            <img
              width="25"
              height="25"
              src="https://img.icons8.com/fluency-systems-regular/48/load-from-file.png"
              alt="load-from-file"
            />
          </div>
        </li>
      </div>

      <div id="active-box">
        <span>{{ selected }}</span>
      </div>
      <div id="file-selector"><input type="file" id="file-input" name="loadfile" /></div>
    </div>
    <div>
      <CanvasComp :selected="selected" @open-dialog="openDialog" ref="applyChangesRef" />
    </div>
  </div>
</template>

<style scoped>
#file-selector {
  float: left;
  margin-top: 13px;
}
#color-selector {
  float: left;
  margin-top: 10px;
}
#toolbox {
  height: 50px;
  border-radius: 24px;
  width: 100%;
  background-color: rgb(8, 127, 141);
}
#active-box {
  float: right;
  margin-right: 100px;
  padding: 10px 10px 10px 10px;
  height: 20px;
  margin-top: 3px;
  border-radius: 5px;
  border: 2px solid black;
}
#tool-container {
  margin-left: 24px;
}
li {
  float: left;
  list-style-type: none;
}
.tool {
  height: 10%;
  padding: 10px;
}
.tool:hover {
  background-color: rgb(6, 81, 90);
}
.selected {
  background-color: rgb(87, 87, 87);
}
</style>
