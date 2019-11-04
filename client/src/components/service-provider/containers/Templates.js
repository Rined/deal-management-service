import React from 'react';
import {useTitleSetter} from "./../../contexts/TitleContext";

const TITLE = 'Templates';

export default function Templates() {
    const titleSetter = useTitleSetter();
    titleSetter(TITLE);
    return (
        <main>
            {TITLE}
        </main>
    );
}
