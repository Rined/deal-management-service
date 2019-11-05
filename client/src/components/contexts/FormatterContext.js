import React, {useState} from 'react';

const FormatterContext = React.createContext('');

export const FormatterProvider = ({children}) => {
    const [action, setAction] = useState({action: 'list'});
    return <FormatterContext.Provider value={{action, setAction}}>{children}</FormatterContext.Provider>
};

export const useActionSetter = () => React.useContext(FormatterContext).setAction;

export const useAction = () => React.useContext(FormatterContext).action;

