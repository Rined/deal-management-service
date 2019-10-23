import React, {useState} from 'react';

const OpenMenuContext = React.createContext('');

export const OpenMenuProvider = ({children}) => {
    const [open, setOpen] = useState(true);
    return <OpenMenuContext.Provider value={{open, setOpen}}>{children}</OpenMenuContext.Provider>
};

export const useOpen = () => React.useContext(OpenMenuContext).setOpen;

export const useIsOpen = () => React.useContext(OpenMenuContext).open;

