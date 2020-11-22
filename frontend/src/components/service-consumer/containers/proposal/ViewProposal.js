import React, {useEffect, useState} from 'react';
import {useActionSetter} from "../../../contexts/ProposalConsumerContext";
import request, {DEAL_PATH, PROPOSAL_PATH} from "./../../../request/request"
import MarkdownIt from "markdown-it";
import ReactHtmlParser from 'react-html-parser';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import CheckCircleIcon from '@material-ui/icons/CheckCircle';
import {generateId} from "../../../utils/Utils"
import ErrorIcon from '@material-ui/icons/Error';
import CardContent from "@material-ui/core/CardContent";

const CURRENT_ACTION = 'view';
export default function ViewProposal(props) {
    const mdParser = new MarkdownIt();

    const token = props.auth.jwt;
    const setAction = useActionSetter();
    const [state, setState] = useState({
        id: generateId(),
        proposalId: '',
        proposalTitle: '',
        dealSubject: '',
        providerId: '',
        price: 0
    });
    const [allowOperation, setAllowOperation] = React.useState(true);

    const [positive, setPositive] = React.useState(false);
    const [open, setOpen] = React.useState(false);

    useEffect(() => {
        const proposalId = props.param.id;
        const options = {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };
        request(PROPOSAL_PATH, `/proposals/consumer/${proposalId}`, options)
            .then(response => {
                let data = response.json;
                console.log(data);
                const htmlText = customHtmlRender(data.format, data.fields);
                setState(prevState => {
                    return {
                        ...prevState,
                        proposalId: data.id,
                        proposalTitle: data.name,
                        dealSubject: htmlText,
                        providerId: data.providerId,
                        price: data.price,
                        user: data.user
                    };
                });
            });
    }, []);

    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    const handleDeal = () => {
        setAllowOperation(false);
        const options = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify(state)
        };
        request(DEAL_PATH, `/deals`, options)
            .then((response) => {
                setPositive(true);
                setOpen(true);
                console.log(response);
            })
            .catch((error) => {
                setAllowOperation(true);
                setPositive(false);
                setOpen(true);
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
                            Deal created successfully! </span>}/>
                        : <SnackbarContent
                            style={{backgroundColor: 'rgb(211, 47, 47)'}}
                            aria-describedby="client-snackbar"
                            message={
                                <span id="client-snackbar"
                                      style={{backgroundColor: 'rgb(211, 47, 47)', display: 'flex', alignItems: 'center'}}>
                            <ErrorIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>
                            Create deal error!</span>}/>
                }
            </Snackbar>
        )
    };

    const customHtmlRender = (text, data) => {
        if (!text)
            return "";
        const codeQuote = "```";
        let templateArr = text.match(/\${.+}/g) || [];
        templateArr.forEach(function (item) {
            if (data) {
                const replaceObject = data.find(dataItem => dataItem.name === item.substring(2, item.length - 1));
                let replacer;
                let isValue = false;
                if (replaceObject) {
                    if (replaceObject.value) {
                        replacer = replaceObject.value;
                        isValue = true;
                    } else {
                        replacer = replaceObject.description;
                    }
                } else replacer = "UNKNOWN!!!";
                text = text.replace(item, replacer.length === 0 ? "" :
                    (isValue ? `${replacer}` : `${codeQuote}${replacer}${codeQuote}`));
            }
        });
        return mdParser.render(text);
    };


    return (
        <React.Fragment>
            {snackNotification()}
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                <Grid container direction="row" justify="space-between" alignItems="baseline">
                    {state && state.proposalTitle &&
                    <div>
                        <Typography component="h1" display="inline" variant="h4" color="inherit">
                            {state.proposalTitle}
                        </Typography>
                    </div>
                    }
                    {state && state.user && (
                        <div>
                            <Typography color="textSecondary">
                                {state.user.company + ' ' + state.user.city}
                            </Typography>
                            <Typography variant="body2" component="p">
                                {state.user.firstName + ' ' + state.user.lastName}
                            </Typography>
                        </div>
                    )}
                </Grid>
                <div>
                    <Typography component="h4" display="inline" variant="h6">
                        {state.price}$
                    </Typography>
                </div>
                {
                    state && state.dealSubject ?
                        <Paper style={{padding: 7, marginTop: 10, marginBottom: 10}}>
                            {ReactHtmlParser(state.dealSubject)}
                        </Paper>
                        : <React.Fragment/>
                }
                <div>
                    <Button style={{marginRight: 10}}
                            variant="contained"
                            size="large"
                            disabled={!allowOperation}
                            onClick={() => handleDeal()}
                            color="primary">
                        DEAL
                    </Button>
                    <Button style={{marginLeft: 10}}
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