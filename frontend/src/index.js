import React from 'react'
import {render} from 'react-dom'
import App from './components/App'
import {AuthProvider} from "./components/contexts/AuthContext";

render(
    <AuthProvider>
        <App/>
    </AuthProvider>,
    document.getElementById('root')
);