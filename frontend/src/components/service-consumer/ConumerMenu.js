import React from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import DashboardIcon from '@material-ui/icons/Dashboard';
import {Link} from "react-router-dom";
import ForumIcon from '@material-ui/icons/Forum';
import AssignmentIcon from '@material-ui/icons/Assignment';

export default function ConsumerMenu() {

    return (
        <React.Fragment>
            <ListItem component={Link} to="/" button>
                <ListItemIcon>
                    <DashboardIcon/>
                </ListItemIcon>
                <ListItemText primary="Dashboard"/>
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
        </React.Fragment>
    );
}
