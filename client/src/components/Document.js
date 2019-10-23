import React from 'react';
import {useTitleSetter} from "./contexts/TitleContext";

const TITLE = 'Documents';

export default function Document() {
    const titleSetter = useTitleSetter();
    titleSetter(TITLE);
    return (
        <main>{TITLE}</main>
    );
}