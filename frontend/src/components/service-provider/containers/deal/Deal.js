import React from 'react';
import {useTitleSetter} from "../../../contexts/TitleContext";
import {useAction} from "./../../../contexts/DealContext";
import EditDeal from "./EditDeal"
import ListDeal from "./ListDeal";
import ViewDeal from "./ViewDeal";
import AddDeal from "./AddDeal";

const TITLE = 'Deals';
export default function Deal() {
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
            return <ListDeal />;
        case 'edit':
            return <EditDeal param={activeAction}/>;
        case 'view':
            return <ViewDeal param={activeAction}/>;
        case 'add':
            return <AddDeal />;
        default:
            throw new Error('Unknown action');
    }
}