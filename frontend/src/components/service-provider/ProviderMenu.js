import React from 'react';
import clsx from "clsx";
import IconButton from '@material-ui/core/IconButton';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import {useStyles} from "./../AppStyle"
import {useIsOpen, useOpen} from "./../contexts/OpenMenuContext";
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import DashboardIcon from '@material-ui/icons/Dashboard';
import AssignmentIcon from '@material-ui/icons/Assignment';
import {Link} from "react-router-dom";
import ForumIcon from '@material-ui/icons/Forum';
import FilterFramesIcon from '@material-ui/icons/FilterFrames';

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
            <List>
                <div>
                    <ListItem component={Link} to="/" button>
                        <ListItemIcon>
                            <DashboardIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Dashboard"/>
                    </ListItem>

                    <ListItem component={Link} to="/templates" button>
                        <ListItemIcon>
                            <FilterFramesIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Templates"/>
                    </ListItem>

                    <ListItem component={Link} to="/proposals" button>
                        <ListItemIcon>
                            <ForumIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Proposals"/>
                    </ListItem>

                    <ListItem component={Link} to="/deals" button>
                        <ListItemIcon>
                            <AssignmentIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Deals"/>
                    </ListItem>
                </div>
            </List>
        </Drawer>
    );
}
