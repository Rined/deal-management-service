import React from 'react'

const Header = (props) => (
    <h1>{props.title}</h1>
);

export default class App extends React.Component {

    constructor() {
        super();
        this.state = {documents: []};
    }

    componentDidMount() {
        fetch('/api/documents')
            .then(response => response.json())
            .then(documents => this.setState({documents}));
    }

    render() {
        return (
            <React.Fragment>
                <Header title={'Documents'}/>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Text</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.documents.map((document, i) => (
                            <tr key={i}>
                                <td>{document.id}</td>
                                <td>{document.text}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </React.Fragment>
        )
    }
};
