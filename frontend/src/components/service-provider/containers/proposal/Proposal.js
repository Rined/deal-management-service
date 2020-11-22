import React from 'react';
import {useTitleSetter} from "../../../contexts/TitleContext";
import {useAction} from "../../../contexts/ProposalContext";
import EditProposal from "./EditProposal"
import ListProposal from "./ListProposal";
import ViewProposal from "./ViewProposal";
import AddProposal from "./AddProposal";
import {useAuth} from "../../../contexts/AuthContext";

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
        case 'edit':
            return <EditProposal auth={auth} param={activeAction}/>;
        case 'view':
            return <ViewProposal auth={auth} param={activeAction}/>;
        case 'add':
            return <AddProposal auth={auth}/>;
        default:
            throw new Error('Unknown action');
    }
}