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


const CURRENT_ACTION = 'add';
const columns = [
    {title: 'Description', field: 'description', editable: 'never'},
    {title: 'Value', field: 'value'}
];
export default function AddProposal() {
    const classes = useStyles();
    const mdParser = new MarkdownIt();
    const [proposal, setProposal] = useState({
        name: "",
        fields: [],
        template: {}
    });
    const [templates, setTemplates] = useState();
    const setAction = useActionSetter();

    useEffect(() => {
        fetch('http://localhost:8080/templates/brief')
            .then(response => response.json())
            .then(templates => setTemplates(templates));
    }, []);

    const save = () => {
        // if (proposal
        //     && proposal.fields
        //     && proposal.fields.some((row) => !row.hasOwnProperty('value'))) {
        // }
    };

    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    const handleChange = event => {
        const templateId = event.target.value;
        fetch(`http://localhost:8080/templates/${templateId}`)
            .then(response => response.json())
            .then(backendTemplate => {
                const fields = backendTemplate.fields;
                delete backendTemplate.fields;
                setProposal({
                    name: "",
                    fields: fields,
                    template: backendTemplate
                })
            });
    };

    const updateStateOnUpdate = (newData, oldData) => {
        setProposal(prevState => {
            const fields = [...prevState.fields];
            fields[fields.indexOf(oldData)] = newData;
            return {...prevState, fields};
        });
    };

    const setTitleState = (text) => {
        setProposal(curState => {
            curState.name = text;
            return curState;
        });
    };

    const customHtmlRender = (text) => {
        if(!text)
            return "";
        const codeQuote = "```";
        let templateArr = text.match(/\${.+}/g) || [];
        templateArr.forEach(function (item) {
            const data = proposal.fields;
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
        <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
            <button onClick={() => console.log(proposal)}>click</button>
            <Grid container direction="row" justify="space-between" alignItems="baseline">
                <div>
                    <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>Create
                        proposal </Typography>
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
                id="outlined-select-currency"
                select
                label="Template"
                style={{width: 500}}
                value={proposal.template.id ? proposal.template.id : {}}
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

            {
                proposal.template && proposal.template.format
                    ? <div style={{width: "100%"}}>
                        <TextField
                            autoComplete="off"
                            id="standard-basic"
                            label="Proposal name"
                            margin="normal"
                            defaultValue={proposal.name}
                            style={{width: 300}}
                            onChange={(event) => setTitleState(event.target.value)}/>
                        <Paper style={{padding: 7}} className={classes.test}>
                            {ReactHtmlParser(customHtmlRender(proposal.template.format))}
                        </Paper>
                        <MaterialTable
                            title="Fill fields"
                            columns={columns}
                            data={proposal.fields}
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
                    : <React.Fragment/>
            }
        </div>
    );
}

export const useStyles = makeStyles(() => ({
    test: {
        "& code": {
            background: "rgb(224, 224, 224)"
        }
    }
}));
