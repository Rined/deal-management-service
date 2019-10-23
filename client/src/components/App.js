import React from 'react';
import clsx from 'clsx';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import {mainListItems, secondaryListItems} from './listItems';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import SignIn from './SignIn'
import Dashboard from './Dashboard'
import Document from './Document'
import NoMatch from "./NoMatch";
import AppTitleHeader from "./AppTitleHeader";
import {useStyles} from "./AppStyle"
import {useOpen, useIsOpen} from "./contexts/OpenMenuContext"

export default function App() {
    const classes = useStyles();
    const open = useIsOpen();

    const changeOpen = useOpen();

    const closeMenu = () => {
        changeOpen(false);
    };

    return (
        <div className={classes.root}>
            <Router>

                    <AppTitleHeader/>

                    <Drawer variant="permanent"
                            classes={{paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose)}}
                            open={open}>
                        <div className={classes.toolbarIcon}>
                            <IconButton onClick={closeMenu}>
                                <ChevronLeftIcon/>
                            </IconButton>
                        </div>
                        <List>{mainListItems}</List>
                        <Divider/>
                        <List>{secondaryListItems}</List>
                    </Drawer>
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
        </div>
    )
}