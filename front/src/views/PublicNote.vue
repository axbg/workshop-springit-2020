<template>
  <div class="container">
    <Navbar />
    <div class="content" v-html="escapedContent" />
  </div>
</template>

<script>
import Navbar from '../components/Navbar.vue';

export default {
  name: 'PublicNote',
  data() {
    return {
      content: '',
    };
  },
  beforeMount() {
    // try to load content
  },
  computed: {
    escapedContent() {
      return this.$sanitize(this.content, {
        allowedTags: this.$sanitize.defaults.allowedTags.concat(['img']),
      });
    },
  },
  components: {
    Navbar,
  },
};
</script>

<style scoped>
.container {
  height: 100%;
  text-align: left;
}
.content {
  height: 80vh;
  margin: 0 auto;
  margin: 10px;
  overflow: auto;
}
</style>
