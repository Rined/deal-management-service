import React, {useEffect} from 'react';
import MdEditor from "react-markdown-editor-lite";
import {htmlRender} from "../utils/Utils";

export function MdElement(props) {
    let mdEditor = null;

    const updateMdText = () => {
        if (mdEditor) {
            mdEditor._setMdText(mdEditor.getMdValue());
        }
    };

    useEffect(() => {
        updateMdText();
    }, [JSON.stringify(props.fields)]);

    const updateStateOnMdEdit = ({text}) => {
        props.stateCallback(text);
    };

    const customHtmlRender = (text) => {
        return htmlRender(text, props.fields);
    };

    const setMdEditor = (node) => {
        if (node)
            mdEditor = node;
    };

    return (
        <MdEditor
            ref={node => setMdEditor(node)}
            value={props.format}
            renderHTML={(text) => customHtmlRender(text)}
            onChange={updateStateOnMdEdit}/>
    );
}