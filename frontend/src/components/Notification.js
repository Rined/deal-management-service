import React from 'react';
import {useTitleSetter} from "./contexts/TitleContext";

const TITLE = 'Notification';

export default function Notification() {
    const titleSetter = useTitleSetter();
    titleSetter(TITLE);
    return (
        <main>{TITLE}</main>
    );
}