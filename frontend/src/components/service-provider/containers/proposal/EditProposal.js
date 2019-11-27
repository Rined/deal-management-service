import React, {useEffect, useState} from 'react';
import Typography from '@material-ui/core/Typography';
import Fab from '@material-ui/core/Fab';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import SaveIcon from '@material-ui/icons/Save';
import {useActionSetter} from "../../../contexts/ProposalContext";
import MaterialTable from 'material-table';
import MarkdownIt from 'markdown-it';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import CheckCircleIcon from '@material-ui/icons/CheckCircle';
import ErrorIcon from '@material-ui/icons/Error';
import CircularProgress from '@material-ui/core/CircularProgress';
import ReactHtmlParser from "react-html-parser";
import Paper from '@material-ui/core/Paper';
import {makeStyles} from "@material-ui/core/styles";
import request from "./../../../request/request";

const CURRENT_ACTION = 'edit';
const columns = [
    {title: 'Description', field: 'description', editable: 'never'},
    {title: 'Value', field: 'value'}
];
export default function EditProposal(props) {
    const mdParser = new MarkdownIt();
    const setAction = useActionSetter();
    const token = props.auth.jwt;
    const proposalId = props.param.id;
    const classes = useStyles();

    const [state, setState] = useState();
    const [loading, setLoading] = React.useState(false);
    const [open, setOpen] = React.useState(false);
    const [positive, setPositive] = React.useState(false);


    useEffect(() => {
        const options = {
            headers: {
                'Authorization': token
            }
        };
        request(`/proposals/api/proposals/${proposalId}`, options)
            .then(response => {
                const proposal = response.json;
                if (proposal.fields) {
                    for (let i = 0; i < proposal.fields.length; i++) {
                        if (!proposal.fields[i].value || proposal.fields[i].value.length === 0) {
                            delete proposal.fields[i].value;
                        }
                    }
                }
                setState(proposal);
            });
    }, []);

    const save = () => {
        setLoading(true);
        const proposalDto = JSON.parse(JSON.stringify(state));
        delete proposalDto.format;
        const options = {
            method: 'put',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(proposalDto)
        };
        request(`/proposals/api/proposals/${proposalId}`, options)
            .then((response) => {
                setTimeout(() => {
                    setPositive(true);
                    setLoading(false);
                    setOpen(true);
                }, 500);
                console.log(response.status);
            })
            .catch((error) => {
                setTimeout(() => {
                    setPositive(false);
                    setLoading(false);
                    setOpen(true);
                }, 500);
                console.log(error);
            });
    };

    const updateStateOnUpdate = (newData, oldData) => {
        setState(prevState => {
            const fields = [...prevState.fields];
            fields[fields.indexOf(oldData)] = newData;
            return {...prevState, fields};
        });
    };

    const setTitleState = (text) => {
        setState(curState => {
            curState.name = text;
            return curState;
        });
    };

    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    const handleClose = () => {
        setOpen(false);
    };

    const customHtmlRender = (text) => {
        if (!text)
            return "";
        const codeQuote = "```";
        let templateArr = text.match(/\${.+}/g) || [];
        templateArr.forEach(function (item) {
            const data = state.fields;
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
                            Proposal edited successfully! </span>}/>
                        : <SnackbarContent
                            style={{backgroundColor: 'rgb(211, 47, 47)'}}
                            aria-describedby="client-snackbar"
                            message={
                                <span id="client-snackbar"
                                      style={{backgroundColor: 'rgb(211, 47, 47)', display: 'flex', alignItems: 'center'}}>
                            <ErrorIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>
                            Edit proposal error!</span>}/>
                }
            </Snackbar>
        )
    };

    if (!state)
        return <React.Fragment/>;

    return (
        <React.Fragment>
            {snackNotification()}
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                <Grid container direction="row" justify="space-between" alignItems="baseline">
                    <div>
                        <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>Edit
                            proposal </Typography>
                        {loading && <CircularProgress size={30} style={{color: 'rgb(67, 160, 71)'}}/>}
                    </div>
                    <Grid style={{width: 120}} item>
                        <Grid container direction="row" justify="space-between">
                            <Fab onClick={() => handleBack()} color="primary" aria-label="add">
                                <ArrowBackIosIcon style={{color: 'white', paddingLeft: 10}}/>
                            </Fab>
                            <Fab onClick={() => save()} style={{backgroundColor: 'rgb(67, 160, 71)'}} aria-label="add">
                                <SaveIcon style={{color: 'white'}}/>
                            </Fab>
                        </Grid>
                    </Grid>
                </Grid>
                <TextField
                    id="standard-basic"
                    label="Proposal name"
                    margin="normal"
                    defaultValue={state.name}
                    style={{width: 300}}
                    onChange={(event) => setTitleState(event.target.value)}/>
                <Paper style={{padding: 7}} className={classes.test}>
                    {ReactHtmlParser(customHtmlRender(state.format))}
                </Paper>
                <MaterialTable
                    title="Proposal fields"
                    columns={columns}
                    data={state.fields}
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
            </div>
        </React.Fragment>
    );
}

export const useStyles = makeStyles(() => ({
    test: {
        "& code": {
            background: "rgb(224, 224, 224)"
        }
    }
}));