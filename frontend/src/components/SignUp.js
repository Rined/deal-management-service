import React, {useState} from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import {makeStyles} from '@material-ui/core/styles';
import request from "./request/request"

export default function SignUp() {
    const classes = useStyles();

    const [role, setRole] = useState('PROVIDER');
    const [globalError, setGlobalError] = useState('');
    const [showComplete, setComplete] = useState(false);
    const [errorTextUsername, setErrorTextUsername] = useState('');
    const [errorTextPassword, setErrorTextPassword] = useState('');
    const [errorTextEmail, setErrorTextEmail] = useState('');
    const [credential, setCredential] = useState({
        user: '',
        password: '',
        email: ''
    });

    const loginHandler = (event) => {
        setComplete(false);
        event.preventDefault();
        setGlobalError('');
        if (validate(credential.user, credential.password, credential.email)) {
            credential.roles = [role];
            const json = JSON.stringify(credential);
            console.log(credential);
            console.log(json);
            const options = {
                method: 'post',
                headers: {'Content-Type': 'application/json'},
                body: json
            };
            request('/auth/registration', options)
                .then(response => {
                    if (response.ok)
                        setComplete(true);
                })
                .catch(response => {
                    setGlobalError(response.json.description);
                });
        }
    };

    const validate = (username, password, email) => {
        let isValid = true;
        isValid &= checkField(username, setErrorTextUsername, 'Username is mandatory');
        isValid &= checkField(password, setErrorTextPassword, 'Password is mandatory');
        isValid &= checkField(email, setErrorTextEmail, 'Email is mandatory');
        return isValid;
    };

    const checkField = (val, showError, errorText) => {
        if (val.length === 0) {
            showError(errorText);
            return false;
        }
        return true;
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
                        Sign up
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
                            label="Email"
                            error={errorTextEmail.length !== 0}
                            helperText={errorTextEmail}
                            defaultValue={credential.email}
                            onChange={(event) => {
                                const eventValue = event.target.value;
                                clear(errorTextEmail, setErrorTextEmail);
                                setCredential(curState => {
                                    curState.email = eventValue;
                                    return curState;
                                })
                            }}/>
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
                        <Grid container justify="center">
                            <RadioGroup aria-label="role"
                                        name="role"
                                        value={role}
                                        onChange={(event) => {
                                            const eventValue = event.target.value;
                                            clear(errorTextPassword, setErrorTextPassword);
                                            setRole(eventValue);
                                        }}
                                        row>
                                <FormControlLabel
                                    value="PROVIDER"
                                    control={<Radio/>}
                                    label="Provider"
                                    labelPlacement="end"
                                />
                                <FormControlLabel
                                    value="CONSUMER"
                                    control={<Radio/>}
                                    label="Consumer"
                                    labelPlacement="end"
                                />
                            </RadioGroup>
                        </Grid>
                        {
                            (globalError && globalError.length !== 0)
                                ? <div style={{color: 'red'}}>{globalError}</div>
                                : ''
                        }
                        {
                            showComplete
                                ? <div style={{color: 'green'}}>Registration successfully completed</div>
                                : ''
                        }
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            color="primary"
                            className={classes.submit}
                            onClick={loginHandler}>
                            Sign Up
                        </Button>
                        <Grid container>
                            <Grid item>
                                <Link href="/" variant="body2">
                                    {"Already have account? Sign In"}
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