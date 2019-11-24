import React, {useEffect, useState} from 'react';
import request from "../../../request/request";
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';

const CURRENT_ACTION = 'list';
export default function ListProposal(props) {
    const token = props.auth.jwt;
    const [proposals, setProposals] = useState();

    useEffect(() => {
        const options = {
            headers: {
                'Authorization': token
            }
        };
        request('/proposals/consumer/brief', options)
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
                                    <Card style={{width: 300, height: 200}}>
                                        <CardContent>
                                            <div style={{
                                                maxWidth: 280,
                                                overflow: 'hidden',
                                                fontSize: '250%',
                                                height: '2.4em',
                                                maxHeight: '2.4em',
                                                lineHeight: '1.2em',
                                                textAlign: "justify"}}>
                                                {proposal.name}
                                            </div>
                                        </CardContent>
                                        <CardActions>
                                            <Button size="small">Learn More</Button>
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