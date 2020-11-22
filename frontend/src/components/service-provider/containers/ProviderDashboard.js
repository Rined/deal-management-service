import React, {useEffect} from 'react';
import request, {PAYMENT_PATH} from "../../request/request";
import {useAuth} from "../../contexts/AuthContext";
import Card from '@material-ui/core/Card';
import CardContent from "@material-ui/core/CardContent";
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import TextField from "@material-ui/core/TextField";
import Button from '@material-ui/core/Button';

export default function ProviderDashboard() {
    const authentication = useAuth();

    const [balance, setBalance] = React.useState({
        balance: 0
    });

    const [value, setValue] = React.useState({
        amount: 0
    });

    useEffect(() => {
        balanceRequest();
    }, []);

    const balanceRequest = () => {
        const options = {
            headers: {
                'Authorization': 'Bearer ' + authentication.jwt
            }
        };
        request(PAYMENT_PATH, '/balance', options)
            .then(response => setBalance(response.json));
    };


    const handleChange = (event) => {
        setValue({amount: event.target.value});
    };

    const recharge = () => {
        const options = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + authentication.jwt
            },
            body: JSON.stringify(value)
        };
        request(PAYMENT_PATH, '/income', options)
            .then(response => setBalance(response.json));
    };

    return (
        <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
            <Grid container direction="column" justify="flex-start" alignItems="flex-start">
                <div>
                    <TextField id="standard-number" style={{width: 250}}
                               label="Amount" type="number"
                               onChange={handleChange}/>
                    <Button variant="contained" color="primary" onClick={recharge}>
                        Recharge
                    </Button>
                </div>
                <Card style={{width: 250, height: 200}}>
                    <CardContent>
                        <Typography component="h5" variant="h5">
                            Balance
                        </Typography>
                        <Typography component="h4" variant="h4">
                            {balance.balance}$
                        </Typography>
                    </CardContent>
                </Card>
            </Grid>
        </div>
    );
}