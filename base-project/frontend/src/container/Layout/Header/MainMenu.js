import React from 'react';
import {NavLink} from 'react-router-dom';
import {Menu} from 'antd';

import {AGENT_PROFILE_PAGE, HOME_PAGE, LISTING_POSTS_PAGE} from 'settings/constant';

const MainMenu = ({className}) => {
    return (
        <Menu className={className}>
            <Menu.Item key="0">
                <NavLink exact to={`${HOME_PAGE}`}>
                    Home
                </NavLink>
            </Menu.Item>
            <Menu.Item key="1">
                <NavLink to={`${LISTING_POSTS_PAGE}`}>Trips</NavLink>
            </Menu.Item>
            <Menu.Item key="1">
                <NavLink to={`${AGENT_PROFILE_PAGE}`}>Become a Host</NavLink>
            </Menu.Item>
            {/*<Menu.Item key="2">*/}
            {/*    <NavLink to={`${AGENT_PROFILE_PAGE}`}>Agent</NavLink>*/}
            {/*</Menu.Item>*/}
            <Menu.Item key="3">
                <NavLink to={{pathname: "https://www.boat-ed.com/floridarental/"}} target="_blank">
                    PWC License
                </NavLink>
            </Menu.Item>
        </Menu>
    );
};

export default MainMenu;
