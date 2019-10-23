import React from 'react'
import {render} from 'react-dom'
import App from './components/App'
import {TitleProvider} from "./components/contexts/TitleContext";
import {OpenMenuProvider} from "./components/contexts/OpenMenuContext";

render(
    <TitleProvider>
        <OpenMenuProvider>
            <App/>
        </OpenMenuProvider>
    </TitleProvider>,
    document.getElementById('root')
);