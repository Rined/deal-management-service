import React from 'react';
import {useTitleSetter} from "../../../contexts/TitleContext";
import EditTemplate from "./EditTemplate"
import {useAction} from "./../../../contexts/ActionContext";
import ListTemplates from "./ListTemplates";
import ViewTemplate from "./ViewTemplate";

const TITLE = 'Template';

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
            return <EditTemplate />;
        case 'view':
            return <ViewTemplate param={activeAction}/>;
        default:
            throw new Error('Unknown action');
    }
}