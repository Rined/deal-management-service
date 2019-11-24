import React from 'react';
import {Route} from "react-router-dom";
import {useStyles} from "./AppStyle";
import Notification from "./Notification";

export default function AppSwitcher({children}) {
    const classes = useStyles();


    return (
        <main className={classes.content}>
            <div className={classes.appBarSpacer}/>
            {children}
            <Route exact path="/notification">
                <Notification/>
            </Route>
        </main>
    )
}