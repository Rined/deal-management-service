import React from 'react';
import {useTitleSetter} from "../../../contexts/TitleContext";
import EditTemplate from "./EditTemplate"
import {useAction} from "./../../../contexts/TemplateContext";
import ListTemplates from "./ListTemplates";
import ViewTemplate from "./ViewTemplate";
import {useAuth} from "../../../contexts/AuthContext";
import AddTemplate from "./AddTemplate";

//https://www.npmjs.com/package/react-markdown-editor-lite
const TITLE = 'Templates';
export default function Template() {
    const titleSetter = useTitleSetter();
    const authentication = useAuth();
    titleSetter(TITLE);

    const activeAction = useAction();

    return (
        <React.Fragment>
            {getActionContent(activeAction, authentication)}
        </React.Fragment>
    );
}

function getActionContent(activeAction, auth) {
    switch (activeAction.action) {
        case 'list':
            return <ListTemplates auth={auth}/>;
        case 'edit':
            return <EditTemplate auth={auth} param={activeAction}/>;
        case 'view':
            return <ViewTemplate auth={auth} param={activeAction}/>;
        case 'add':
            return <AddTemplate auth={auth}/>;
        default:
            throw new Error('Unknown action');
    }
}