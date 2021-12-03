import './App.css';
import {ThemeProvider} from "styled-components";
import theme from "./themes/default.theme";
import GlobalStyles from "./themes/global.style";
import {BrowserRouter} from "react-router-dom";
import AuthProvider from "./context/AuthProvider";
import Routes from "./router";
import React from "react";
import TestView from "./service/test/TestView";
import Alert from 'react-s-alert';
import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';

function App() {

    return (
        // <TestView />
        <ThemeProvider theme={theme}>
            <GlobalStyles/>
            <BrowserRouter>
                <AuthProvider>
                    <Routes/>
                    <Alert stack={{limit: 3}} 
                        timeout = {4000}
                        position='top-right' effect='slide' offset={65} />
                </AuthProvider>
            </BrowserRouter>
        </ThemeProvider>
    );

}

export default App;
