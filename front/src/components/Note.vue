<template>
  <div class="container">
    <p>Last modified: {{this.computeUpdated}}</p>
    <div class="editor">
      <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
        <div class="menubar">
          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.bold() }"
            @click="commands.bold"
          >
            <md-icon>format_bold</md-icon>
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.italic() }"
            @click="commands.italic"
          >
            <md-icon>format_italic</md-icon>
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.strike() }"
            @click="commands.strike"
          >
            <md-icon>format_strikethrough</md-icon>
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.underline() }"
            @click="commands.underline"
          >
            <md-icon>format_underline</md-icon>
          </button>

          <button class="menubar__button" @click="showImagePrompt(commands.image)">
            <md-icon>image</md-icon>
          </button>

          <button class="menubar__button" @click="commands.horizontal_rule">
            <md-icon>minimize</md-icon>
          </button>

          <button class="menubar__button" @click="changeStatus">
            <md-icon v-if="isPublic">lock_open</md-icon>
            <md-icon v-else>lock</md-icon>
          </button>

          <button class="menubar__button" @click="saveContent">
            <md-icon>save</md-icon>
          </button>
        </div>
      </editor-menu-bar>
      <editor-content class="editor__content" :editor="editor" />
    </div>
    <md-button class="md-fab md-mini fab" @click="removeNote">
      <md-icon>delete</md-icon>
    </md-button>
    <md-button class="md-fab md-mini fab fab-2" @click="shareContent">
      <md-icon>share</md-icon>
    </md-button>
  </div>
</template>

<script>
import { Editor, EditorContent, EditorMenuBar } from 'tiptap';
import {
  Heading,
  HorizontalRule,
  BulletList,
  ListItem,
  Bold,
  Italic,
  Strike,
  Underline,
  Image,
} from 'tiptap-extensions';

export default {
  name: 'Note',
  data() {
    return {
      id: 0,
      isPublic: false,
      content: 'Loading the content',
      updated: 'undefined',
      editor: new Editor({
        extensions: [
          new Heading({ levels: [1, 2, 3] }),
          new HorizontalRule(),
          new BulletList(),
          new ListItem(),
          new Bold(),
          new Image(),
          new Italic(),
          new Strike(),
          new Underline(),
        ],
        onUpdate({ getHTML }) {
          this.content = getHTML();
        },
      }),
    };
  },
  async mounted() {
    const result = await this.$fetchHandler(`${this.$baseUrl}/note/${this.$route.params.id}`, 'GET');

    if (result.status === 200) {
      const note = await result.json();
      this.updateNote(note);
    } else {
      this.$toasted.show('Error when loading note');
    }
  },
  methods: {
    showImagePrompt(command) {
      const src = prompt('Enter the url of your image here');
      if (src !== null) {
        command({ src });
      }
    },
    updateNote(note) {
      this.id = note.id;
      this.content = note.content;
      this.updated = note.updated;
      this.isPublic = note.isPublic;
      this.editor.setContent(note.content);
      this.editor.content = note.content;
    },
    async changeStatus() {
      const result = await this.$fetchHandler(`${this.$baseUrl}/note`, 'PUT', {
        id: this.id,
        isPublic: !this.isPublic,
      });

      if (result.status === 200) {
        this.isPublic = !this.isPublic;
        this.$toasted.show('Status updated');
      }
    },
    async saveContent() {
      const result = await this.$fetchHandler(`${this.$baseUrl}/note`, 'PUT', {
        id: this.id,
        content: this.editor.content,
        isPublic: null,
      });

      if (result.status === 200) {
        const note = await result.json();
        this.updateNote(note);
        this.$toasted.show('Saved');
      }
    },
    async shareContent() {
      this.isPublic = false;
      await this.changeStatus();
      window.open(`/#/public/${this.id}`);
    },
    async removeNote() {
      const result = await this.$fetchHandler(`${this.$baseUrl}/note/${this.id}`, 'DELETE');

      if (result.status === 200) {
        this.$router.push('/');
      }
    },
  },
  computed: {
    computeUpdated() {
      return `${this.updated[2]}-${this.updated[1]}-${this.updated[0]} ${
        this.updated[3]}:${this.updated[4] > 9 ? this.updated[4] : '0'.concat(this.updated[4])}`;
    },
  },
  beforeDestroy() {
    this.editor.destroy();
  },
  components: {
    EditorContent,
    EditorMenuBar,
  },
};
</script>

<style>
.container {
  height: 100%;
  overflow: hidden;
}
.editor {
  height: 80vh;
  overflow: hidden;
  margin: 20px;
}
.editor__content {
  margin-top: 15px;
  height: 70vh;
  max-height: 70vh;
  overflow: auto;
  text-align: left;
  border: 1px solid gray;
}
.ProseMirror {
    min-height: 65vh;
}
.ProseMirror:focus {
    outline: none;
  }
.fab {
  position: fixed;
  bottom: 10px;
  right: 5px;
  background-color: var(--accent-color);
  color: whitesmoke;
}
.fab-2 {
  bottom: 60px;
}
.is-active {
  color: white;
  background-color: var(--accent-color);
}
@media only screen and (max-width: 500px) {
  .editor__content {
    height: 60vh;
    max-height: 60vh;
  }
  .ProseMirror {
    min-height: 50vh;
    }
}
</style>
