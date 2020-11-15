import React, {useEffect, useState} from 'react';
import request, {TEMPLATE_PATH} from "./../../../request/request"
import MutableTemplate from "./MutableTemplate";

const CURRENT_ACTION = 'edit';
export default function EditTemplate(props) {
    const [editState, setEditState] = useState();

    useEffect(() => {
        const options = {
            headers: {'Authorization': 'Bearer ' +props.auth.jwt}
        };
        request(TEMPLATE_PATH, `/templates/${props.param.id}`, options)
            .then(response => setEditState(response.json));
    }, []);

    const saveRequest = (foreignState) => {
        const options = {
            method: 'put',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' +props.auth.jwt
            },
            body: JSON.stringify(foreignState)
        };
        return request(TEMPLATE_PATH, `/templates/${props.param.id}`, options)
    };

    if(!editState){
        return <React.Fragment/>;
    }

    return (
        <MutableTemplate currentAction={CURRENT_ACTION}
                         title="Edit template"
                         fabPositiveText="Template edited successfully!"
                         fabNegativeText="Edit template error!"
                         state={editState}
                         saveRequest={saveRequest}/>
    );
}