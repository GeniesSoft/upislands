import React, {Fragment, useContext, useEffect, useState} from 'react';
import {Link, NavLink, Route} from 'react-router-dom';
import isEmpty from 'lodash/isEmpty';
import {IoIosAdd, IoLogoFacebook, IoLogoInstagram, IoLogoTwitter,} from 'react-icons/io';
import {Menu, Popover} from 'antd';
import Container from 'components/UI/Container/Container';
import Image from 'components/UI/Image/Image';
import Heading from 'components/UI/Heading/Heading';
import Text from 'components/UI/Text/Text';
import {ProfilePicLoader} from 'components/UI/ContentLoader/ContentLoader';
import Loader from 'components/Loader/Loader';
import AuthProvider, {AuthContext} from 'context/AuthProvider';
import AgentItemLists from './AgentItemLists';
import AgentFavItemLists from './AgentFavItemLists';
import AgentContact from './AgentContact';
import useDataApi from 'library/hooks/useDataApi'
import {getCurrentUser} from "../../../service/auth/AuthApi";
import {ADD_HOTEL_PAGE, AGENT_PROFILE_CONTACT, AGENT_PROFILE_FAVOURITE,} from 'settings/constant';
import AgentDetailsPage, {
    BannerSection,
    NavigationArea,
    ProfileImage,
    ProfileInformation,
    ProfileInformationArea,
    SocialAccount,
    UserInfoArea,
} from './AgentDetails.style';

const ProfileNavigation = (props) => {
    const {match, className} = props;
    const {loggedIn} = useContext(AuthContext);
    return (
        <NavigationArea>
            <Container fluid={true}>
                <Menu className={className}>
                    <Menu.Item key="0">
                        <NavLink exact to={`${match.url}`}>
                            Listing
                        </NavLink>
                    </Menu.Item>
                    <Menu.Item key="1">
                        <NavLink to={`${match.url}${AGENT_PROFILE_FAVOURITE}`}>
                            Favourite
                        </NavLink>
                    </Menu.Item>
                    <Menu.Item key="2">
                        <NavLink to={`${match.url}${AGENT_PROFILE_CONTACT}`}>
                            Contact
                        </NavLink>
                    </Menu.Item>
                </Menu>

                {loggedIn && (
                    <Link className="add_card" to={ADD_HOTEL_PAGE}>
                        <IoIosAdd/> Add Hotel
                    </Link>
                )}
            </Container>
        </NavigationArea>
    );
};

const ProfileRoute = (props) => {
    const {match} = props;
    return (
        <Container fluid={true}>
            <Route exact path={`${match.path}`} component={AgentItemLists}/>
            <Route
                path={`${match.path}${AGENT_PROFILE_FAVOURITE}`}
                component={AgentFavItemLists}
            />
            <Route
                path={`${match.path}${AGENT_PROFILE_CONTACT}`}
                component={AgentContact}
            />
        </Container>
    );
};

const AgentProfileInfo = () => {

    // const {data, loading} = useDataApi('getCurrentUser');
    const {data, loading} = useDataApi('/data/agent.json');

    if (isEmpty(data) || loading) return <Loader/>;

    // const social_profile2= {
    //     "facebook": "http://www.facebook.com/redqinc",
    //         "twitter": "http://www.twitter.com/redqinc",
    //         "linkedin": "http://www.linkedin.com/company/redqinc",
    //         "instagram": "http://www.instagram.com/redqinc",
    //         "pinterest": "http://www.pinterest.com/redqinc"
    // }
    // const first_name = data[0].firstName;
    // const last_name = data[0].lastName;
    // const content = 'Mert Yuksek';
    // const profile_pic = data[0].avatar;
    // const cover_pic = "http://s3.amazonaws.com/redqteam.com/tripfinder-images/coverpic2.png";
    // const social_profile = social_profile2;
    // const username = `${first_name} ${last_name}`;

    return (
        <Fragment>
            <BannerSection>
                <Image className="absolute" src={data[0].cover_pic.url} alt="Profile cover"/>
            </BannerSection>
            <UserInfoArea>
                <Container fluid={true}>
                    <ProfileImage>
                        {data[0].profile_pic ? (
                            <Image src={data[0].profile_pic.url} alt="Profile"/>
                        ) : (
                            <ProfilePicLoader/>
                        )}
                    </ProfileImage>
                    <ProfileInformationArea>
                        <ProfileInformation>
                            <Heading content={data[0].username}/>
                            <Text content={data[0].content}/>
                        </ProfileInformation>
                        <SocialAccount>
                            <Popover content="Twitter">
                                <a
                                    href={data[0].social_profile.twitter}
                                    target="_blank"
                                    rel="noopener noreferrer"
                                >
                                    <IoLogoTwitter className="twitter"/>
                                </a>
                            </Popover>
                            <Popover content="Facebook">
                                <a
                                    href={data[0].social_profile.facebook}
                                    target="_blank"
                                    rel="noopener noreferrer"
                                >
                                    <IoLogoFacebook className="facebook"/>
                                </a>
                            </Popover>
                            <Popover content="Instagram">
                                <a
                                    href={data[0].social_profile.instagram}
                                    target="_blank"
                                    rel="noopener noreferrer"
                                >
                                    <IoLogoInstagram className="instagram"/>
                                </a>
                            </Popover>
                        </SocialAccount>
                    </ProfileInformationArea>
                </Container>
            </UserInfoArea>
        </Fragment>
    );
};

export default function AgentDetailsViewPage(props) {
    return (
        <AgentDetailsPage>
            <AuthProvider>
                <AgentProfileInfo/>
                <ProfileNavigation {...props} />
                <ProfileRoute {...props} />
            </AuthProvider>
        </AgentDetailsPage>
    );
}
