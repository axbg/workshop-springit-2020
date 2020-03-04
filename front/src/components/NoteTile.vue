<template>
  <div class="note-container" @click="navigateToNote">
    <md-card md-with-hover>
      <md-ripple>
        <md-card-header>
            <div class="md-subhead">Last modified: {{lastModified}}</div>
        </md-card-header>
        <md-card-content>
          <p>{{content}}</p>
        </md-card-content>
        <md-card-actions>
          <md-button class="note-button" @click="remove">Delete</md-button>
        </md-card-actions>
      </md-ripple>
    </md-card>
  </div>
</template>

<script>
export default {
  name: 'NoteTile',
  props: {
    id: Number,
    content: String,
    updated: Array,
  },
  methods: {
    navigateToNote() {
      this.$router.push(`/note/${this.id}`);
    },
    remove(event) {
      event.stopPropagation();
      this.$emit('deleted', this.id);
    },
  },
  computed: {
    lastModified() {
      return `${this.updated[2]}-${this.updated[1]}-${this.updated[0]} ${
        this.updated[3]}:${this.updated[4]}`;
    },
  },
};
</script>

<style scoped>
@import "../assets/variables.css";

.note-container {
    background-color: white;
    user-select: none;
}
.md-subhead {
  color: var(--accent-color);
  font-weight: 800;
}
.note-button {
  background-color: var(--accent-color);
  color: white;
}
</style>
