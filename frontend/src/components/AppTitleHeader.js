import React from 'react';
import clsx from "clsx";
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Badge from '@material-ui/core/Badge';
import MenuIcon from '@material-ui/icons/Menu';
import IconButton from '@material-ui/core/IconButton';
import NotificationsIcon from '@material-ui/icons/Notifications';
import {useTitle} from "./contexts/TitleContext";
import {useStyles} from "./AppStyle"
import {useIsOpen, useOpen} from "./contexts/OpenMenuContext"
import {Link} from "react-router-dom";


export default function AppTitleHeader() {
    const classes = useStyles();
    const title = useTitle();
    const open = useIsOpen();

    const changeOpen = useOpen();
    const openMenu = () => {
        changeOpen(true);
    };

    return (
        <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}>
            <Toolbar className={classes.toolbar}>
                <IconButton
                    edge="start"
                    color="inherit"
                    aria-label="open drawer"
                    onClick={openMenu}
                    className={clsx(classes.menuButton, open && classes.menuButtonHidden)}>
                    <MenuIcon/>
                </IconButton>
                <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
                    {title}
                </Typography>
                <IconButton component={Link} to="/notification" color="inherit">
                    <Badge badgeContent={5} color="secondary">
                        <NotificationsIcon/>
                    </Badge>
                </IconButton>
            </Toolbar>
        </AppBar>
    );
}
