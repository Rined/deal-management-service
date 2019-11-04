import React, {useEffect, useState} from 'react';
import {List} from '@material-ui/core';
import {ListItem} from '@material-ui/core';
import {ListItemText} from '@material-ui/core';
import {useTitleSetter} from "./../../contexts/TitleContext";
import {useStyles} from "./../../AppStyle"
import ControlPointIcon from '@material-ui/icons/ControlPoint';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';


const TITLE = 'Formatter';

export default function Formatter() {
    const classes = useStyles();
    const [formatters, setFormatter] = useState();

    const titleSetter = useTitleSetter();
    titleSetter(TITLE);

    useEffect(() => {
        fetch('http://localhost:8080/formatters/brief')
            .then(response => response.json())
            .then(formatters => setFormatter(formatters));
    }, []);

    return (
        <Container className={classes.root} aria-label="contacts">
            {/*<Fab color="primary" aria-label="add">*/}
                {/*<AddIcon />*/}
            {/*</Fab>*/}
            <Grid container spacing={3}>
                <Grid item xs={12} md={8} lg={9}>
                    <Paper style={{height: 240}}>
                        <List component="nav">
                            <ListItem button>
                                <ControlPointIcon color="secondary"/>
                                {formatters && formatters.map((document, i) => (
                                    <ListItemText key={i} primary={document.name}/>
                                ))}
                            </ListItem>
                        </List>
                    </Paper>
                </Grid>
                <Grid item xs={4} lg={3}>
                    <Paper style={{height: 240}}>
                        2
                    </Paper>
                </Grid>
                <Grid item xs={12}>
                    <Paper style={{height: 240}}>
                        3
                    </Paper>
                </Grid>
            </Grid>
        </Container>
    );
}
