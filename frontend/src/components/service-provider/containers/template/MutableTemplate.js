import React, {useState} from 'react';
import Typography from '@material-ui/core/Typography';
import Fab from '@material-ui/core/Fab';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import SaveIcon from '@material-ui/icons/Save';
import {useActionSetter} from "../../../contexts/TemplateContext";
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import CircularProgress from '@material-ui/core/CircularProgress';
import request from "./../../../request/request";
import {DefaultSnack} from "./../../../utils/DefaultSnack"
import {Table} from "../../../elements/Table";
import {MdElement} from "../../../elements/MdElement";

const CURRENT_ACTION = 'add';
export default function MutableTemplate(props) {
    const setAction = useActionSetter();

    const [properties, setProperties] = React.useState({
        process: false,
        open: false,
        positive: false
    });

    const [state, setState] = useState(props.state);

    const enableControl = (isCorrect) => {
        setProperties(prevProperties => {
            return {
                ...prevProperties,
                positive: isCorrect,
                open: true,
                process: false
            }
        });
    };

    const updateFormat = (text) => {
        setState(prevState => {
            return {...prevState, format: text};
        });
    };

    const updateFields = (newFields) => {
        setState(prevState => {
            return {...prevState, fields: newFields};
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
        setProperties(prevProperties => {
            return {
                ...prevProperties,
                open: false
            }
        });
    };

    const save = () => {
        setProperties(prevProperties => {
            return {
                ...prevProperties,
                process: true
            }
        });
        const options = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': props.auth.jwt
            },
            body: JSON.stringify(state)
        };
        request(`/templates/api/templates`, options)
            .then((response) => {
                setTimeout(() => enableControl(true), 500);
                console.log(response.status);
            })
            .catch((error) => {
                setTimeout(() => enableControl(false), 500);
                console.log(error);
            });
    };

    if (!state)
        return <React.Fragment/>;

    return (
        <React.Fragment>
            <DefaultSnack positiveText={'Template created successfully!'}
                          negativeText={'Create template error!'}
                          handleCloseFunction={handleClose}
                          positive={properties.positive}
                          isOpen={properties.open}/>
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                <Grid container direction="row" justify="space-between" alignItems="baseline">
                    <div>
                        <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>
                            Create template
                        </Typography>
                        {properties.process && <CircularProgress size={30} style={{color: 'rgb(67, 160, 71)'}}/>}
                    </div>
                    <Grid style={{width: 120}} item>
                        <Grid container direction="row" justify="space-between">
                            <Fab onClick={() => handleBack()}
                                 style={{backgroundColor: properties.process ? 'rgb(119, 136, 153)' : 'rgb(63, 81, 181)'}}
                                 aria-label="add">
                                <ArrowBackIosIcon style={{color: 'white', paddingLeft: 10}}/>
                            </Fab>
                            <Fab onClick={() => save()} disabled={properties.process}
                                 style={{backgroundColor: properties.process ? 'rgb(119, 136, 153)' : 'rgb(67, 160, 71)'}}
                                 aria-label="add">
                                <SaveIcon style={{color: 'white'}}/>
                            </Fab>
                        </Grid>
                    </Grid>
                </Grid>
                <TextField id="standard-basic" label="Template name" margin="normal"
                           defaultValue={state.name} style={{width: 300}}
                           onChange={(event) => setTitleState(event.target.value)}/>
                <div style={{height: "500px"}}>
                    <MdElement stateCallback={updateFormat}
                               fields={state.fields}
                               format={state.format}/>
                </div>
                <Table title='Template fields'
                       data={state.fields}
                       stateCallback={updateFields}
                       columns={[{title: 'Field', field: 'name'}, {title: 'Description', field: 'description'}]}/>
            </div>
        </React.Fragment>
    );
}