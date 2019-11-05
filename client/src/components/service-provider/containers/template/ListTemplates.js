import React, {useEffect, useState} from 'react';
import {List, ListItem, ListItemText} from '@material-ui/core';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import {useActionSetter} from "./../../../contexts/ActionContext";

const CURRENT_ACTION = 'list';

export default function ListTemplates() {
    const setAction = useActionSetter();
    // setAction({action: 'list'});
    const [templates, setTemplates] = useState();

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


    useEffect(() => {
        fetch('http://localhost:8080/templates/brief')
            .then(response => response.json())
            .then(templates => setTemplates(templates));
    }, []);

    return (
        <List component="nav">
            {templates && templates.map((template, i) => (
                <ListItem key={i} onClick={() => handleView(template.id)} button>
                    <ListItemText primary={template.name}/>
                    <ListItemSecondaryAction>
                        <IconButton edge="end" onClick={() => handleEdit(template.id)} aria-label="edit">
                            <EditIcon color="primary"/>
                        </IconButton>
                        <IconButton edge="end" aria-label="delete">
                            <DeleteIcon/>
                        </IconButton>
                    </ListItemSecondaryAction>
                </ListItem>
            ))}
        </List>
    );
}