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
import {authenticate, authentication} from "./auth/AuthenticationManager";
import {useAuthSetter} from "./contexts/AuthContext";

export default function SignIn() {
    const classes = useStyles();
    const setAuth = useAuthSetter();

    const [globalError, setGlobalError] = useState('');
    const [errorTextUsername, setErrorTextUsername] = useState('');
    const [errorTextPassword, setErrorTextPassword] = useState('');
    const [credential, setCredential] = useState({
        user: '',
        password: ''
    });


    function parseJSON(response) {
        return new Promise((resolve) => response.json()
            .then((json) => resolve({
                status: response.status,
                ok: response.ok,
                json,
            })));
    }

    function request(url, options) {
        return new Promise((resolve, reject) => {
            fetch(url, options)
                .then(parseJSON)
                .then((response) => {
                    if (response.ok) {
                        return resolve(response);
                    }
                    return reject(response);
                });
        });
    }

    const loginHandler = (event) => {
        event.preventDefault();
        setGlobalError('');
        console.log(credential);
        if (validate(credential.user, credential.password)) {
            const options = {
                method: 'post',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(credential)
            };
            request('http://localhost:8081/login', options)
                .then(response => {
                    authenticate(response.json.token);
                    setAuth(authentication);
                })
                .catch(response => {
                    setGlobalError(response.json.description);
                });
        }
    };
    const validate = (username, password) => {
        let isValid = true;
        if (username.length === 0) {
            setErrorTextUsername('Username is mandatory');
            isValid = false;
        }
        if (password.length === 0) {
            setErrorTextPassword('Password is mandatory');
            isValid = false;
        }
        return isValid;
    };

    const clear = (text, clearFunc) => {
        if (text.length !== 0) {
            clearFunc('');
        }
    };


    return (
        <React.Fragment>
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <div className={classes.paper}>
                    <Avatar className={classes.avatar}>
                        <LockOutlinedIcon/>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign in
                    </Typography>
                    <form className={classes.form} noValidate>
                        <TextField
                            required
                            variant="outlined"
                            margin="normal"
                            fullWidth
                            label="Username"
                            error={errorTextUsername.length !== 0}
                            helperText={errorTextUsername}
                            defaultValue={credential.user}
                            onChange={(event) => {
                                const eventValue = event.target.value;
                                clear(errorTextUsername, setErrorTextUsername);
                                setCredential(curState => {
                                    curState.user = eventValue;
                                    return curState;
                                })
                            }}
                            autoFocus/>
                        <TextField
                            required
                            variant="outlined"
                            margin="normal"
                            fullWidth
                            label="Password"
                            type="password"
                            error={errorTextPassword.length !== 0}
                            helperText={errorTextPassword}
                            defaultValue={credential.password}
                            onChange={(event) => {
                                const eventValue = event.target.value;
                                clear(errorTextPassword, setErrorTextPassword);
                                setCredential(curState => {
                                    curState.password = eventValue;
                                    return curState;
                                })
                            }}
                            autoComplete="current-password"/>
                        {
                            globalError.length !== 0
                                ? <div style={{color: 'red'}}>{globalError}</div>
                                : ''
                        }
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            color="primary"
                            className={classes.submit}
                            onClick={loginHandler}>
                            Sign In
                        </Button>
                        <Grid container>
                            <Grid item>
                                <Link href="#" variant="body2">
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