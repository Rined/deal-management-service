import React from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import DashboardIcon from '@material-ui/icons/Dashboard';
import NoteAddIcon from '@material-ui/icons/NoteAdd';
import AssignmentIcon from '@material-ui/icons/Assignment';
import BackupIcon from '@material-ui/icons/Backup';
import EmailIcon from '@material-ui/icons/Email';
import {Link} from "react-router-dom";

export default function ConsumerMenu() {

    return (
        <React.Fragment>
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
        </React.Fragment>
    );
}
