import React, {useState} from 'react';
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
import CircularProgress from '@material-ui/core/CircularProgress';
import request from "./../../../request/request";
import {generateId} from "./../../../utils/Utils"
import {defaultSnack} from "./../../../utils/DefaultSnack"

const CURRENT_ACTION = 'add';
const columns = [
    {title: 'Field', field: 'name'},
    {title: 'Description', field: 'description'}
];
export default function AddTemplate(props) {
    const token = props.auth.jwt;
    const mdParser = new MarkdownIt();
    const setAction = useActionSetter();
    let mdEditor = null;

    const [disable, setDisable] = React.useState(false);
    const [loading, setLoading] = React.useState(false);
    const [open, setOpen] = React.useState(false);
    const [positive, setPositive] = React.useState(false);
    const [state, setState] = useState({
        id: generateId(),
        name: "",
        fields: [],
        format: ""
    });

    const disableControl = () => {
        setLoading(true);
        setDisable(true);
    };

    const enableControl = (isCorrect) => {
        setPositive(isCorrect);
        setLoading(false);
        setOpen(true);
        setDisable(false);
    };

    const save = () => {
        disableControl();
        const options = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
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

    if (!state)
        return <React.Fragment/>;

    return (
        <React.Fragment>
            {defaultSnack(open, handleClose, positive, 'Template created successfully!', 'Create template error!')}
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                <Grid container direction="row" justify="space-between" alignItems="baseline">
                    <div>
                        <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>Create
                            template </Typography>
                        {loading && <CircularProgress size={30} style={{color: 'rgb(67, 160, 71)'}}/>}
                    </div>
                    <Grid style={{width: 120}} item>
                        <Grid container direction="row" justify="space-between">
                            <Fab onClick={() => handleBack()}
                                 style={{backgroundColor: disable ? 'rgb(119, 136, 153)' : 'rgb(63, 81, 181)'}}
                                 aria-label="add">
                                <ArrowBackIosIcon style={{color: 'white', paddingLeft: 10}}/>
                            </Fab>
                            <Fab onClick={() => save()} disabled={disable}
                                 style={{
                                     backgroundColor: disable ? 'rgb(119, 136, 153)' :
                                         'rgb(67, 160, 71)'
                                 }} aria-label="add">
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
                                }, 300);
                            }),
                        onRowUpdate: (newData, oldData) =>
                            new Promise(resolve => {
                                setTimeout(() => {
                                    if (oldData) {
                                        resolve();
                                        updateStateOnUpdate(newData, oldData);
                                    }
                                }, 300);
                            }),
                        onRowDelete: oldData =>
                            new Promise(resolve => {
                                setTimeout(() => {
                                    resolve();
                                    updateStateOnRemove(oldData);
                                }, 300);
                            }),
                    }}
                />
            </div>
        </React.Fragment>
    );
}