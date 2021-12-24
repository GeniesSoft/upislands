import React, {useState, useEffect} from 'react';
import {useHistory} from 'react-router-dom';
import { signin, getCurrentUser } from 'service/auth/AuthApi';
import {ACCESS_TOKEN, USER_ID} from 'constants/index';
import Alert from 'react-s-alert';

export const AuthContext = React.createContext();

const fakeUserData = {
    id: 1,
    name: 'Mert Yüksek',
    avatar: '',
    roles: ['USER', 'ADMIN'],
};

const AuthProvider = (props) => {
    let history = useHistory();
    const [loggedIn, setLoggedIn] = useState(false);
    const [user, setUser] = useState({});

    useEffect(()=>{
        getCurrentUser()
            .then(response => {
                setLoggedIn(true)
            })
            .catch(error => {
                setLoggedIn(false)
            });    
    },[])

    const signIn = (loginRequest) => {
        console.log("signIn ######")
        signin(loginRequest)
        .then(response => {
            localStorage.setItem(ACCESS_TOKEN, response.accessToken);
            Alert.success("You're successfully logged in!");
            setLoggedIn(true);
            history.push(`/`);
        }).catch(error => {
            Alert.error((error && error.message) || 'Oops! Something went wrong. Please try again!');
        });
    };

    const signUp = (params) => {
        console.log(params, 'sign up form Props');
        setUser(fakeUserData);
        setLoggedIn(true);
        history.push(`/`);
    };

    const logOut = () => {
        setUser(null);
        setLoggedIn(false);
        localStorage.removeItem("accessToken")
    };

    return (
        <AuthContext.Provider
            value={{
                loggedIn,
                logOut,
                signIn,
                signUp,
                user,
            }}
        >
            <>{props.children}</>
        </AuthContext.Provider>
    );
};

export default AuthProvider;
