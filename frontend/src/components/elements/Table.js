import React from "react";
import MaterialTable from 'material-table';

export function Table(props) {

    const updateStateOnAdd = (newData) => {
        const fields = props.data;
        fields.push(newData);
        props.stateCallback(fields);
    };

    const updateStateOnRemove = (oldData) => {
        const fields = props.data;
        fields.splice(fields.indexOf(oldData), 1);
        props.stateCallback(fields);
    };

    const updateStateOnUpdate = (newData, oldData) => {
        const fields = props.data;
        fields[fields.indexOf(oldData)] = newData;
        props.stateCallback(fields);
    };

    return (
        <MaterialTable
            title={props.title}
            columns={props.columns}
            data={props.data}
            editable={{
                onRowAdd: newData =>
                    new Promise(resolve => {
                        setTimeout(() => {
                            resolve();
                            updateStateOnAdd(newData);
                        }, 300);
                    }),
                onRowUpdate: (newData, oldData) =>
                    new Promise(resolve => {
                        setTimeout(() => {
                            if (oldData) {
                                resolve();
                                updateStateOnUpdate(newData, oldData);
                            }
                        }, 300);
                    }),
                onRowDelete: oldData =>
                    new Promise(resolve => {
                        setTimeout(() => {
                            resolve();
                            updateStateOnRemove(oldData);
                        }, 300);
                    }),
            }}
        />
    );
}