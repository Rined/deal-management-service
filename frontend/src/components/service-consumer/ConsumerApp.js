import React from 'react';
import {Route, Switch} from "react-router-dom";
import SignIn from './../SignIn'
import ConsumerDashboard from './containers/ConsumerDashboard'
import NoMatch from "./../NoMatch";
import {useStyles} from "./../AppStyle"
import Notification from "./../Notification";

export default function ConsumerApp() {
    const classes = useStyles();

    return (
        <main className={classes.content}>
            <div className={classes.appBarSpacer}/>
            <Switch>
                <Route exact path="/">
                    <ConsumerDashboard/>
                </Route>
                <Route exact path="/dashboard">
                    <ConsumerDashboard/>
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