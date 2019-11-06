import React, {useState} from 'react';

const TitleContext = React.createContext('');

export const TitleProvider = ({children}) => {
    const [title, setTitle] = useState('');
    return <TitleContext.Provider value={{title, setTitle}}>{children}</TitleContext.Provider>
};

export const useTitleSetter = () => React.useContext(TitleContext).setTitle;

export const useTitle = () => React.useContext(TitleContext).title;

