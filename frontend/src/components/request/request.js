import React from 'react';
import {logout} from "../auth/AuthenticationManager";

const API_VERSION = '/api/v1';

export const AUTH_PATH = '/auth';
export const DEAL_PATH = '/deal';
export const PROPOSAL_PATH = '/proposal';
export const TEMPLATE_PATH = '/template';
export const USER_PATH = '/users';
export const PAYMENT_PATH = '/payment';

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
    return response.status === 403 || response.status === 401;
};

export default function request(service, url, options) {
    return new Promise((resolve, reject) => {
        fetch(`${service}${API_VERSION}${url}`, options)
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