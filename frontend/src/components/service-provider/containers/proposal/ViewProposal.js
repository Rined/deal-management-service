import React, {useEffect, useState} from 'react';
import Grid from '@material-ui/core/Grid';
import ReactHtmlParser from 'react-html-parser';
import MaterialTable from 'material-table';
import MarkdownIt from "markdown-it";
import Typography from '@material-ui/core/Typography';
import Fab from '@material-ui/core/Fab';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import TextField from '@material-ui/core/TextField';
import Paper from '@material-ui/core/Paper';
import {useActionSetter} from "../../../contexts/ProposalContext";
import request from "./../../../request/request"

const CURRENT_ACTION = 'view';
const columns = [
    {title: 'Description', field: 'description'},
    {title: 'Value', field: 'value'}
];
export default function ViewProposal(props) {
    const mdParser = new MarkdownIt();
    const token = props.auth.jwt;

    const [state, setState] = useState();
    const setAction = useActionSetter();

    useEffect(() => {
        const proposalId = props.param.id;
        const options = {
            headers: {
                'Authorization': token
            }
        };
        request(`/proposals/api/proposals/${proposalId}`, options)
            .then(response => setState(response.json));
    }, []);


    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    const customHtmlRender = (text) => {
        if(!text)
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

    if (!state)
        return <React.Fragment/>;

    return (
        <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
            <Grid container direction="row" justify="space-between" alignItems="baseline">
                <div>
                    <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>View
                        proposal </Typography>
                </div>
                <Fab onClick={() => handleBack()} color="primary" aria-label="add">
                    <ArrowBackIosIcon style={{color: 'white', paddingLeft: 10}}/>
                </Fab>
            </Grid>
            <TextField
                id="standard-basic"
                label="Proposal name"
                margin="normal"
                value={state.name}
                style={{width: 300}}/>
            {
                state.format ?
                    <Paper style={{padding: 7}}>
                        {ReactHtmlParser(customHtmlRender(state.format))}
                    </Paper>
                    : <React.Fragment/>
            }
            <MaterialTable
                title="Proposal fields"
                columns={columns}
                data={state.fields}
            />
        </div>
    );
}