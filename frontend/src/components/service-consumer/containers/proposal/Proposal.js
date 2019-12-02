import React from 'react';
import {useTitleSetter} from "../../../contexts/TitleContext";
import {useAction} from "./../../../contexts/ProposalConsumerContext";
import {useAuth} from "../../../contexts/AuthContext";
import ListProposal from "./ListProposal";
import ViewProposal from "./ViewProposal";

const TITLE = 'Proposals';
export default function Proposal() {
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
            return <ListProposal auth={auth}/>;
        case 'view':
            return <ViewProposal auth={auth} param={activeAction}/>;
        default:
            throw new Error('Unknown action');
    }
}