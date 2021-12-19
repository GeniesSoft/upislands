import React, {useContext} from 'react';
import {NavLink} from 'react-router-dom';
import {Menu} from 'antd';
import {AuthContext} from 'context/AuthProvider';
import {AGENT_ACCOUNT_SETTINGS_PAGE, HOME_PAGE, LISTING_POSTS_PAGE} from 'settings/constant';

const MobileMenu = ({className}) => {
    // auth context
    const {loggedIn, logOut} = useContext(AuthContext);

    return (
        <Menu className={className}>
            <Menu.Item key="0">
                <NavLink exact to={HOME_PAGE}>
                    Home
                </NavLink>
            </Menu.Item>
            <Menu.Item key="1">
                <NavLink to={LISTING_POSTS_PAGE}>Listing</NavLink>
            </Menu.Item>
            <Menu.Item key="2">
                <NavLink to={{pathname: "https://www.boat-ed.com/floridarental/"}} target="_blank">
                    PWC License
                </NavLink>
            </Menu.Item>
            {loggedIn && (
                <Menu.Item key="3">
                    <NavLink to={AGENT_ACCOUNT_SETTINGS_PAGE}>Account Settings</NavLink>
                </Menu.Item>
            )}
            {loggedIn && (
                <Menu.Item key="4">
                    <button onClick={logOut}>Log Out</button>
                </Menu.Item>
            )}
        </Menu>
    );
};

export default MobileMenu;
