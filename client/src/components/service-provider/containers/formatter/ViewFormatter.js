import React, {useEffect, useState} from 'react';


export default function ViewFormatter(props) {
    const [formatter, setFormatter] = useState();

    useEffect(() => {
        fetch('http://localhost:8080/formatters/' + props.param.id)
            .then(response => response.json())
            .then(formatter => setFormatter(formatter));
    }, []);

    return (
        <React.Fragment>
            <h1>{formatter && formatter.name}</h1>
            <p>{formatter && formatter.type}</p>
            <p>{formatter && formatter.value}</p>
        </React.Fragment>
    );

}