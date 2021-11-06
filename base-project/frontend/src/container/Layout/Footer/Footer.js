import React from 'react';
import Logo from 'components/UI/Logo/Logo';
import Footers from 'components/Footer/Footer';
import FooterMenu from './FooterMenu';

const Footer = () => {
    return (
        <Footers
            logo={
                <Logo
                    withLink
                    linkTo="/"
                    src="/images/logo-alt.png"
                    title=""
                />
            }
            menu={<FooterMenu/>}
            copyright={`Copyright @ ${new Date().getFullYear()} GeniesSoft`}
        />
    );
};

export default Footer;
