import './App.css';
import NavBar from "./components/NavBar";
import {routes} from "./routes";
import {BrowserRouter, NavLink, Route, Routes} from "react-router-dom";
import {List, ListItem, ListItemIcon, ListItemText} from "@mui/material";
import * as React from "react";

function App() {

    const navigation = (
        <List>
            {routes.map((route) => (
                <NavLink
                    to={route.path}
                    style={
                        {
                            color: "inherit",
                            textDecoration: "none"
                        }
                    }
                    key={route.id}
                >
                    <ListItem>
                        <ListItemIcon>
                            {route.icon}
                        </ListItemIcon>
                        <ListItemText primary={route.name}/>
                    </ListItem>
                </NavLink>
            ))}
        </List>
    )

    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    {routes.map(route =>
                        <Route key={route.id} path={route.path} element={
                            <NavBar nav={navigation}>
                                {route.component}
                            </NavBar>
                        }/>
                    )}
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
