import React from 'react';
import {Route, Switch} from "react-router-dom";
import ProviderDashboard from './containers/ProviderDashboard'
import NoMatch from "./../NoMatch";
import {useStyles} from "./../AppStyle"
import Notification from "./../Notification";
import Template from "./containers/template/Template"
import Deal from "./containers/deal/Deal"
import {TemplateProvider} from "../contexts/TemplateContext";
import {DealProvider} from "../contexts/DealContext";
import {ProposalProvider} from "../contexts/ProposalContext";
import Proposal from "./containers/proposal/Proposal";

export default function App() {
    const classes = useStyles();

    return (
        <main className={classes.content}>
            <div className={classes.appBarSpacer}/>
            <Switch>
                <Route exact path="/">
                    <ProviderDashboard/>
                </Route>

                <Route exact path="/dashboard">
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

                <Route exact path="/notification">
                    <Notification/>
                </Route>

                <Route path="*">
                    <NoMatch/>
                </Route>
            </Switch>
        </main>
    )
}