import React, {useState} from 'react';
import {useHistory} from 'react-router-dom';
import { signin, getCurrentUser } from 'service/auth/AuthApi';
import { ACCESS_TOKEN } from 'constants/index';
import Alert from 'react-s-alert';

export const AuthContext = React.createContext();

const fakeUserData = {
    id: 1,
    name: 'Jhon Doe',
    avatar:
        'https://upisland-bucket.s3.eu-central-1.amazonaws.com/1-hande.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20211121T095720Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3573&X-Amz-Credential=%2F20211121%2Feu-central-1%2Fs3%2Faws4_request&X-Amz-Signature=6bdb9f015efeb596bf7617f0034f6692730279bec1686c9d5220b0b8a7a2a152',
    roles: ['USER', 'ADMIN'],
};

const AuthProvider = (props) => {
    let history = useHistory();
    const [loggedIn, setLoggedIn] = useState(false);
    const [user, setUser] = useState({});

    const signIn = (loginRequest) => {
        
        console.log(loginRequest, 'sign in form Props');
        
        signin(loginRequest)
        .then(response => {
            localStorage.setItem(ACCESS_TOKEN, response.accessToken);
            Alert.success("You're successfully logged in!");
            setLoggedIn(true);

            getCurrentUser()
            .then(response => {
                //setUser(response)
                setUser(fakeUserData)
            })
            .catch(error => {
                Alert.error((error && error.message) || 'Oops! Something went wrong. Failed to load user data.');
            })

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
