import React from 'react';
import {useTitleSetter} from "./contexts/TitleContext";

const TITLE = 'Send documents';

export default function SendDocuments() {
    const titleSetter = useTitleSetter();
    titleSetter(TITLE);
    return (
        <main>{TITLE}</main>
    );
}