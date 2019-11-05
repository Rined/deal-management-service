import React, {useEffect, useState} from 'react';
import {List, ListItem, ListItemText} from '@material-ui/core';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import {useActionSetter} from "./../../../contexts/FormatterContext";

const CURRENT_ACTION = 'list';

export default function ListFormatters() {
    const setAction = useActionSetter();
    // setAction({action: 'list'});
    const [formatters, setFormatter] = useState();

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
        fetch('http://localhost:8080/formatters/brief')
            .then(response => response.json())
            .then(formatters => setFormatter(formatters));
    }, []);

    return (
        <List component="nav">
            {formatters && formatters.map((formatter, i) => (
                <ListItem key={i} onClick={() => handleView(formatter.id)} button>
                    <ListItemText primary={formatter.name}/>
                    <ListItemSecondaryAction>
                        <IconButton edge="end" onClick={() => handleEdit(formatter.id)} aria-label="edit">
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