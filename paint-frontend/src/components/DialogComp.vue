<!-- eslint-disable vue/require-v-for-key -->
<template>
  <div class="centertile">
    <dialog id="wrapper">
      <div class="title">Change parameters</div>
      <div class="requirement" v-for="(req, index) in requirements">
        <div class="property">{{ req }}</div>
        <input :type="types[index]" :value="defaultValues[index]" v-model="values[index]" />
      </div>
      <!-- <button class="cancel">Cancel</button> -->
      <button class="save" @click="onSave">Save</button>
    </dialog>
  </div>
</template>
<script>
export default {
  name: 'DialogComp',
  mounted() {
    this.d = document.getElementById('wrapper')
    this.d.show()
  },
  data() {
    return {
      types: ['color', 'color', 'checkbox', 'number', 'number'],
      values: this.defaultValues
    }
  },
  props: {
    requirements: Array,
    defaultValues: Array
  },
  methods: {
    onSave() {
      this.$emit('changeParameters', this.values)
    }
  }
}
</script>
<style scoped>
#centerpoint {
  top: 50%;
  left: 50%;
  position: absolute;
}

dialog {
  width: 300px;
  height: 300px;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -150px;
  margin-top: -150px;
  background: rgb(236, 248, 255);
  font-family: Arial, Helvetica, sans-serif;
}

.title {
  font-size: 20px;
  font-weight: bold;
  margin: auto;
  width: 100%;
  text-align: center;
}
button {
  width: 100%;
  margin: 10 auto;
  margin-top: 12px;
  padding: 12px;
  border-radius: 12px;
  background-color: rgb(8, 127, 141);
  color: #ffffff;
  font-size: 20px;
}

input {
  width: 100%;
}
</style>
