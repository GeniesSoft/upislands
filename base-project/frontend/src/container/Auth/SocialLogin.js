import React, {useContext, useState, useEffect} from 'react';
import {Redirect} from 'react-router-dom';
import {Button, Col, Row} from 'antd';
import {AuthContext} from 'context/AuthProvider';
import { GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL, ACCESS_TOKEN } from '../../constants/index'

const SocialLogin = () => {
    const {signUp, loggedIn} = useContext(AuthContext);
    const [state, setState] = useState({
        facebook: false,
        github: false,
        firebase: false,
        google: false,
    });

    const handleSocialAuth = (key) => {
        if(key == 'facebook'){
            window.location.href = FACEBOOK_AUTH_URL;
        }
        if(key == 'google'){
            window.location.href = GOOGLE_AUTH_URL;
        }
        setState({
            ...state,
            [key]: true,
        });
        setTimeout(() => {
            setState({
                ...state,
                [key]: false,
            });
            signUp({});
        }, 600);
    };
    if (loggedIn) {
        return <Redirect to={{pathname: '/'}}/>;
    }

    return (
        <div>
            <Row gutter={16}>
                <Col span={12}>
                    <Button
                        loading={state.facebook}
                        className="facebook-btn"
                        type="primary"
                        style={{width: '100%', marginBottom: 16}}
                        size="large"
                        onClick={() => handleSocialAuth('facebook')}
                    >
                        Facebook
                    </Button>
                </Col>
                <Col span={12}>
                <Button
                        loading={state.google}
                        className="google-btn"
                        type="primary"
                        style={{width: '100%', marginBottom: 16}}
                        size="large"
                        onClick={() => handleSocialAuth('google')}
                    >
                        Google+
                    </Button>
                </Col>
            </Row>
        </div>
    );
};

export default SocialLogin;
