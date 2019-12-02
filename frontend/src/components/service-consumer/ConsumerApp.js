import React from 'react';
import {Route} from "react-router-dom";
import ConsumerDashboard from './containers/ConsumerDashboard'
import {ProposalConsumerProvider} from "../contexts/ProposalConsumerContext";
import Proposal from "./containers/proposal/Proposal";
import {DealConsumerProvider} from "../contexts/DealConsumerContext";
import Deal from "./containers/deal/Deal";

export default function ConsumerApp() {
    return (
        <React.Fragment>
            <Route exact path="/">
                <ConsumerDashboard/>
            </Route>
            <Route exact path="/proposals">
                <ProposalConsumerProvider>
                    <Proposal />
                </ProposalConsumerProvider>
            </Route>
            <Route exact path="/deals">
                <DealConsumerProvider>
                    <Deal />
                </DealConsumerProvider>
            </Route>
        </React.Fragment>
    )
}