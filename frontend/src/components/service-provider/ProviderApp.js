import React from 'react';
import {Route} from "react-router-dom";
import Template from "./containers/template/Template"
import Deal from "./containers/deal/Deal"
import Proposal from "./containers/proposal/Proposal";
import {TemplateProvider} from "../contexts/TemplateContext";
import {DealProvider} from "../contexts/DealContext";
import {ProposalProvider} from "../contexts/ProposalContext";
import ProviderDashboard from "./containers/ProviderDashboard";

export default function ProviderApp() {
    return (
        <React.Fragment>
            <Route exact path="/">
                <ProviderDashboard/>
            </Route>
            <Route exact path="/templates">
                <TemplateProvider>
                    <Template/>
                </TemplateProvider>
            </Route>

            <Route exact path="/deals">
                <DealProvider>
                    <Deal/>
                </DealProvider>
            </Route>

            <Route exact path="/proposals">
                <ProposalProvider>
                    <Proposal/>
                </ProposalProvider>
            </Route>
        </React.Fragment>
    )
};