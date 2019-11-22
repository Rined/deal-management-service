import React from 'react';
import {BrowserRouter} from "react-router-dom";
import AppTitleHeader from "./AppTitleHeader";
import {useStyles} from "./AppStyle"
import {TitleProvider} from "./contexts/TitleContext";
import {OpenMenuProvider} from "./contexts/OpenMenuContext";
import SignIn from "./SignIn";
import {useAuth} from "./contexts/AuthContext";
import Menu from "./Menu";
import AppSwitcher from "./AppSwitcher";
import ProviderApp from "./service-provider/ProviderApp"
import ConsumerApp from "./service-consumer/ConsumerApp"
import ProviderMenu from "./service-provider/ProviderMenu"
import ConsumerMenu from "./service-consumer/ConumerMenu"

export default function App() {
    const classes = useStyles();
    const auth = useAuth();

    return (
        <div className={classes.root}>
            {
                auth ?
                    <TitleProvider>
                        <BrowserRouter>
                            <OpenMenuProvider>
                                <AppTitleHeader/>
                                <Menu>
                                    {auth.isConsumer() ? <ConsumerMenu/> : null}
                                    {auth.isProvider() ? <ProviderMenu/> : null}
                                </Menu>
                            </OpenMenuProvider>
                            <AppSwitcher>
                                {auth.isConsumer() ? <ConsumerApp/> : null}
                                {auth.isProvider() ? <ProviderApp/> : null}
                            </AppSwitcher>
                        </BrowserRouter>
                    </TitleProvider>
                    : <SignIn/>
            }
        </div>
    )
}