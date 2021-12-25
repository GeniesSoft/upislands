import React from 'react';
import PropTypes from 'prop-types';
import Heading from 'components/UI/Heading/Heading';
import TextLink from 'components/UI/TextLink/TextLink';
import {FaAirFreshener, FaCarAlt, FaSwimmer, FaWifi} from 'react-icons/fa';
import IconCard from 'components/IconCard/IconCard';
import {TextButton} from '../TripPageView.style';
import {Element} from 'react-scroll';
import GuidesWrapper, {GuidesArea} from "./Guides.style";
import {Card} from "antd";
import Meta from "antd/es/card/Meta";
import {FiExternalLink} from "react-icons/fi";
import {GUIDE_PAGE} from "../../../settings/constant";

const Guides = ({guides, titleStyle, linkStyle}) => {
    return (
        <Element name="guides" className="guides">
            <GuidesWrapper>
                <Heading as="h2" content="Guides" {...titleStyle} />
                <GuidesArea>
                    {
                        guides.map( guide => {
                            return (
                                <Card
                                    hoverable
                                    style={{ width: 240 }}
                                >
                                    <div className={"card-image"}>
                                        <img alt="guide" src={guide.gallery[0] ? guide.gallery[0].url : guide.image} />
                                    </div>
                                    <Meta title={guide.name} description={guide.location.formattedAddress} />
                                    <div className={"view-details"}>
                                        <TextLink
                                            link={`${GUIDE_PAGE}/${guide.id}`}
                                            icon={<FiExternalLink/>}
                                            content="View Details"
                                        />
                                    </div>

                                </Card>
                            )
                        })
                    }
                </GuidesArea>
                <TextButton>
                    <TextLink link="#1" content="Show all guides" {...linkStyle} />
                </TextButton>
            </GuidesWrapper>
        </Element>
    );
};

Guides.propTypes = {
    titleStyle: PropTypes.object,
    linkStyle: PropTypes.object,
};

Guides.defaultProps = {
    titleStyle: {
        color: '#2C2C2C',
        fontSize: ['17px', '20px', '25px'],
        lineHeight: ['1.15', '1.2', '1.36'],
        mb: ['14px', '20px', '30px'],
    },
    linkStyle: {
        fontSize: '15px',
        fontWeight: '700',
        color: '#008489',
    },
};

export default Guides;
