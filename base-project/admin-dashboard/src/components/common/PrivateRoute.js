import React from 'react';
import AuthApi from "../../service/auth/AuthApi";
import {Navigate, Outlet } from 'react-router-dom';

const PrivateRoute = (props) => {
    return (AuthApi.isUserLoggedIn()) ? <Outlet /> : <Navigate to={"/login"} />
};

export default PrivateRoute;