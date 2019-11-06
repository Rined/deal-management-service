import React from 'react';
import {BrowserRouter as Router} from "react-router-dom";
import AppTitleHeader from "./AppTitleHeader";
import {useStyles} from "./AppStyle"
import {TitleProvider} from "./contexts/TitleContext";
import {OpenMenuProvider} from "./contexts/OpenMenuContext";
import ProviderMenu from "./service-provider/ProviderMenu"
import ConsumerMenu from "./service-consumer/ConumerMenu"
import ProviderApp from "./service-provider/ProviderApp"
import ConsumerApp from "./service-consumer/ConsumerApp"

function isProvider() {
    return true;
}

export default function App() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <TitleProvider>
                <Router>
                    <OpenMenuProvider>
                        <AppTitleHeader/>
                        {isProvider() ? <ProviderMenu/> : <ConsumerMenu/>}
                    </OpenMenuProvider>
                    {isProvider() ? <ProviderApp/> : <ConsumerApp/>}
                </Router>
            </TitleProvider>
        </div>
    )
}