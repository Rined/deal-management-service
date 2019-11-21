import React, {useState} from 'react';
import {Role} from "../auth/Role";

const AuthContext = React.createContext('');

export const AuthProvider = ({children}) => {
    const [auth, setAuth] = useState({
        isAuth: false,
        roles: [Role.None]
    });
    return <AuthContext.Provider value={{auth, setAuth}}>{children}</AuthContext.Provider>
};

export const useAuthSetter = () => React.useContext(AuthContext).setAuth;

export const useAuth = () => React.useContext(AuthContext).auth;
