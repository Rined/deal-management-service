import React from 'react';
import {useTitleSetter} from "../../../contexts/TitleContext";
import {useAction} from "./../../../contexts/DealConsumerContext";
import {useAuth} from "../../../contexts/AuthContext";
import ListDeal from "./ListDeal";
import ViewDeal from "./ViewDeal";

const TITLE = 'Deals';
export default function Deal() {
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
            return <ListDeal auth={auth}/>;
        case 'view':
            return <ViewDeal auth={auth} param={activeAction}/>;
        default:
            throw new Error('Unknown action');
    }
}