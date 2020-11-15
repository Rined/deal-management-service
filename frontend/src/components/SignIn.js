import React, {useState} from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import {makeStyles} from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import {authenticate} from "./auth/AuthenticationManager";
import {useAuthSetter} from "./contexts/AuthContext";
import request, {AUTH_PATH} from "./request/request"
import {Validator, Validation} from "./utils/Validation";

export default function SignIn() {
    const classes = useStyles();
    const setAuth = useAuthSetter();

    const [validation, setValidationError] = useState({
        globalError: '',
        errorTextUsername: '',
        errorTextPassword: ''
    });

    const [credential, setCredential] = useState({
        user: '',
        password: ''
    });

    const setGlobalError = (globalError) => {
        setValidationError({
            ...validation,
            globalError
        });
    };

    const setErrorTextUsername = (errorTextUsername) => {
        setValidationError({
            ...validation,
            errorTextUsername
        });
    };

    const setErrorTextPassword = (errorTextPassword) => {
        setValidationError({
            ...validation,
            errorTextPassword
        });
    };

    const validator = new Validator(
        {
            user: new Validation(credential.user, setErrorTextUsername, 'Username is mandatory'),
            password: new Validation(credential.password, setErrorTextPassword, 'Password is mandatory')
        },
        setGlobalError
    );

    const loginHandler = (event) => {
        event.preventDefault();
        validator.clear();
        if (validator.validate()) {
            const options = {
                method: 'post',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(credential)
            };
            request(AUTH_PATH, '/login', options)
                .then(
                    (response) => setAuth(authenticate(response.json.token)),
                    (response) => validator.showError(response.json.description)
                );
        }
    };

    const usernameOnChange = (event) => {
        const eventValue = event.target.value;
        validator.validations.user.clear();
        setCredential(curState => {
            curState.user = eventValue;
            return curState;
        })
    };

    const passwordOnChange = (event) => {
        const eventValue = event.target.value;
        validator.validations.password.clear();
        setCredential(curState => {
            curState.password = eventValue;
            return curState;
        })
    };

    return (
        <React.Fragment>
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <div className={classes.paper}>
                    <Avatar className={classes.avatar}>
                        <LockOutlinedIcon/>
                    </Avatar>
                    <Typography component="h1" variant="h5">Sign in</Typography>
                    <form className={classes.form} noValidate>
                        <TextField required variant="outlined" margin="normal" fullWidth label="Username"
                                   error={validation.errorTextUsername.length !== 0}
                                   helperText={validation.errorTextUsername}
                                   onChange={usernameOnChange}
                                   autoFocus/>
                        <TextField required variant="outlined" margin="normal" fullWidth label="Password"
                                   type="password" error={validation.errorTextPassword.length !== 0}
                                   helperText={validation.errorTextPassword}
                                   onChange={passwordOnChange}
                        />
                        {
                            (validation.globalError && validation.globalError.length !== 0) &&
                            (<div style={{color: 'red'}}>{validation.globalError}</div>)

                        }
                        <Button type="submit" fullWidth variant="contained" color="primary"
                                className={classes.submit} onClick={loginHandler}>
                            Sign In
                        </Button>
                        <Grid container>
                            <Grid item>
                                <Link href="/signup" variant="body2">
                                    {"Don't have an account? Sign Up"}
                                </Link>
                            </Grid>
                        </Grid>
                    </form>
                </div>
            </Container>
        </React.Fragment>
    );
}

const useStyles = makeStyles(theme => ({
    '@global': {
        body: {
            backgroundColor: theme.palette.common.white,
        },
    },
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%',
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));