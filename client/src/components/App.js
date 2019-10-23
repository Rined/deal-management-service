import React from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import SignIn from './SignIn'
import Dashboard from './Dashboard'
import Document from './Document'
import NoMatch from "./NoMatch";
import AppTitleHeader from "./AppTitleHeader";
import AppLeftSideMenu from "./AppLeftSideMenu";
import {useStyles} from "./AppStyle"
import {TitleProvider} from "./contexts/TitleContext";
import {OpenMenuProvider} from "./contexts/OpenMenuContext";
import CreateDocuments from "./CreateDocuments";
import UploadDocuments from "./UploadDocuments";
import SendDocuments from "./SendDocuments";
import Notification from "./Notification";

export default function App() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <TitleProvider>
                <Router>
                    <OpenMenuProvider>
                        <AppTitleHeader/>
                        <AppLeftSideMenu/>
                    </OpenMenuProvider>
                    <main className={classes.content}>
                        <div className={classes.appBarSpacer}/>
                        <Switch>
                            <Route exact path="/">
                                <Dashboard/>
                            </Route>
                            <Route exact path="/dashboard">
                                <Dashboard/>
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
                </Router>
            </TitleProvider>
        </div>
    )
}