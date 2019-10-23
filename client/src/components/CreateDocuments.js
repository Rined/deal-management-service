import React from 'react';
import {useTitleSetter} from "./contexts/TitleContext";

const TITLE = 'Create documents';

export default function CreateDocuments() {
    const titleSetter = useTitleSetter();
    titleSetter(TITLE);
    return (
        <main>{TITLE}</main>
    );
}