import React from 'react';
import {useTitleSetter} from "./contexts/TitleContext";

const TITLE = 'Dashboard';

export default function Dashboard() {
    const titleSetter = useTitleSetter();
    titleSetter(TITLE);
    return (
        <main>{TITLE}</main>
    );
}