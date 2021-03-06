import React, {useEffect, useState} from 'react';
import {List, ListItem, ListItemText} from '@material-ui/core';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import {useActionSetter} from "../../../contexts/TemplateContext";
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import Fab from '@material-ui/core/Fab';
import Paper from '@material-ui/core/Paper';
import AddIcon from '@material-ui/icons/Add';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Slide from '@material-ui/core/Slide';
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import CheckCircleIcon from '@material-ui/icons/CheckCircle';
import ErrorIcon from '@material-ui/icons/Error';
import request, {TEMPLATE_PATH} from "./../../../request/request"

const CURRENT_ACTION = 'list';
export default function ListTemplates(props) {
    const setAction = useActionSetter();
    const token = props.auth.jwt;
    const [templates, setTemplates] = useState();
    const [dialogState, setDialogState] = React.useState({open: false});
    const [openSnack, setOpenSnack] = React.useState(false);
    const [positiveSnack, setPositiveSnack] = React.useState(false);

    const openEditTemplate = (id) => {
        setAction({
            action: 'edit',
            id: id,
            previousAction: CURRENT_ACTION
        });
    };

    const openViewTemplate = (id) => {
        setAction({
            action: 'view',
            id: id,
            previousAction: CURRENT_ACTION
        });
    };

    const openCreateTemplate = () => {
        setAction({
            action: 'add',
            previousAction: CURRENT_ACTION
        });
    };

    const hideSnack = () => {
        setOpenSnack(false);
    };

    const openDeleteDialog = (templateId, templateName) => {
        hideSnack();
        setDialogState({
            id: templateId,
            name: templateName,
            open: true
        });
    };

    const handleCloseDeleteDialog = () => {
        setDialogState({open: false});
    };

    const removeTemplateById = (id) => {
        setTemplates(prevState => {
            const index = prevState.findIndex(dataItem => dataItem.id === id);
            prevState.splice(index, 1);
            return prevState;
        });
    };

    useEffect(() => {
        const options = {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };
        request(TEMPLATE_PATH, '/templates/brief', options)
            .then(response => setTemplates(response.json))
            .catch(response => {
                console.log('!!!', response);
            });
    }, []);


    const handleAgreeDeleteDialog = () => {
        const templateId = dialogState.id;
        setDialogState({open: false});
        const options = {
            method: 'delete',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
        };
        request(TEMPLATE_PATH, `/templates/${templateId}`, options)
            .then((response) => {
                setPositiveSnack(true);
                removeTemplateById(templateId);
                setOpenSnack(true);
                console.log(response.status);
            })
            .catch((error) => {
                setPositiveSnack(false);
                setOpenSnack(true);
                console.log(error);
            });
    };

    const removeDialog = () => {
        return (
            <Dialog
                open={dialogState.open}
                TransitionComponent={Transition}
                keepMounted onClose={handleCloseDeleteDialog}
                aria-labelledby="alert-dialog-slide-title"
                aria-describedby="alert-dialog-slide-description">
                <DialogTitle id="alert-dialog-slide-title">
                    Delete '{dialogState.name}' template?
                </DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-slide-description">
                        Are you sure want to delete template '{dialogState.name}'?
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDeleteDialog} color="primary">
                        Disagree
                    </Button>
                    <Button onClick={handleAgreeDeleteDialog} color="primary">
                        Agree
                    </Button>
                </DialogActions>
            </Dialog>
        );
    };

    const snackNotification = () => {
        return (<Snackbar
            anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
            }}
            open={openSnack}
            onClose={hideSnack}
            autoHideDuration={3000}>
            {
                positiveSnack
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
                            Template deleted successfully! </span>}/>
                    : <SnackbarContent
                        style={{backgroundColor: 'rgb(211, 47, 47)'}}
                        aria-describedby="client-snackbar"
                        message={
                            <span id="client-snackbar"
                                  style={{backgroundColor: 'rgb(211, 47, 47)', display: 'flex', alignItems: 'center'}}>
                            <ErrorIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>
                            Delete template error!</span>}/>
            }
        </Snackbar>);
    };

    return (
        <React.Fragment>
            {snackNotification()}
            {removeDialog()}
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                <Grid container direction="row" justify="space-between" alignItems="baseline">
                    <div>
                        <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>Template
                            list </Typography>
                    </div>
                    <Fab style={{backgroundColor: 'rgb(67, 160, 71)'}} onClick={() => openCreateTemplate()}
                         aria-label="add">
                        <AddIcon style={{color: 'white'}}/>
                    </Fab>
                </Grid>
                {templates && templates.length !== 0 &&
                <Paper style={{marginTop: 10}}>
                    <List component="nav">
                        {templates && templates.map((template, i) => (
                            <ListItem key={i} onClick={() => openViewTemplate(template.id)} button>
                                <ListItemText primary={template.name}/>
                                <ListItemSecondaryAction>
                                    <IconButton edge="end" onClick={() => openEditTemplate(template.id)}
                                                aria-label="edit">
                                        <EditIcon color="primary"/>
                                    </IconButton>
                                    <IconButton edge="end"
                                                onClick={() => openDeleteDialog(template.id, template.name)}
                                                aria-label="delete">
                                        <DeleteIcon/>
                                    </IconButton>
                                </ListItemSecondaryAction>
                            </ListItem>
                        ))}
                    </List>
                </Paper>
                }
            </div>
        </React.Fragment>
    );
}


const Transition = React.forwardRef(function Transition(props, ref) {
    return ref && <Slide direction="up" ref={ref} {...props} />;
});
