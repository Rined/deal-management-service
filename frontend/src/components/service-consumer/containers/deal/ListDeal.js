import React, {useEffect, useState} from 'react';
import request from "../../../request/request";
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import {List, ListItem, ListItemText} from '@material-ui/core';
import {useActionSetter} from "./../../../contexts/DealConsumerContext";

const CURRENT_ACTION = 'list';
export default function ListDeal(props) {
    const token = props.auth.jwt;
    const [deals, setDeals] = useState();
    const setAction = useActionSetter();

    useEffect(() => {
        const options = {
            headers: {
                'Authorization': token
            }
        };
        request('/deals/api/deals/consumer/brief', options)
            .then(response => {
                setDeals(response.json);
                console.log(response.json);
            });
    }, []);


    const openViewDeal = (id) => {
        setAction({
            action: 'view',
            id: id,
            previousAction: CURRENT_ACTION
        });
    };

    return (
        <React.Fragment>
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                <Grid container direction="row" justify="space-between" alignItems="baseline">
                    <div>
                        <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>Deal
                            list </Typography>
                    </div>
                </Grid>
                {deals && deals.length !== 0 &&
                <Paper style={{marginTop: 10}}>
                    <List component="nav">
                        {deals && deals.map((deal, i) => (
                            <ListItem key={i} onClick={() => openViewDeal(deal.id)} button>
                                <ListItemText primary={deal.title}/>
                                <ListItemSecondaryAction>
                                    {deal.state}
                                </ListItemSecondaryAction>
                            </ListItem>
                        ))}
                    </List>
                </Paper>
                }
            </div>
        </React.Fragment>
    );

}