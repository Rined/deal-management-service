import React from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ListSubheader from '@material-ui/core/ListSubheader';
import DashboardIcon from '@material-ui/icons/Dashboard';
import NoteAddIcon from '@material-ui/icons/NoteAdd';
import AssignmentIcon from '@material-ui/icons/Assignment';
import BackupIcon from '@material-ui/icons/Backup';
import EmailIcon from '@material-ui/icons/Email';
import {Link} from "react-router-dom";

export const mainListItems = (
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
);

export const secondaryListItems = (
    <div>
        <ListSubheader inset>Saved reports</ListSubheader>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon/>
            </ListItemIcon>
            <ListItemText primary="Current month"/>
        </ListItem>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon/>
            </ListItemIcon>
            <ListItemText primary="Last quarter"/>
        </ListItem>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon/>
            </ListItemIcon>
            <ListItemText primary="Year-end sale"/>
        </ListItem>
    </div>
);
