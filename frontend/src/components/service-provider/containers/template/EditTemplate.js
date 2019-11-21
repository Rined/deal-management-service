import React, {useEffect, useState} from 'react';
import Typography from '@material-ui/core/Typography';
import Fab from '@material-ui/core/Fab';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import SaveIcon from '@material-ui/icons/Save';
import {useActionSetter} from "../../../contexts/TemplateContext";
import MaterialTable from 'material-table';
import MdEditor from 'react-markdown-editor-lite';
import MarkdownIt from 'markdown-it';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import CheckCircleIcon from '@material-ui/icons/CheckCircle';
import ErrorIcon from '@material-ui/icons/Error';
import CircularProgress from '@material-ui/core/CircularProgress';


const CURRENT_ACTION = 'edit';
const columns = [
    {title: 'Field', field: 'name'},
    {title: 'Description', field: 'description'}
];
export default function EditTemplate(props) {
    const mdParser = new MarkdownIt();
    const setAction = useActionSetter();
    const templateId = props.param.id;
    let mdEditor = null;

    const [state, setState] = useState();
    const [loading, setLoading] = React.useState(false);
    const [open, setOpen] = React.useState(false);
    const [positive, setPositive] = React.useState(false);


    useEffect(() => {
        fetch(`http://localhost:8080/templates/${templateId}`)
            .then(response => response.json())
            .then(template => setState(template));
    }, []);

    const save = () => {
        setLoading(true);
        fetch(`http://localhost:8080/templates/${templateId}`, {
            method: 'put',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(state)
        }).then(function (response) {
            setTimeout(() => {
                setPositive(response.ok);
                setLoading(false);
                setOpen(true);
            }, 500);
            console.log(response.status);
        }).catch((error) => {
            setTimeout(() => {
                setPositive(false);
                setLoading(false);
                setOpen(true);
            }, 500);
            console.log(error);
        });
    };

    const updateStateOnAdd = (newData) => {
        setState(prevState => {
            const fields = [...prevState.fields];
            fields.push(newData);
            return {...prevState, fields};
        });
        updateMdText();
    };

    const updateStateOnRemove = (oldData) => {
        setState(prevState => {
            const fields = [...prevState.fields];
            fields.splice(fields.indexOf(oldData), 1);
            return {...prevState, fields};
        });
        updateMdText();
    };

    const updateStateOnUpdate = (newData, oldData) => {
        setState(prevState => {
            const fields = [...prevState.fields];
            fields[fields.indexOf(oldData)] = newData;
            return {...prevState, fields};
        });
        updateMdText();
    };

    const updateStateOnMdEdit = ({text}) => {
        setState(curState => {
            curState.format = text;
            return curState;
        });
    };

    const setTitleState = (text) => {
        setState(curState => {
            curState.userName = text;
            return curState;
        });
    };

    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    const setMdEditor = (node) => {
        if (node)
            mdEditor = node;
    };

    const updateMdText = () => {
        if (mdEditor) {
            mdEditor._setMdText(mdEditor.getMdValue());
        }
    };

    const handleClose = () => {
        setOpen(false);
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
                            Template edited successfully! </span>}/>
                        : <SnackbarContent
                            style={{backgroundColor: 'rgb(211, 47, 47)'}}
                            aria-describedby="client-snackbar"
                            message={
                                <span id="client-snackbar"
                                      style={{backgroundColor: 'rgb(211, 47, 47)', display: 'flex', alignItems: 'center'}}>
                            <ErrorIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>
                            Edit template error!</span>}/>
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
                            template </Typography>
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
                    label="Template name"
                    margin="normal"
                    defaultValue={state.name}
                    style={{width: 300}}
                    onChange={(event) => setTitleState(event.target.value)}/>
                <div style={{height: "500px"}}>
                    <MdEditor
                        ref={node => setMdEditor(node)}
                        value={state.format}
                        renderHTML={(text) => customHtmlRender(text)}
                        onChange={updateStateOnMdEdit}
                    />
                </div>
                <MaterialTable
                    title="Template fields"
                    columns={columns}
                    data={state.fields}
                    editable={{
                        onRowAdd: newData =>
                            new Promise(resolve => {
                                setTimeout(() => {
                                    resolve();
                                    updateStateOnAdd(newData);
                                }, 600);
                            }),
                        onRowUpdate: (newData, oldData) =>
                            new Promise(resolve => {
                                setTimeout(() => {
                                    if (oldData) {
                                        resolve();
                                        updateStateOnUpdate(newData, oldData);
                                    }
                                }, 600);
                            }),
                        onRowDelete: oldData =>
                            new Promise(resolve => {
                                setTimeout(() => {
                                    resolve();
                                    updateStateOnRemove(oldData);
                                }, 600);
                            }),
                    }}
                />
            </div>
        </React.Fragment>
    );
}