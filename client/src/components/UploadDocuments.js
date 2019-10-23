import React from 'react';
import {useTitleSetter} from "./contexts/TitleContext";

const TITLE = 'Upload documents';

export default function UploadDocuments() {
    const titleSetter = useTitleSetter();
    titleSetter(TITLE);
    return (
        <main>{TITLE}</main>
    );
}