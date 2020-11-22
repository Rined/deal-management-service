import React, {useEffect, useState} from 'react';
import Typography from '@material-ui/core/Typography';
import Fab from '@material-ui/core/Fab';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import {useActionSetter} from "../../../contexts/TemplateContext";
import MaterialTable from 'material-table';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import request, {TEMPLATE_PATH} from "./../../../request/request";
import PaperMdRender from "../../../elements/PaperMdRender";

const CURRENT_ACTION = 'view';
export default function ViewTemplate(props) {
    const setAction = useActionSetter();

    const [state, setState] = useState();

    useEffect(() => {
        const options = {
            headers: {
                'Authorization': 'Bearer ' + props.auth.jwt
            }
        };
        request(TEMPLATE_PATH, `/templates/${props.param.id}`, options)
            .then(response => setState(response.json));
    }, []);

    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    if (!state)
        return <React.Fragment/>;

    return (
        <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
            <Grid container direction="row" justify="space-between" alignItems="baseline">
                <div>
                    <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>
                        View template
                    </Typography>
                </div>
                <Fab onClick={() => handleBack()} color="primary" aria-label="add">
                    <ArrowBackIosIcon style={{color: 'white', paddingLeft: 10}}/>
                </Fab>
            </Grid>
            <TextField id="standard-basic" label="Template name" margin="normal" style={{width: 300}}
                       value={state.name}/>
            <PaperMdRender format={state.format} fields={state.fields}/>
            {
                state.fields && state.fields.length ?
                    <MaterialTable
                        title="Template fields"
                        data={state.fields}
                        columns={[{title: 'Field', field: 'name'}, {title: 'Description', field: 'description'}]}/>
                    : <React.Fragment/>
            }
        </div>
    );
}