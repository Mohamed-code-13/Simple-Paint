<template>
    <dialog  id="wrapper">

    </dialog>
</template>
<script>
    export default{
        name: "DialogComp",
        mounted(){
            this.d = document.getElementById("wrapper")
            this.d.show()
        }


    }

</script>
<style>
.wrapper{
    
}
</style>