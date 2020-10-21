import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import CheckCircleIcon from "@material-ui/icons/CheckCircle";
import ErrorIcon from "@material-ui/icons/Error";
import React from "react";

export function defaultSnack(isOpen, handleCloseFunction, positive, positiveText, negativeText) {
    return (
        <Snackbar
            anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
            }}
            open={isOpen}
            onClose={handleCloseFunction}
            autoHideDuration={3000}>
            {
                <SnackbarContent
                    style={{backgroundColor: positive ? 'rgb(67, 160, 71)' : 'rgb(211, 47, 47)'}}
                    aria-describedby="client-snackbar"
                    message={
                        <span id="client-snackbar" style={{
                            backgroundColor: positive ? 'rgb(67, 160, 71)' : 'rgb(211, 47, 47)',
                            display: 'flex',
                            alignItems: 'center'
                        }}>
                                {positive
                                    ? <CheckCircleIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>
                                    : <ErrorIcon style={{fontSize: 20, opacity: 0.9, marginRight: 5}}/>}
                            {positive ? positiveText : negativeText}
                            </span>
                    }
                />
            }
        </Snackbar>
    )
}