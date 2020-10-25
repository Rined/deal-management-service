import React from 'react';
import ReactHtmlParser from 'react-html-parser';
import Paper from '@material-ui/core/Paper';
import {htmlRender} from "../utils/Utils";

export default function PaperMdRender(props){

    if (!props.format)
        return <React.Fragment/>;

    return (
        <Paper style={{padding: 7}}>
            {ReactHtmlParser(htmlRender(props.format, props.fields))}
        </Paper>
    );
}