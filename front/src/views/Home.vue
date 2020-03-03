<template>
  <div class="home">
    <Navbar/>
    <router-view/>
  </div>
</template>

<script>
import Navbar from '../components/Navbar.vue';

export default {
  name: 'Dashboard',
  components: {
    Navbar,
  },
};
</script>

<style scoped>
  .home {
    height: 100%;
    overflow: hidden;
  }
</style>
