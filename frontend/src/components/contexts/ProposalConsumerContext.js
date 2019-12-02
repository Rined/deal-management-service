import React, {useState} from 'react';

const ProposalConsumerContext = React.createContext('');

export const ProposalConsumerProvider = ({children}) => {
    const [action, setAction] = useState({action: 'list'});
    return <ProposalConsumerContext.Provider value={{action, setAction}}>{children}</ProposalConsumerContext.Provider>
};

export const useActionSetter = () => React.useContext(ProposalConsumerContext).setAction;

export const useAction = () => React.useContext(ProposalConsumerContext).action;

