import React, {useState} from 'react';
import request, {TEMPLATE_PATH} from "./../../../request/request";
import {generateId} from "../../../utils/Utils"
import MutableTemplate from "./MutableTemplate";

const CURRENT_ACTION = 'add';
export default function AddTemplate(props) {

    const [addState] = useState({
        id: generateId(),
        name: "",
        fields: [],
        format: ""
    });

    const saveRequest = (foreignState) => {
        const options = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' +props.auth.jwt
            },
            body: JSON.stringify(foreignState)
        };
        return request(TEMPLATE_PATH, `/templates`, options);
    };

    return (
        <MutableTemplate currentAction={CURRENT_ACTION}
                         title="Create template"
                         fabPositiveText="Template created successfully!"
                         fabNegativeText="Create template error!"
                         state={addState}
                         saveRequest={saveRequest}/>
    );
}