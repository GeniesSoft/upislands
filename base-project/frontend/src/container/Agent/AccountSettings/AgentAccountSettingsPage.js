import React from 'react';
import { Route, NavLink, Link } from 'react-router-dom';
import { Row, Col, Menu, Avatar } from 'antd';
import Container from 'components/UI/Container/Container.style';
import AgentPictureChangeForm from './AgentPictureChangeForm';
import AgentCreateOrUpdateForm from './AgentCreateOrUpdateForm';
import ChangePassWord from './ChangePassWordForm';
import {
  AGENT_IMAGE_EDIT_PAGE,
  AGENT_PASSWORD_CHANGE_PAGE,
  AGENT_PROFILE_PAGE,
} from 'settings/constant';
import AccountSettingWrapper, {
  AccountSidebar,
  AgentAvatar,
  SidebarMenuWrapper,
  ContentWrapper,
  AgentName,
  FromWrapper,
} from './AccountSettings.style';

const AccountSettingNavLink = (props) => {
  const { match } = props;
  return (
    <SidebarMenuWrapper>
      <Menu
        defaultSelectedKeys={['1']}
        defaultOpenKeys={['sub1']}
        mode="inline"
      >
        <Menu.Item key="1">
          <NavLink exact to={`${match.url}`}>
            Edit Profile
          </NavLink>
        </Menu.Item>
        <Menu.Item key="2">
          <NavLink to={`${match.url}${AGENT_IMAGE_EDIT_PAGE}`}>
            Change Photos
          </NavLink>
        </Menu.Item>
        <Menu.Item key="3">
          <NavLink to={`${match.url}${AGENT_PASSWORD_CHANGE_PAGE}`}>
            Change Password
          </NavLink>
        </Menu.Item>
      </Menu>
    </SidebarMenuWrapper>
  );
};

const AccountSettingRoute = (props) => {
  const { match } = props;
  return (
    <FromWrapper>
      <Route exact path={`${match.path}`} component={AgentCreateOrUpdateForm} />
      <Route
        path={`${match.path}${AGENT_IMAGE_EDIT_PAGE}`}
        component={AgentPictureChangeForm}
      />
      <Route
        path={`${match.path}${AGENT_PASSWORD_CHANGE_PAGE}`}
        component={ChangePassWord}
      />
    </FromWrapper>
  );
};

export default function AgentAccountSettingsPage(props) {
  return (
    <AccountSettingWrapper>
      <Container fullWidth={true}>
        <Row gutter={30}>
          <Col md={9} lg={6}>
            <AccountSidebar>
              <AgentAvatar>
                <Avatar
                  src="http://s3.amazonaws.com/redqteam.com/isomorphic-reloaded-image/profilepic.png"
                  alt="avatar"
                />
                <ContentWrapper>
                  <AgentName>Aziz Acharki Ahmedh</AgentName>
                  <Link to={AGENT_PROFILE_PAGE}>View profile</Link>
                </ContentWrapper>
              </AgentAvatar>
              <AccountSettingNavLink {...props} />
            </AccountSidebar>
          </Col>
          <Col md={15} lg={18}>
            <AccountSettingRoute {...props} />
          </Col>
        </Row>
      </Container>
    </AccountSettingWrapper>
  );
}
