import React from 'react';
import {useTitleSetter} from "./../../contexts/TitleContext";

const TITLE = 'Provider Dashboard';

export default function ProviderDashboard() {
    const titleSetter = useTitleSetter();
    titleSetter(TITLE);
    return (
        <main>{TITLE}</main>
    );
}