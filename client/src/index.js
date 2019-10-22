import React from 'react'
// import ReactDOM from 'react-dom'
import {render} from 'react-dom'
import Button from '@material-ui/core/Button';

import SignIn from './components/SignIn'

import {BrowserRouter as Router, Switch, Route} from "react-router-dom";

import Dashboard from './components/dashboard/Dashboard'

// import App from './components/App'
//
// ReactDOM.render(
//     <App/>,
//     document.getElementById('root')
// );

// function HelloWorld() {
//     return (
//         <Button variant="contained" color="primary">
//             Hello World
//         </Button>
//     )
// }

// render(
//     <HelloWorld/>,
//     document.getElementById('root')
// );

// render(<SignIn/>, document.getElementById('root'));

// render(<Dashboard/>, document.getElementById('root'));

render(
    <Router>
        <Switch>
            <Route exact path="/">
                <Dashboard/>
            </Route>
            <Route exact path="/auth">
                <SignIn/>
            </Route>
        </Switch>
    </Router>,
    document.getElementById('root'));