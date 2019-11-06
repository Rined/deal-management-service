import React, {useEffect, useState} from 'react';
import {List, ListItem, ListItemText} from '@material-ui/core';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import {useActionSetter} from "./../../../contexts/ProposalContext";
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import Fab from '@material-ui/core/Fab';
import Paper from '@material-ui/core/Paper';
import AddIcon from '@material-ui/icons/Add';


const CURRENT_ACTION = 'list';
export default function ListProposal() {
    const setAction = useActionSetter();
    const [proposals, setProposals] = useState();

    const handleEdit = (id) => {
        setAction({
            action: 'edit',
            id: id,
            previousAction: CURRENT_ACTION
        });
    };

    const handleView = (id) => {
        setAction({
            action: 'view',
            id: id,
            previousAction: CURRENT_ACTION
        });
    };

    const handleAdd = () => {
        setAction({
            action: 'add',
            previousAction: CURRENT_ACTION
        });
    };

    // useEffect(() => {
    //     fetch('http://localhost:8080/templates/brief')
    //         .then(response => response.json())
    //         .then(proposals => setProposals(proposals));
    // }, []);

    return (
        <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
            <Grid container direction="row" justify="space-between" alignItems="baseline">
                <div>
                    <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>Proposal
                        list </Typography>
                </div>
                <Fab style={{backgroundColor: 'rgb(67, 160, 71)'}} onClick={() => handleAdd()} aria-label="add">
                    <AddIcon style={{color: 'white'}}/>
                </Fab>
            </Grid>
            {proposals &&
            <Paper style={{marginTop: 10}}>
                <List component="nav">
                    {proposals && proposals.map((proposal, i) => (
                        <ListItem key={i} onClick={() => handleView(proposal.id)} button>
                            <ListItemText primary={proposal.name}/>
                            <ListItemSecondaryAction>
                                <IconButton edge="end" onClick={() => handleEdit(proposal.id)} aria-label="edit">
                                    <EditIcon color="primary"/>
                                </IconButton>
                                <IconButton edge="end" aria-label="delete">
                                    <DeleteIcon/>
                                </IconButton>
                            </ListItemSecondaryAction>
                        </ListItem>
                    ))}
                </List>
            </Paper>
            }
        </div>
    );
}