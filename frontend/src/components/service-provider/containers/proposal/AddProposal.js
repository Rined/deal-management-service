import React, {useEffect, useState} from 'react';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import MaterialTable from 'material-table';
import ReactHtmlParser from "react-html-parser";
import MarkdownIt from "markdown-it";
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import Fab from '@material-ui/core/Fab';
import SaveIcon from '@material-ui/icons/Save';
import {makeStyles} from "@material-ui/core/styles";
import {useActionSetter} from "../../../contexts/ProposalContext";
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import CheckCircleIcon from '@material-ui/icons/CheckCircle';
import ErrorIcon from '@material-ui/icons/Error';
import CircularProgress from '@material-ui/core/CircularProgress';
import {generateId} from "../../../utils/Utils"
import request, {PROPOSAL_PATH, TEMPLATE_PATH} from "./../../../request/request"

const CURRENT_ACTION = 'add';
const columns = [
    {title: 'Description', field: 'description', editable: 'never'},
    {title: 'Value', field: 'value'}
];
export default function AddProposal(props) {
    const mdParser = new MarkdownIt();
    const token = props.auth.jwt;
    const classes = useStyles();
    const setAction = useActionSetter();

    const [positive, setPositive] = React.useState(false);
    const [loading, setLoading] = React.useState(false);
    const [open, setOpen] = React.useState(false);

    const [multiOperation, setMultiOperation] = React.useState({
        allow: false,
        done: false
    });

    const [templates, setTemplates] = useState();
    const [proposalView, setProposalView] = useState({
        id: generateId(),
        templateId: '',
        name: '',
        price: 0,
        fields: [],
        template: {}
    });


    useEffect(() => {
        const options = {headers: {'Authorization': 'Bearer ' + token}};
        request(TEMPLATE_PATH, '/templates/brief', options)
            .then(response => {
                console.log(response);
                setTemplates(response.json);
            });
    }, []);

    const getProposal = () => {
        return {
            id: proposalView.id,
            templateId: proposalView.templateId,
            name: proposalView.name,
            price: proposalView.price,
            fields: proposalView.fields.map(field => {
                return {
                    name: field.name,
                    value: field.value
                }
            })
        }
    };

    const save = () => {
        setLoading(true);
        const proposalDto = getProposal();
        // const proposalDto = JSON.parse(JSON.stringify(proposalView));
        // proposalDto.format = proposalView.template.format;
        // delete proposalDto.template;
        const options = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify(proposalDto)
        };
        request(PROPOSAL_PATH, `/proposals`, options)
            .then((response) => {
                setTimeout(() => {
                    setPositive(true);
                    setLoading(false);
                    setOpen(true);
                    setMultiOperation(prevState => {
                        return {
                            ...prevState,
                            done: true
                        }
                    });
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
        console.log(proposalView);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    const handleChange = event => {
        const templateId = event.target.value;
        const options = {headers: {'Authorization': 'Bearer ' + token}};
        request(TEMPLATE_PATH, `/templates/${templateId}`, options)
            .then(response => {
                const backendTemplate = response.json;
                const fields = backendTemplate.fields;
                delete backendTemplate.fields;
                setProposalView(prevState => {
                    return {
                        ...prevState,
                        templateId: templateId,
                        name: "",
                        fields: fields,
                        template: backendTemplate
                    }
                })
            });
    };

    const updateStateOnUpdate = (newData, oldData) => {
        setProposalView(prevState => {
            const fields = [...prevState.fields];
            fields[fields.indexOf(oldData)] = newData;
            return {...prevState, fields};
        });
    };

    const setTitleState = (text) => {
        setProposalView(curState => {
            curState.name = text;
            return curState;
        });
    };

    const setPrice = (price) => {
        setProposalView(curState => {
            curState.price = price;
            return curState;
        });
    };

    const customHtmlRender = (text) => {
        if (!text)
            return "";
        const codeQuote = "```";
        let templateArr = text.match(/\${.+}/g) || [];
        templateArr.forEach(function (item) {
            const data = proposalView.fields;
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
                            Proposal created successfully! </span>}/>
                        : <SnackbarContent
                            style={{backgroundColor: 'rgb(211, 47, 47)'}}
                            aria-describedby="client-snackbar"
                            message={
                                <span id="client-snackbar"
                                      style={{backgroundColor: 'rgb(211, 47, 47)', display: 'flex', alignItems: 'center'}}>
                            <ErrorIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>
                            Create proposal error!</span>}/>
                }
            </Snackbar>
        )
    };

    return (
        <React.Fragment>
            {snackNotification()}
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                <Grid container direction="row" justify="space-between" alignItems="baseline">
                    <div>
                        <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>Create
                            proposal </Typography>
                        {loading && <CircularProgress size={30} style={{color: 'rgb(67, 160, 71)'}}/>}
                    </div>
                    <Grid style={{width: 120}} item>
                        <Grid container direction="row" justify="space-between">
                            <Fab onClick={() => handleBack()}
                                 disabled={loading}
                                 style={{backgroundColor: loading ? 'rgb(119, 136, 153)' : 'rgb(63, 81, 181)'}}
                                 aria-label="add">
                                <ArrowBackIosIcon style={{color: 'white', paddingLeft: 10}}/>
                            </Fab>
                            <Fab onClick={() => save()}
                                 disabled={loading || (multiOperation.allow ? false : multiOperation.done)}
                                 style={{
                                     backgroundColor: (loading || (multiOperation.allow ? false : multiOperation.done))
                                         ? 'rgb(119, 136, 153)' : 'rgb(67, 160, 71)'
                                 }}
                                 aria-label="add">
                                <SaveIcon style={{color: 'white'}}/>
                            </Fab>
                        </Grid>
                    </Grid>
                </Grid>
                <TextField
                    id="outlined-select-currency"
                    select
                    label="Template"
                    style={{width: 500}}
                    value={proposalView.template.id ? proposalView.template.id : {}}
                    onChange={handleChange}
                    helperText="Please select template"
                    margin="normal"
                    variant="outlined">
                    {templates
                        ? templates.map((template) => (
                            <MenuItem key={template.id} value={template.id}>
                                {template.name}
                            </MenuItem>
                        )) : <MenuItem/>}
                </TextField>

                <div style={{width: "100%"}}>
                    {
                        proposalView.template && proposalView.template.id &&
                        <React.Fragment>
                            <div>
                                <TextField
                                    autoComplete="off"
                                    id="standard-basic"
                                    label="Proposal name"
                                    margin="normal"
                                    defaultValue={proposalView.name}
                                    style={{width: 300}}
                                    onChange={(event) => setTitleState(event.target.value)}/>
                            </div>
                            <div>
                                <TextField id="standard-number"
                                           style={{width: 300}}
                                           label="Price"
                                           type="number"
                                           onChange={(event) => setPrice(event.target.value)}/>
                            </div>
                            <Paper style={{padding: 7}} className={classes.test}>
                                {ReactHtmlParser(customHtmlRender(proposalView.template.format))}
                            </Paper>
                            <MaterialTable
                                title="Fill proposal fields"
                                columns={columns}
                                data={proposalView.fields}
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
                        </React.Fragment>
                    }
                </div>
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
