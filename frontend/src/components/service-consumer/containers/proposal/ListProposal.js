import React, {useEffect, useState} from 'react';
import request, {PROPOSAL_PATH} from "../../../request/request";
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';
import {useActionSetter} from "../../../contexts/ProposalConsumerContext";
import Typography from '@material-ui/core/Typography';

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
                'Authorization': 'Bearer ' +token
            }
        };
        request(PROPOSAL_PATH, '/proposals/consumer/brief', options)
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
                                    <Card style={{width: 500, height: 230}}>
                                        <CardContent>
                                            <div style={{ maxWidth: 480, overflow: 'hidden', fontSize: '200%',
                                                height: '2.4em', maxHeight: '2.4em', lineHeight: '1.2em', textAlign: "justify"}}>
                                                {proposal.name}
                                            </div>
                                            <Grid container direction="row" justify="space-between" alignItems="baseline">
                                                <Typography color="textSecondary">
                                                    {proposal && proposal.user && (proposal.user.company + ' ' + proposal.user.city)}
                                                </Typography>
                                                <div>
                                                    <Typography variant="body2" component="p">
                                                        {proposal && proposal.user && (proposal.user.firstName + ' ' + proposal.user.lastName)}
                                                    </Typography>
                                                    <Typography color="primary" variant="body2" component="p">
                                                        {proposal && (proposal.price + '$')}
                                                    </Typography>
                                                </div>
                                            </Grid>
                                        </CardContent>
                                        <CardActions>
                                            <Button size="small" color="primary" onClick={() => openViewProposal(proposal.id)}>
                                                Learn More
                                            </Button>
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