import React, {useEffect, useState} from 'react';
import ReactHtmlParser from 'react-html-parser';
import Fab from '@material-ui/core/Fab';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import {useActionSetter} from "../../../contexts/ActionContext";
import MaterialTable from 'material-table';
import MdEditor from 'react-markdown-editor-lite'
import MarkdownIt from 'markdown-it'

const CURRENT_ACTION = 'view';

export default function ViewTemplate(props) {
    const mdParser = new MarkdownIt(/* Markdown-it options */);

    const [state, setState] = useState();

    const setAction = useActionSetter();

    const handleBack = () => {
        setAction({
            action: 'list',
            previousAction: CURRENT_ACTION
        });
    };

    useEffect(() => {
        fetch('http://localhost:8080/templates/' + props.param.id)
            .then(response => response.json())
            .then(template => setState(template));
    }, []);

    if (!state)
        return (
            <React.Fragment>
                {back()}
            </React.Fragment>
        );

    return (
        <React.Fragment>
            <h1>{back()} {state.name}</h1>
            <TemplateTable data={state.fields}/>
            <MdEditor
                value={state.format}
                renderHTML={(text) => mdParser.render(text)}
            />
            <p>{ReactHtmlParser(state.format)}</p>

        </React.Fragment>
    );

    function back() {
        return (
            <Fab onClick={() => handleBack()} color="primary" aria-label="add">
                <ArrowBackIosIcon/>
            </Fab>
        );
    }
}

function TemplateTable({data}) {
    const [state, setState] = React.useState({
        columns: [
            {title: 'Field', field: 'name'},
            {title: 'Description', field: 'description'}
        ],
        data: data,
    });

    return (
        <MaterialTable
            title="Template fields"
            columns={state.columns}
            data={state.data}
            editable={{
                onRowAdd: newData =>
                    new Promise(resolve => {
                        setTimeout(() => {
                            resolve();
                            setState(prevState => {
                                const data = [...prevState.data];
                                data.push(newData);
                                return {...prevState, data};
                            });
                        }, 600);
                    }),
                onRowUpdate: (newData, oldData) =>
                    new Promise(resolve => {
                        setTimeout(() => {
                            resolve();
                            if (oldData) {
                                setState(prevState => {
                                    const data = [...prevState.data];
                                    data[data.indexOf(oldData)] = newData;
                                    return {...prevState, data};
                                });
                            }
                        }, 600);
                    }),
                onRowDelete: oldData =>
                    new Promise(resolve => {
                        setTimeout(() => {
                            resolve();
                            setState(prevState => {
                                const data = [...prevState.data];
                                data.splice(data.indexOf(oldData), 1);
                                return {...prevState, data};
                            });
                        }, 600);
                    }),
            }}
        />
    );
}