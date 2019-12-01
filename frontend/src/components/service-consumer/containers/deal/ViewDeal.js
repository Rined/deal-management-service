import React, {useEffect, useState} from 'react';
import {useActionSetter} from "./../../../contexts/DealConsumerContext";
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
import RefreshIcon from '@material-ui/icons/Refresh';
import Fab from '@material-ui/core/Fab';
import MaterialTable from 'material-table';

const CURRENT_ACTION = 'view';
const columns = [
    {title: 'Description', field: 'description', editable: 'never'},
    {title: 'Value', field: 'value'}
];
export default function ViewDeal(props) {
    const [activeStep, setActiveStep] = React.useState(0);
    const steps = [
        'Wait provider accept',
        'Wait consumer accept',
        'Wait provider info request',
        'Wait consumer provide info',
        'In work',
        'Done'
    ];
    const token = props.auth.jwt;
    const setAction = useActionSetter();
    const role = 'consumer';
    const [deal, setDeal] = useState();
    const [table, setTable] = useState({});

    const [positive, setPositive] = React.useState(false);
    const [open, setOpen] = React.useState(false);

    const getStepNumber = (state) => {
        switch (state) {
            case 'PROVIDER_ACCEPT':
                return 0;
            case 'CONSUMER_ACCEPT':
                return 1;
            case 'PROVIDER_REQUEST_INFO':
                return 2;
            case 'CONSUMER_PROVIDE_INFO':
                return 3;
            case 'IN_WORK':
                return 4;
            case 'DONE':
                return 6;
        }
    };

    useEffect(() => {
        load();
    }, []);

    const load = () => {
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
    };

    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    const handleRefresh = () => {
        load();
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

    const handleAccept = () => {
        const options = {
            method: 'post',
            headers: {
                'Authorization': token
            },
        };
        request(`/deals/api/deals/${role}/${props.param.id}/accept`, options)
            .then((response) => {
                console.log(response);
                setPositive(true);
                setOpen(true);
                setDeal(response.json);
                setActiveStep(getStepNumber(response.json.state));
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

    const handleSend = () => {
        for (let i = 0; i < table.fields.length; i++) {
            if (!table.fields[i].value || table.fields[i].value.length === 0) {
                setPositive(false);
                setOpen(true);
                return;
            }
        }
        console.log(table);
        const options = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(table)
        };
        request(`/deals/api/deals/${role}/${props.param.id}/request`, options)
            .then((response) => {
                console.log(response);
                setPositive(true);
                setOpen(true);
                setDeal(response.json);
                setActiveStep(getStepNumber(response.json.state));
            })
            .catch((error) => {
                setPositive(false);
                setOpen(true);
                console.log(error);
            });
    };

    const needButton = (btnName, state) => {
        switch (btnName) {
            case 'DECLINE':
                return state !== 'DONE'
                    && state !== 'PROVIDER_DECLINE'
                    && state !== 'CONSUMER_DECLINE';
            case 'ACCEPT':
                return state === 'CONSUMER_ACCEPT';
            case 'SEND':
                return state === 'CONSUMER_PROVIDE_INFO';

        }
    };

    const buttons = (state) => {
        return (
            <React.Fragment>
                {
                    needButton('SEND', state) &&
                    <Button style={{margin: 10}}
                            variant="contained"
                            size="large"
                            onClick={() => handleSend()}
                            color="primary">
                        SEND
                    </Button>
                }
                {
                    needButton('ACCEPT', state) &&
                    <Button style={{margin: 10}}
                            variant="contained"
                            size="large"
                            onClick={() => handleAccept()}
                            color="primary">
                        ACCEPT
                    </Button>
                }
                {
                    needButton('DECLINE', state) &&
                    <Button
                        style={{margin: 10}}
                        variant="contained"
                        size="large"
                        onClick={() => handleDecline()}
                        color="secondary">
                        DECLINE
                    </Button>
                }
            </React.Fragment>
        );
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

    const updateStateOnUpdate = (newData, oldData) => {
        setTable(prevState => {
            const fields = [...prevState.fields];
            fields[fields.indexOf(oldData)] = newData;
            return {...prevState, fields};
        });
    };

    const fillTableState = () => {
        if (!deal.dealInfo.info)
            return false;
        if (table.fields)
            return true;
        const info = deal.dealInfo.info;
        for (let i = 0; i < info.length; i++) {
            if (!info[i].value || info[i].value.length === 0) {
                info[i].value = '';
            }
        }
        setTable({fields: info});
        return true;
    };

    const currentStateStatus = () => {
        switch (getStepNumber(deal.state)) {
            case 0:
                return 'Please wait while provider accept deal';
            case 1:
                return 'Please accept with deal';
            case 2:
                return 'Please wait while provider request necessary information for deal';
            case 3:
                return 'Please provide information for deal';
            case 4:
                return 'Deal in work';
            case 5:
            case 6:
                return 'Done';
            default:
                return 'DECLINE';
        }
    };

    return (
        <React.Fragment>
            {snackNotification()}
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                <Grid container direction="row" justify="space-between" alignItems="baseline">
                    {deal && deal.dealInfo &&
                    <React.Fragment>
                        <Grid item>
                            <Typography component="h1" display="inline" variant="h4" color="inherit">
                                {deal.dealInfo.dealTitle}
                            </Typography>
                        </Grid>
                        <Grid item>
                            <Fab onClick={() => handleRefresh()} color="primary" aria-label="add">
                                <RefreshIcon style={{color: 'white'}}/>
                            </Fab>
                        </Grid>
                    </React.Fragment>
                    }
                </Grid>
                {
                    deal && currentStateStatus() !== 'DECLINE' &&
                    <Stepper activeStep={activeStep} alternativeLabel>
                        {steps.map(label => (
                            <Step key={label}>
                                <StepLabel>{label}</StepLabel>
                            </Step>
                        ))}
                    </Stepper>
                }
                <Typography component="h1" display="inline" variant="h5" color="inherit">
                    Deal info:
                </Typography>
                {
                    deal && deal.state &&
                    <Typography variant="subtitle1" gutterBottom>
                        Status: {currentStateStatus()}
                    </Typography>
                }
                {
                    activeStep > 3
                    && deal
                    && deal.dealInfo
                    && deal.dealInfo.info
                    && deal.dealInfo.info.map((info, i) => (
                        <Typography key={i} variant="subtitle1" gutterBottom>
                            {`${info.description}:`} {info.value}
                        </Typography>
                    ))
                }
                {
                    deal && deal.dealInfo.dealSubject ?
                        <Paper style={{padding: 7, marginTop: 10, marginBottom: 10}}>
                            {ReactHtmlParser(deal.dealInfo.dealSubject)}
                        </Paper>
                        : <React.Fragment/>
                }
                {
                    activeStep === 3 && fillTableState() && table.fields && table.fields.length !== 0 &&
                    <MaterialTable
                        title="Fill info for deal"
                        columns={columns}
                        data={table.fields}
                        editable={{
                            onRowUpdate: (newData, oldData) =>
                                new Promise(resolve => {
                                    setTimeout(() => {
                                        if (oldData) {
                                            resolve();
                                            updateStateOnUpdate(newData, oldData);
                                        }
                                    }, 600);
                                }),
                        }}
                    />
                }
                <div>
                    {
                        deal && deal.state && buttons(deal.state)
                    }
                    <Button style={{margin: 10}}
                            variant="contained"
                            onClick={() => handleBack()}
                            size="large">
                        BACK
                    </Button>
                </div>
            </div>
        </React.Fragment>
    );
}