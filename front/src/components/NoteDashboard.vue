<template>
  <div>
  <div class="container">
    <NoteTile v-for="(note, index) in notes" :key="index" v-bind:id="note.id"
    :content="note.content"/>
    <p class="list-end">👌</p>
  </div>
  <md-button class="md-fab md-mini fab" @click="createNote">
        <md-icon>add</md-icon>
  </md-button>
  </div>
</template>

<script>
import NoteTile from './NoteTile.vue';

export default {
  name: 'NoteDashboard',
  data: () => ({
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
    async createNote() {
      alert('creating note');
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
.list-end {
  display: none;
  font-size: 30px;
  grid-column: 1 / -1;
}
.fab {
  position: fixed;
  bottom: 10px;
  right: 10px;
  background-color: var(--accent-color);
  color: whitesmoke;
}
@media only screen and (max-width: 650px) {
  .list-end {
    display: block;
  }
}
</style>
