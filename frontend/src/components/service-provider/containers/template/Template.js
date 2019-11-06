import React from 'react';
import {useTitleSetter} from "../../../contexts/TitleContext";
import EditTemplate from "./EditTemplate"
import {useAction} from "./../../../contexts/TemplateContext";
import ListTemplates from "./ListTemplates";
import ViewTemplate from "./ViewTemplate";
import AddTemplate from "./AddTemplate";

//https://www.npmjs.com/package/react-markdown-editor-lite
const TITLE = 'Templates';
export default function Template() {
    const titleSetter = useTitleSetter();
    titleSetter(TITLE);

    const activeAction = useAction();

    return (
        <React.Fragment>
            {getActionContent(activeAction)}
        </React.Fragment>
    );
}

function getActionContent(activeAction) {
    switch (activeAction.action) {
        case 'list':
            return <ListTemplates />;
        case 'edit':
            return <EditTemplate param={activeAction}/>;
        case 'view':
            return <ViewTemplate param={activeAction}/>;
        case 'add':
            return <AddTemplate />;
        default:
            throw new Error('Unknown action');
    }
}