import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import CheckCircleIcon from "@material-ui/icons/CheckCircle";
import ErrorIcon from "@material-ui/icons/Error";
import React from "react";

export function DefaultSnack(props) {
    return (
        <Snackbar
            anchorOrigin={{vertical: 'bottom', horizontal: 'left'}}
            open={props.isOpen}
            onClose={props.handleCloseFunction}
            autoHideDuration={3000}>
            {
                <SnackbarContent
                    style={{backgroundColor: props.positive ? 'rgb(67, 160, 71)' : 'rgb(211, 47, 47)'}}
                    aria-describedby="client-snackbar"
                    message={
                        <span id="client-snackbar" style={{
                            backgroundColor: props.positive ? 'rgb(67, 160, 71)' : 'rgb(211, 47, 47)',
                            display: 'flex',
                            alignItems: 'center'
                        }}>
                                {props.positive
                                    ? <CheckCircleIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>
                                    : <ErrorIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>}
                            {props.positive ? props.positiveText : props.negativeText}
                            </span>
                    }
                />
            }
        </Snackbar>
    )
}