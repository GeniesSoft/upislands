import React from 'react';
import {NavLink} from 'react-router-dom';
import {Menu} from 'antd';

import {AGENT_PROFILE_PAGE, HOME_PAGE, LISTING_POSTS_PAGE, PRIVACY_PAGE,} from 'settings/constant';

const FooterMenu = () => {

    const openPageHandler = () => {

    }

    return (
        <Menu>
            <Menu.Item key="0">
                <NavLink to={`${HOME_PAGE}`}>Trips</NavLink>
            </Menu.Item>
            <Menu.Item key="1">
                <NavLink to={`${LISTING_POSTS_PAGE}`}>Trips</NavLink>
            </Menu.Item>
            <Menu.Item key="2">
                <NavLink to={{pathname: "https://www.boat-ed.com/floridarental/"}} target="_blank">
                    PWC License
                </NavLink>
            </Menu.Item>
            <Menu.Item key="3">
                <NavLink to={`${PRIVACY_PAGE}`}>Privacy</NavLink>
            </Menu.Item>
            {/*<Menu.Item key="4">*/}
            {/*    <NavLink to={`${AGENT_PROFILE_PAGE}`}>Agent</NavLink>*/}
            {/*</Menu.Item>*/}
        </Menu>
    );
};

export default FooterMenu;
