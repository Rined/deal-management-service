import Authentication from "./Authentication";

export const authentication = {
    isAuth: false,
    auth: null
};

export const logout = () => {
    authentication.isAuth = false;
    authentication.auth = null;
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
    authentication.isAuth = true;
    authentication.auth = new Authentication(JSON.parse(payloadJson), token);
};

if (hasToken()) {
    authenticate(getToken());
}