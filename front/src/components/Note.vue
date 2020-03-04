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
      <editor-content class="editor__content" :editor="editor" />
    </div>
    <md-button class="md-fab md-mini fab" @click="removeNote">
      <md-icon>delete</md-icon>
    </md-button>
    <md-button class="md-fab md-mini fab fab-2" @click="removeNote">
      <md-icon>share</md-icon>
    </md-button>
  </div>
</template>

<script>
import { Editor, EditorContent, EditorMenuBar } from 'tiptap';
import {
  Blockquote,
  CodeBlock,
  HardBreak,
  Heading,
  HorizontalRule,
  OrderedList,
  BulletList,
  ListItem,
  TodoItem,
  TodoList,
  Bold,
  Code,
  Italic,
  Link,
  Strike,
  Underline,
  History,
} from 'tiptap-extensions';

export default {
  name: 'Note',
  data() {
    return {
      status: true,
      editor: new Editor({
        extensions: [
          new Blockquote(),
          new BulletList(),
          new CodeBlock(),
          new HardBreak(),
          new Heading({ levels: [1, 2, 3] }),
          new HorizontalRule(),
          new ListItem(),
          new OrderedList(),
          new TodoItem(),
          new TodoList(),
          new Link(),
          new Bold(),
          new Code(),
          new Italic(),
          new Strike(),
          new Underline(),
          new History(),
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
    async changeStatus() {
      alert('changing status');
    },
    async saveContent() {
      alert(this.editor.content);
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

<style scoped>
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
    overflow: auto;
    text-align: left;
    border: 1px solid black;
}
.fab {
  position: fixed;
  bottom: 10px;
  right: 10px;
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
</style>
