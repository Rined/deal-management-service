import React, {useState} from 'react';

const DealConsumerContext = React.createContext('');

export const DealConsumerProvider = ({children}) => {
    const [action, setAction] = useState({action: 'list'});
    return <DealConsumerContext.Provider value={{action, setAction}}>{children}</DealConsumerContext.Provider>
};

export const useActionSetter = () => React.useContext(DealConsumerContext).setAction;

export const useAction = () => React.useContext(DealConsumerContext).action;

