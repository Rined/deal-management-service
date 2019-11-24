import React, {useEffect, useState} from 'react';
import Typography from '@material-ui/core/Typography';
import Fab from '@material-ui/core/Fab';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import {useActionSetter} from "../../../contexts/TemplateContext";
import MaterialTable from 'material-table';
import MarkdownIt from 'markdown-it';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import ReactHtmlParser from 'react-html-parser';
import Paper from '@material-ui/core/Paper';
import request from "./../../../request/request";

const CURRENT_ACTION = 'view';
const columns = [
    {title: 'Field', field: 'name'},
    {title: 'Description', field: 'description'}
];
export default function ViewTemplate(props) {
    const token = props.auth.jwt;
    const mdParser = new MarkdownIt();
    const setAction = useActionSetter();
    const [state, setState] = useState();

    useEffect(() => {
        const templateId = props.param.id;
        const options = {
            headers: {
                'Authorization': token
            }
        };
        request(`/templates/${templateId}`, options)
            .then(response => setState(response.json));
    }, []);


    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    const customHtmlRender = (text) => {
        const codeQuote = "```";
        let templateArr = text.match(/\${.+}/g) || [];
        templateArr.forEach(function (item) {
            const data = state.fields;
            if (data) {
                const replaceObject = data.find(dataItem => dataItem.name === item.substring(2, item.length - 1));
                const replacer = replaceObject ? replaceObject.description : "UNKNOWN!!!";
                text = text.replace(item, replacer.length === 0 ? "" : `${codeQuote}${replacer}${codeQuote}`);
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
                        template </Typography>
                </div>
                <Fab onClick={() => handleBack()} color="primary" aria-label="add">
                    <ArrowBackIosIcon style={{color: 'white', paddingLeft: 10}}/>
                </Fab>
            </Grid>
            <TextField
                id="standard-basic"
                label="Template name"
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
                title="Template fields"
                columns={columns}
                data={state.fields}
            />
        </div>
    );
}