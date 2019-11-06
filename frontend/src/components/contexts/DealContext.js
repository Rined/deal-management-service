import React, {useState} from 'react';

const DealContext = React.createContext('');

export const DealProvider = ({children}) => {
    const [action, setAction] = useState({action: 'list'});
    return <DealContext.Provider value={{action, setAction}}>{children}</DealContext.Provider>
};

export const useActionSetter = () => React.useContext(DealContext).setAction;

export const useAction = () => React.useContext(DealContext).action;

