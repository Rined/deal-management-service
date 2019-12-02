import React, {useState} from 'react';

const TemplateContext = React.createContext('');

export const TemplateProvider = ({children}) => {
    const [action, setAction] = useState({action: 'list'});
    return <TemplateContext.Provider value={{action, setAction}}>{children}</TemplateContext.Provider>
};

export const useActionSetter = () => React.useContext(TemplateContext).setAction;

export const useAction = () => React.useContext(TemplateContext).action;

