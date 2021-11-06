import './App.css';
import {ThemeProvider} from "styled-components";
import theme from "./themes/default.theme";
import GlobalStyles from "./themes/global.style";
import {BrowserRouter} from "react-router-dom";
import AuthProvider from "./context/AuthProvider";
import Routes from "./router";
import React from "react";

function App() {

    return (
        <ThemeProvider theme={theme}>
            <GlobalStyles/>
            <BrowserRouter>
                <AuthProvider>
                    <Routes/>
                </AuthProvider>
            </BrowserRouter>
        </ThemeProvider>
    );

}

export default App;
