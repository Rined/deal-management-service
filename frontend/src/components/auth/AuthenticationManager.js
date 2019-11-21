import Authentication from "./Authentication";

export let authentication;

export const getAuthentication = () => {
    return authentication;
};

export const logout = () => {
    authentication = null;
    localStorage.removeItem('user');
};

const hasToken = () => {
    const token = getToken();
    return token !== null;
};

const getToken = () => {
    return localStorage.getItem('user');
};

export const authenticate = (token) => {
    const payload = token.split('.')[1];
    const payloadJson = window.atob(payload);
    authentication = new Authentication(JSON.parse(payloadJson), token);
};

if (hasToken()) {
    authenticate(getToken());
}