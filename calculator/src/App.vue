<template>
  <div class="layout-container">
    <Navbar />
    <RouterView></RouterView>
  </div>
</template>

<style scoped>

</style>

<script>
import Navbar from "@/components/Navbar.vue";
export default {
  components: {Navbar}
}
</script>