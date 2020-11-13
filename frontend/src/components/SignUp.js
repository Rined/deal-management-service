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
import {Validator, Validation} from "./utils/Validation";

export default function SignUp() {
    const classes = useStyles();

    const [validation, setValidationError] = useState({
        globalError: '',
        errorTextUsername: '',
        errorTextPassword: '',
        errorTextEmail: '',
        showComplete: false
    });

    const [credential, setCredential] = useState({
        user: '',
        password: '',
        email: '',
        role: 'PROVIDER'
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
            errorTextUsername,
            showComplete: false
        });
    };

    const setErrorTextPassword = (errorTextPassword) => {
        setValidationError({
            ...validation,
            errorTextPassword,
            showComplete: false
        });
    };

    const setErrorTextEmail = (errorTextEmail) => {
        setValidationError({
            ...validation,
            errorTextEmail,
            showComplete: false
        });
    };

    const setComplete = (showComplete) => {
        setValidationError({
            ...validation,
            showComplete
        });
    };

    const validator = new Validator(
        {
            user: new Validation(credential.user, setErrorTextUsername, 'Username is mandatory'),
            password: new Validation(credential.password, setErrorTextPassword, 'Password is mandatory'),
            email: new Validation(credential.email, setErrorTextEmail, 'Email is mandatory')
        },
        setGlobalError
    );

    const loginHandler = (event) => {
        event.preventDefault();
        validator.clear(); // todo не удаляется globalError
        setComplete(false);
        if (validator.validate()) {
            const options = {
                method: 'post',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(credential)
            };
            request('/auth/registration', options)
                .then(response => { response.ok && setComplete(true)})
                .catch(response => {validator.showError(response.json.description)});
        }
    };

    let usernameOnChange = (event) => {
        const user = event.target.value;
        validator.validations.user.clear();
        setCredential({
            ...credential,
            user
        });
    };

    let passwordOnChange = (event) => {
        const password = event.target.value;
        validator.validations.password.clear();
        setCredential({
            ...credential,
            password
        });
    };

    let emailOnChange = (event) => {
        const email = event.target.value;
        validator.validations.email.clear();
        setCredential({
            ...credential,
            email
        })
    };

    let roleOnChange = (event) => {
        const role = event.target.value;
        setCredential({
            ...credential,
            role
        });
    };

    return (
        <React.Fragment>
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <div className={classes.paper}>
                    <Avatar className={classes.avatar}>
                        <LockOutlinedIcon/>
                    </Avatar>
                    <Typography component="h1" variant="h5">Sign up</Typography>
                    <form className={classes.form} noValidate>
                        <TextField required variant="outlined" margin="normal" fullWidth label="Username"
                                   error={validation.errorTextUsername.length !== 0}
                                   helperText={validation.errorTextUsername}
                                   onChange={usernameOnChange}
                                   autoFocus/>
                        <TextField required variant="outlined" margin="normal" fullWidth label="Email"
                                   error={validation.errorTextEmail.length !== 0}
                                   helperText={validation.errorTextEmail}
                                   defaultValue={credential.email}
                                   onChange={emailOnChange}/>
                        <TextField required variant="outlined" margin="normal" fullWidth label="Password"
                                   type="password"
                                   error={validation.errorTextPassword.length !== 0}
                                   helperText={validation.errorTextPassword} defaultValue={credential.password}
                                   onChange={passwordOnChange}/>
                        <Grid container justify="center">
                            <RadioGroup aria-label="role" name="role"
                                        value={credential.role}
                                        onChange={roleOnChange}
                                        row>
                                <FormControlLabel value="PROVIDER" control={<Radio/>} label="Provider"
                                                  labelPlacement="end"/>
                                <FormControlLabel value="CONSUMER" label="Consumer" control={<Radio/>}
                                                  labelPlacement="end"/>
                            </RadioGroup>
                        </Grid>
                        {
                            (validation.globalError &&
                                (<div style={{color: 'red'}}>{validation.globalError}</div>))
                        }
                        {validation.showComplete &&
                        (<div style={{color: 'green'}}>Registration successfully completed</div>)}
                        <Button type="submit" fullWidth variant="contained" color="primary" className={classes.submit}
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