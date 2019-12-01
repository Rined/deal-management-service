import React, {useState} from 'react';

const DealProviderContext = React.createContext('');

export const DealProviderProvider = ({children}) => {
    const [action, setAction] = useState({action: 'list'});
    return <DealProviderContext.Provider value={{action, setAction}}>{children}</DealProviderContext.Provider>
};

export const useActionSetter = () => React.useContext(DealProviderContext).setAction;

export const useAction = () => React.useContext(DealProviderContext).action;

