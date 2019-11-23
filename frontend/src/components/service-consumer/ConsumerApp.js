import React from 'react';
import {Route} from "react-router-dom";
import ConsumerDashboard from './containers/ConsumerDashboard'

export default function ConsumerApp() {
    return (
        <React.Fragment>
            <Route exact path="/">
                <ConsumerDashboard/>
            </Route>
        </React.Fragment>
    )
}