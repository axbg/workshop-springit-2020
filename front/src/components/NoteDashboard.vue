<template>
  <div class="container">
    <NoteTile v-for="(note, index) in notes" :key="index" v-bind:id="note.id"
    :content="note.content"/>
          <infinite-loading
        v-if="displayInfinite"
        @infinite="scrollHandler"
        class="infinite-loader"
        :distance="20"
        spinner="waveDots"
      />
  </div>
</template>

<script>
import NoteTile from './NoteTile.vue';

export default {
  name: 'NoteDashboard',
  data: () => ({
    displayInfinite: true,
    notes: [
      {
        id: 1,
        content: 'content#1',
      },
      {
        id: 2,
        content: 'content#2',
      },
      {
        id: 3,
        content: 'content#3',
      },
      {
        id: 4,
        content: 'content#4',
      },
    ],
  }),
  methods: {
    async scrollHandler($state) {
      $state.complete();
    },
  },
  components: {
    NoteTile,
  },
};
</script>

<style scoped>
.container {
  width: 100%;
  height: 90vh;
  background-color: white;
  padding: 15px;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  grid-auto-rows: 170px;
  column-gap: 20px;
  row-gap: 20px;
  overflow-y: auto;
}
.infinite-loader {
  grid-column: 1 / -1;
  height: 60px;
  padding: 0 !important;
  color: red;
}
</style>
