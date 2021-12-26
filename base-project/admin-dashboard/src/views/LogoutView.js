import {Navigate} from 'react-router-dom';
import {useEffect} from "react";
import AuthApi from "../service/auth/AuthApi";

const LogoutView = () => {

    useEffect(() => {
        AuthApi.logout();
    }, []);

    return <Navigate to={"/login"} />

}

export default LogoutView;