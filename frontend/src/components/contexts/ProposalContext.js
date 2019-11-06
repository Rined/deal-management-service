import React, {useState} from 'react';

const ProposalContext = React.createContext('');

export const ProposalProvider = ({children}) => {
    const [action, setAction] = useState({action: 'list'});
    return <ProposalContext.Provider value={{action, setAction}}>{children}</ProposalContext.Provider>
};

export const useActionSetter = () => React.useContext(ProposalContext).setAction;

export const useAction = () => React.useContext(ProposalContext).action;

