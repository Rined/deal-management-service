import React from 'react';
import {Route, Switch} from "react-router-dom";
import SignIn from './../SignIn'
import ProviderDashboard from './containers/ProviderDashboard'
import Document from './../Document'
import NoMatch from "./../NoMatch";
import {useStyles} from "./../AppStyle"
import CreateDocuments from "./../CreateDocuments";
import UploadDocuments from "./../UploadDocuments";
import SendDocuments from "./../SendDocuments";
import Notification from "./../Notification";
import Template from "./containers/template/Template"
import Formatter from "./containers/formatter/Formatter"
import {FormatterProvider} from "./../contexts/FormatterContext";
import {ActionProvider} from "../contexts/ActionContext";

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
                    <ActionProvider>
                        <Template/>
                    </ActionProvider>
                </Route>

                <Route exact path="/formatter">
                    <FormatterProvider>
                        <Formatter/>
                    </FormatterProvider>
                </Route>

                <Route exact path="/document">
                    <Document/>
                </Route>

                <Route exact path="/create">
                    <CreateDocuments/>
                </Route>

                <Route exact path="/upload">
                    <UploadDocuments/>
                </Route>

                <Route exact path="/send">
                    <SendDocuments/>
                </Route>

                <Route exact path="/notification">
                    <Notification/>
                </Route>

                <Route path="*">
                    <NoMatch/>
                </Route>

                <Route exact path="/auth">
                    <SignIn/>
                </Route>

            </Switch>
        </main>
    )
}