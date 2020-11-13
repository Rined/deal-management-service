import React from 'react';
import {logout} from "../auth/AuthenticationManager";

const origin = "";

function parseJSON(response) {
    return response.text().then((text) =>
        (text && text.length)
            ? new Promise((resolve) =>
                resolve({
                    status: response.status,
                    ok: response.ok,
                    json: JSON.parse(text)
                }))
            : Promise.resolve({
                status: response.status,
                ok: response.ok
            })
    );
}

const isForbidden = (response) => {
    return response.status === 403;
};

export default function request(url, options) {
    return new Promise((resolve, reject) => {
        fetch(`${origin}${url}`, options)
            .then(parseJSON)
            .then((response) => {
                if (response.ok)
                    return resolve(response);
                if (isForbidden(response)) {
                    logout();
                }
                return reject(response);
            })
    });
}