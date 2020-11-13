import Authentication from "./Authentication";

let logoutCallback;
export let authentication;

export const getAuthentication = () => {
    return authentication;
};

export const setLogoutCallback = (clearAuthentication) => {
    logoutCallback = clearAuthentication;
};

export const logout = () => {
    authentication = null;
    localStorage.removeItem('token');
    if(logoutCallback)
        logoutCallback();
};

const hasToken = () => {
    const token = getToken();
    return token !== null;
};

const getToken = () => {
    return localStorage.getItem('token');
};

export const authenticate = (token) => {
    const payload = token.split('.')[1];
    const payloadJson = window.atob(payload);
    authentication = new Authentication(JSON.parse(payloadJson), token);
    localStorage.setItem('token', token);
    return authentication;
};

if (hasToken()) {
    authenticate(getToken());
}