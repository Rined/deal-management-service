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
                            <Route exact path="/auth">
                                <SignIn/>
                            </Route>
                            <Route path="*">
                                <NoMatch/>
                            </Route>
                        </Switch>
                    </main>
                </Router>
            </TitleProvider>
        </div>
    )
}