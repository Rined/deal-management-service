import React, {useState} from 'react';

const AuthContext = React.createContext('');

export const AuthProvider = ({children}) => {
    const [auth, setAuth] = useState({
        isAuth: false,
        role: "provider"
    });
    return <AuthContext.Provider value={{auth, setAuth}}>{children}</AuthContext.Provider>
};

export const useAuthSetter = () => React.useContext(AuthContext).setAuth;

export const useAuth = () => React.useContext(AuthContext).auth;
