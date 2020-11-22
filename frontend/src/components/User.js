import React, {useEffect} from 'react';
import {useTitleSetter} from "./contexts/TitleContext";
import {useAuth} from "./contexts/AuthContext";
import request, {USER_PATH, PAYMENT_PATH} from "./request/request";
import CircularProgress from "@material-ui/core/CircularProgress";
import Grid from "@material-ui/core/Grid";
import Fab from "@material-ui/core/Fab";
import SaveIcon from "@material-ui/icons/Save";
import TextField from "@material-ui/core/TextField";
import Typography from "@material-ui/core/Typography";
import {DefaultSnack} from "./utils/DefaultSnack";

const TITLE = 'Profile';
export default function User() {
    const titleSetter = useTitleSetter();
    const authentication = useAuth();
    titleSetter(TITLE);

    const [properties, setProperties] = React.useState({
        process: false,
        open: false,
        positive: false,
    });

    const [user, setUser] = React.useState();

    useEffect(() => {
        const options = {
            headers: {
                'Authorization': 'Bearer ' + authentication.jwt
            }
        };
        request(USER_PATH, `/user/me`, options)
            .then(response => setUser(response.json));
    }, []);

    const setProcess = (process) => {
        setProperties(prevState => {
            return {...prevState, process: process};
        });
    };

    const handleClose = (open) => {
        setProperties(prevState => {
            return {...prevState, open: open};
        });
    };

    const enableControl = (isCorrect) => {
        setProperties({
            positive: isCorrect,
            open: true,
            process: false
        });
    };

    const save = () => {
        setProcess(true);
        const options = {
            method: 'put',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + authentication.jwt
            },
            body: JSON.stringify(user)
        };
        request(USER_PATH, `/user/me`, options)
            .then((response) => {
                setTimeout(() => enableControl(true), 500);
                console.log(response.status);
            })
            .catch((error) => {
                setTimeout(() => enableControl(false), 500);
                console.log(error);
            });
    };

    if (!user)
        return <React.Fragment/>;

    return (
        <main>
            <DefaultSnack positiveText="Profile has been saved successfully!"
                          negativeText="Create template error!"
                          handleCloseFunction={() => handleClose(false)}
                          positive={properties.positive}
                          isOpen={properties.open}/>
            <div style={{boxSizing: 'border-box', padding: 20, width: "100%"}}>
                <Grid container direction="row" justify="space-between" alignItems="baseline">
                    <div>
                        <Typography component="h1" display="inline" variant="h4" color="inherit" noWrap>
                            {authentication.name} profile
                        </Typography>
                        {properties.process && <CircularProgress size={30} style={{color: 'rgb(67, 160, 71)'}}/>}
                    </div>
                    <Grid item>
                        <Fab onClick={() => save()}
                             disabled={properties.process}
                             style={{backgroundColor: properties.process ? 'rgb(119, 136, 153)' : 'rgb(67, 160, 71)'}}
                             aria-label="add">
                            <SaveIcon style={{color: 'white'}}/>
                        </Fab>
                    </Grid>
                </Grid>
                <div style={{boxSizing: 'border-box', width: "30%"}}>
                    <TextField name="firstName" label="First name" margin="normal"
                               defaultValue={user.firstName} fullWidth style={{display: "block"}}
                               onChange={(event) => setUser(prevState => {
                                   if (event && event.target && event.target.value)
                                       prevState.firstName = event.target.value;
                                   return prevState;
                               })}/>
                    <TextField name="lastName" label="Second name" margin="normal"
                               defaultValue={user.lastName} fullWidth style={{display: "block"}}
                               onChange={(event) => setUser(prevState => {
                                   if (event && event.target && event.target.value)
                                       prevState.lastName = event.target.value;
                                   return prevState;
                               })}/>
                    <TextField name="city" label="City" margin="normal"
                               defaultValue={user.city} fullWidth style={{display: "block"}}
                               onChange={(event) => setUser(prevState => {
                                   if (event && event.target && event.target.value)
                                       prevState.city = event.target.value;
                                   return prevState;
                               })}/>
                    <TextField name="company" label="Company" margin="normal"
                               defaultValue={user.company} fullWidth style={{display: "block"}}
                               onChange={(event) => setUser(prevState => {
                                   if (event && event.target && event.target.value)
                                       prevState.company = event.target.value;
                                   return prevState;
                               })}/>
                    <TextField name="about" label="About" multiline variant="outlined" rows={7}
                               defaultValue={user.about} fullWidth style={{display: "block"}}
                               onChange={(event) => setUser(prevState => {
                                   if (event && event.target && event.target.value)
                                       prevState.about = event.target.value;
                                   return prevState;
                               })}/>
                </div>
            </div>
        </main>
    );
}