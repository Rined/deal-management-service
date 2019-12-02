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
import MeetingRoomIcon from '@material-ui/icons/MeetingRoom';
import {useAuth, useAuthSetter} from "./contexts/AuthContext";
import {logout} from "./auth/AuthenticationManager";


export default function AppTitleHeader() {
    const classes = useStyles();
    const title = useTitle();
    const open = useIsOpen();
    const authentication = useAuth();
    const authenticationSetter = useAuthSetter();

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

                <Typography component="h6" variant="h6" color="inherit" noWrap>
                    {authentication.name}
                </Typography>
                <IconButton component={Link} to="/notification" color="inherit">
                    <Badge badgeContent={5} color="secondary">
                        <NotificationsIcon/>
                    </Badge>
                </IconButton>
                <IconButton onClick={() => {logout(); authenticationSetter(null);}} color="inherit">
                    <MeetingRoomIcon/>
                </IconButton>
            </Toolbar>
        </AppBar>
    );
}
