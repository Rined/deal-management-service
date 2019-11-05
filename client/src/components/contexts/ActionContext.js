import React, {useState} from 'react';

const ActionContext = React.createContext('');

export const ActionProvider = ({children}) => {
    const [action, setAction] = useState({action: 'list'});
    return <ActionContext.Provider value={{action, setAction}}>{children}</ActionContext.Provider>
};

export const useActionSetter = () => React.useContext(ActionContext).setAction;

export const useAction = () => React.useContext(ActionContext).action;

