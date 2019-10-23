import React from 'react';
import clsx from "clsx";
import IconButton from '@material-ui/core/IconButton';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import {useStyles} from "./AppStyle"
import {useIsOpen, useOpen} from "./contexts/OpenMenuContext";
import {mainListItems, secondaryListItems} from "./listItems";
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';

export default function AppLeftSideMenu() {
    const classes = useStyles();

    const open = useIsOpen();

    const changeOpen = useOpen();
    const closeMenu = () => {
        changeOpen(false);
    };

    return (
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
    );
}
