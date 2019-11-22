import React from 'react';
import {Route} from "react-router-dom";
import {useStyles} from "./AppStyle";
import Notification from "./Notification";
import ConsumerDashboard from "./service-consumer/containers/ConsumerDashboard";
import ProviderDashboard from "./service-provider/containers/ProviderDashboard";

export default function AppSwitcher({children}) {
    const classes = useStyles();

    return (
        <main className={classes.content}>
            <div className={classes.appBarSpacer}/>
            <Route exact path="/">
                <ConsumerDashboard/>
                <ProviderDashboard/>
            </Route>
            {children}
            <Route exact path="/notification">
                <Notification/>
            </Route>
        </main>
    )
}