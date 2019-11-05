import React from 'react';
import {useTitleSetter} from "../../../contexts/TitleContext";
import ListFormatters from "./ListFormatters"
import EditFormatter from "./EditFormatter"
import ViewFormatter from "./ViewFormatter"
import {useAction} from "./../../../contexts/FormatterContext";

const TITLE = 'Formatter';

export default function Formatter() {
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
            return <ListFormatters />;
        case 'edit':
            return <EditFormatter />;
        case 'view':
            return <ViewFormatter param={activeAction}/>;
        // case 'view':
        //     return <ViewFormatter />;
        default:
            throw new Error('Unknown action');
    }
}