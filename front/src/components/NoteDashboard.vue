<template>
  <div>
  <div class="container">
    <NoteTile v-for="(note, index) in notes" :key="index" v-bind:id="note.id"
    :content="note.content" :updated="note.updated" @deleted="deleteNote"/>
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
    notes: [],
  }),
  async mounted() {
    const response = await this.$fetchHandler(`${this.$baseUrl}/note`, 'GET');
    this.notes = await response.json();
  },
  methods: {
    async createNote() {
      const result = await this.$fetchHandler(`${this.$baseUrl}/note`, 'POST', { content: '' });

      const note = await result.json();
      this.notes.push(note);
    },
    async deleteNote(id) {
      const result = await this.$fetchHandler(`${this.$baseUrl}/note/${id}`, 'DELETE');

      if (result.status === 200) {
        this.notes = this.notes.filter((note) => note.id !== id);
        this.$toasted.show('Removed');
      }
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
