import React, {useEffect, useState} from 'react';
import request from "../../../request/request";
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import {useActionSetter} from "../../../contexts/ProposalConsumerContext";

const CURRENT_ACTION = 'list';
export default function ListProposal(props) {
    const token = props.auth.jwt;
    const [proposals, setProposals] = useState();
    const setAction = useActionSetter();

    const openViewProposal = (id) => {
        setAction({
            action: 'view',
            id: id,
            previousAction: CURRENT_ACTION
        });
    };

    useEffect(() => {
        const options = {
            headers: {
                'Authorization': token
            }
        };
        request('/proposals/api/proposals/consumer/brief', options)
            .then(response => setProposals(response.json));
    }, []);

    return (
        <React.Fragment>
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                {
                    proposals && proposals.length !== 0 &&
                    <Grid container spacing={2}>
                        {
                            proposals.map((proposal, i) => (
                                <Grid key={i} item>
                                    <Card style={{width: 300, height: 170}}>
                                        <CardContent>
                                            <div style={{
                                                maxWidth: 280,
                                                overflow: 'hidden',
                                                fontSize: '200%',
                                                height: '2.4em',
                                                maxHeight: '2.4em',
                                                lineHeight: '1.2em',
                                                textAlign: "justify"}}>
                                                {proposal.name}
                                            </div>
                                        </CardContent>
                                        <CardActions>
                                            <Button size="small" onClick={() => openViewProposal(proposal.id)}>Learn More</Button>
                                        </CardActions>
                                    </Card>
                                </Grid>
                            ))
                        }
                    </Grid>
                }
            </div>
        </React.Fragment>
    );
}