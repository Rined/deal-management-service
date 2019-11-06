import React from 'react';
import {useTitleSetter} from "../../../contexts/TitleContext";
import {useAction} from "./../../../contexts/ProposalContext";
import EditProposal from "./EditProposal"
import ListProposal from "./ListProposal";
import ViewProposal from "./ViewProposal";
import AddProposal from "./AddProposal";

const TITLE = 'Proposals';
export default function Proposal() {
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
            return <ListProposal />;
        case 'edit':
            return <EditProposal param={activeAction}/>;
        case 'view':
            return <ViewProposal param={activeAction}/>;
        case 'add':
            return <AddProposal />;
        default:
            throw new Error('Unknown action');
    }
}