import React, {useEffect, useState} from 'react';
// import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';

export default function AddProposal() {
    const [template, setTemplate] = useState({});
    const [templates, setTemplates] = useState();

    useEffect(() => {
        fetch('http://localhost:8080/templates/brief')
            .then(response => response.json())
            .then(templates => setTemplates(templates));
    }, []);

    const handleChange = event => {
        setTemplate(event.target.value);
        console.log(event.target.value);
    };

    return (
        <div>
            <TextField
                id="outlined-select-currency"
                select
                label="Template"
                style={{width: 500}}
                value={template}
                onChange={handleChange}
                helperText="Please select template"
                margin="normal"
                variant="outlined">
                {templates
                    ? templates.map((template) => (
                        <MenuItem key={template.id} value={template.id}>
                            {template.name}
                        </MenuItem>

                    )) : <MenuItem/>}
            </TextField>
        </div>
    );
}

