import React, {useState} from 'react';
import {getAuthentication, setLogoutCallback} from "../auth/AuthenticationManager";

const AuthContext = React.createContext('');

export const AuthProvider = ({children}) => {
    const [auth, setAuth] = useState(getAuthentication());
    setLogoutCallback(() => setAuth(null));
    return <AuthContext.Provider value={{auth, setAuth}}>{children}</AuthContext.Provider>
};

export const useAuthSetter = () => React.useContext(AuthContext).setAuth;

export const useAuth = () => React.useContext(AuthContext).auth;
