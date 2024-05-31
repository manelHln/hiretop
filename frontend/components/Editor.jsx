"use client";

import React, { useState, useEffect } from "react";
import { EditorState } from "draft-js";
import { Editor } from "react-draft-wysiwyg";
import { convertToHTML } from "draft-convert";
import DOMPurify from "dompurify";

import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";

function EditorComponent({header}) {
  const [editorState, setEditorState] = useState(() => EditorState.createEmpty());

  return (
    <div className="App">
      <header className="App-header">{header}</header>
      <Editor
        editorState={editorState}
        onEditorStateChange={setEditorState}
        wrapperClassName="wrapper-class"
        editorClassName="editor-class"
        toolbar={{
          options: [
            "inline",
            "blockType",
            "fontSize",
            "list",
            "textAlign",
            "link",
            "emoji",
            "image",
            "remove",
            "history",
          ],
        }}
      />
    </div>
  );
}

export default EditorComponent;
