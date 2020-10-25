import React from 'react';
import {BrowserRouter, Route, Switch} from "react-router-dom";
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
import SignUp from "./SignUp";


export default function App() {
    const classes = useStyles();
    const auth = useAuth();

    return (
        <div className={classes.root}>
            <TitleProvider>
                <BrowserRouter>
                    {
                        auth ?
                            <React.Fragment>
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
                            </React.Fragment>
                            : <React.Fragment>
                                <Switch>
                                    <Route exact path="/signup">
                                        <SignUp/>
                                    </Route>
                                    <Route path="*">
                                        <SignIn/>
                                    </Route>
                                </Switch>
                            </React.Fragment>
                    }
                </BrowserRouter>
            </TitleProvider>
        </div>
    )
}