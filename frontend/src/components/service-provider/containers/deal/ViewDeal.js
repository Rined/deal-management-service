import React, {useEffect, useState} from 'react';
import {useActionSetter} from "./../../../contexts/DealProviderContext";
import request from "./../../../request/request"
import ReactHtmlParser from 'react-html-parser';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import CheckCircleIcon from '@material-ui/icons/CheckCircle';
import ErrorIcon from '@material-ui/icons/Error';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';


const CURRENT_ACTION = 'view';
export default function ViewDeal(props) {
    const [activeStep, setActiveStep] = React.useState(0);
    const steps = [
        'Wait provider',
        'Provider accept',
        'Accepter',
        'Wait info',
        'Agreed',
        'In progress',
        'Done'
    ];
    const token = props.auth.jwt;
    const setAction = useActionSetter();
    const role = 'provider';
    const [deal, setDeal] = useState();

    const [positive, setPositive] = React.useState(false);
    const [open, setOpen] = React.useState(false);

    const getStepNumber = (state) => {
        switch (state) {
            case 'WAIT_PROVIDER':
                return 0;
            case 'PROVIDER_ACCEPT':
                return 1;
            case 'ACCEPTED':
                return 2;
            case 'WAIT_INFO':
                return 3;
            case 'AGREED':
                return 4;
            case 'IN_PROGRESS':
                return 5;
            case 'DONE':
                return 7;
        }
    };

    useEffect(() => {
        const dealId = props.param.id;
        const options = {
            headers: {
                'Authorization': token
            }
        };
        request(`/deals/api/deals/${role}/${dealId}`, options)
            .then(response => {
                let data = response.json;
                console.log(data);
                setDeal(response.json);
                setActiveStep(getStepNumber(response.json.state));
            });
    }, []);

    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    const handleDecline = () => {
        const options = {
            method: 'post',
            headers: {
                'Authorization': token
            },
        };
        request(`/deals/api/deals/${role}/${props.param.id}/decline`, options)
            .then((response) => {
                console.log(response);
                setPositive(true);
                setOpen(true);
                setDeal(response.json);
            })
            .catch((error) => {
                setPositive(false);
                setOpen(true);
                console.log(error);
            });
    };

    const handleClose = () => {
        setOpen(false);
    };

    const snackNotification = () => {
        return (
            <Snackbar
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'left',
                }}
                open={open}
                onClose={handleClose}
                autoHideDuration={3000}>
                {
                    positive
                        ? <SnackbarContent
                            style={{backgroundColor: 'rgb(67, 160, 71)'}}
                            aria-describedby="client-snackbar"
                            message={
                                <span id="client-snackbar" style={{
                                    backgroundColor: 'rgb(67, 160, 71)',
                                    display: 'flex',
                                    alignItems: 'center'
                                }}>
                            <CheckCircleIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>
                            Operation completed successfully! </span>}/>
                        : <SnackbarContent
                            style={{backgroundColor: 'rgb(211, 47, 47)'}}
                            aria-describedby="client-snackbar"
                            message={
                                <span id="client-snackbar"
                                      style={{backgroundColor: 'rgb(211, 47, 47)', display: 'flex', alignItems: 'center'}}>
                            <ErrorIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>
                            Operation fail!</span>}/>
                }
            </Snackbar>
        )
    };

    return (
        <React.Fragment>
            {snackNotification()}
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                <Grid container direction="row" justify="space-between" alignItems="baseline">
                    {deal && deal.dealInfo &&
                    <div>
                        <Typography component="h1" display="inline" variant="h4" color="inherit">
                            {deal.dealInfo.dealTitle}
                        </Typography>
                    </div>
                    }
                </Grid>
                <Stepper activeStep={activeStep} alternativeLabel>
                    {steps.map(label => (
                        <Step key={label}>
                            <StepLabel>{label}</StepLabel>
                        </Step>
                    ))}
                </Stepper>
                {
                    deal && deal.state &&
                    <Typography variant="subtitle1" gutterBottom>
                        Deal state: {deal.state}
                    </Typography>
                }
                {
                    deal && deal.dealInfo.dealSubject ?
                        <Paper style={{padding: 7, marginTop: 10, marginBottom: 10}}>
                            {ReactHtmlParser(deal.dealInfo.dealSubject)}
                        </Paper>
                        : <React.Fragment/>
                }
                <div>
                    <Button style={{margin: 10}}
                            variant="contained"
                            size="large"
                            onClick={() => console.log(deal)}
                            color="primary">
                        DEAL
                    </Button>
                    <Button style={{margin: 10}}
                            variant="contained"
                            onClick={() => handleBack()}
                            size="large">
                        BACK
                    </Button>
                    <Button style={{margin: 10}}
                            variant="contained"
                            size="large"
                            onClick={() => handleDecline()}
                            color="secondary">
                        DECLINE
                    </Button>
                </div>
            </div>
        </React.Fragment>
    );

}