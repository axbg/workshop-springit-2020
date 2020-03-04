<template>
  <div class="container">
    <p>Last modified today</p>
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
            <md-icon v-if="status">lock_open</md-icon>
            <md-icon v-else>lock</md-icon>
          </button>

          <button class="menubar__button" @click="saveContent">
            <md-icon>save</md-icon>
          </button>
        </div>
      </editor-menu-bar>
      <editor-content class="editor__content" :editor="editor" @click="selectChild"/>
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
      status: true,
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
        content: `
          <h2>
            Hi there,
          </h2>
        `,
        onUpdate({ getHTML }) {
          this.content = getHTML();
        },
      }),
      content: 'some content',
    };
  },
  methods: {
    showImagePrompt(command) {
      const src = prompt('Enter the url of your image here');
      if (src !== null) {
        command({ src });
      }
    },
    selectChild() {
      alert('select');
    },
    async changeStatus() {
      alert('changing status');
    },
    async saveContent() {
      alert(this.editor.content);
    },
    shareContent() {
      this.$toasted.show('Copied to clipboard');
    },
    async removeNote() {
      alert('removing note');
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
