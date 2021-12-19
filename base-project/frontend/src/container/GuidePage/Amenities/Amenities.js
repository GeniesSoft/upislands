import React from 'react';
import PropTypes from 'prop-types';
import Heading from 'components/UI/Heading/Heading';
import TextLink from 'components/UI/TextLink/TextLink';
import {FaAirFreshener, FaCarAlt, FaGasPump, FaRegMoneyBillAlt, FaSwimmer, FaWifi} from 'react-icons/fa';
import IconCard from 'components/IconCard/IconCard';
import AmenitiesWrapper, {AmenitiesArea} from './Amenities.style';
import {TextButton} from '../GuidePageView.style';
import {Element} from 'react-scroll';
import {GrMoney, IoMdLocate, MdAttachMoney, RiLuggageDepositLine, RiNumber2} from "react-icons/all";

const Amenities = ({amenities, titleStyle, linkStyle}) => {
    console.log(amenities)

    return (
        <Element name="amenities" className="Amenities">
            <AmenitiesWrapper>
                <Heading as="h2" content="Amenities" {...titleStyle} />
                <AmenitiesArea>
                    {amenities["deposit"]["value"] ?
                        <IconCard icon={<MdAttachMoney/>} title="Deposit"/>
                        :
                        null
                    }
                    {amenities["personalItemLocator"]["value"] ?
                        <IconCard icon={<IoMdLocate/>} title="Locator"/>
                        :
                        null
                    }
                    {amenities["gas"]["value"] ?
                        <IconCard icon={<FaGasPump/>} title="Gas Included"/>
                        :
                        null
                    }
                    {amenities["secondRiderFee"]["value"] ?
                        <IconCard icon={<RiNumber2/>} title="Second Rider"/>
                        :
                        null
                    }
                </AmenitiesArea>
                <TextButton>
                    <TextLink link="#1" content="Show all amenities" {...linkStyle} />
                </TextButton>
            </AmenitiesWrapper>
        </Element>
    );
};

Amenities.propTypes = {
    titleStyle: PropTypes.object,
    linkStyle: PropTypes.object,
};

Amenities.defaultProps = {
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

export default Amenities;
