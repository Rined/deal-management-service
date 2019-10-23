import React from 'react';
import clsx from "clsx";
import IconButton from '@material-ui/core/IconButton';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import {useStyles} from "./AppStyle"
import {useIsOpen, useOpen} from "./contexts/OpenMenuContext";
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import DashboardIcon from '@material-ui/icons/Dashboard';
import NoteAddIcon from '@material-ui/icons/NoteAdd';
import AssignmentIcon from '@material-ui/icons/Assignment';
import BackupIcon from '@material-ui/icons/Backup';
import EmailIcon from '@material-ui/icons/Email';
import {Link} from "react-router-dom";

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

                    <ListItem component={Link} to="/document" button>
                        <ListItemIcon>
                            <AssignmentIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Documents"/>
                    </ListItem>

                    <ListItem component={Link} to="/create" button>
                        <ListItemIcon>
                            <NoteAddIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Create document"/>
                    </ListItem>

                    <ListItem component={Link} to="/upload" button>
                        <ListItemIcon>
                            <BackupIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Upload document"/>
                    </ListItem>

                    <ListItem component={Link} to="/send" button>
                        <ListItemIcon>
                            <EmailIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Send documents"/>
                    </ListItem>
                </div>
            </List>
        </Drawer>
    );
}
