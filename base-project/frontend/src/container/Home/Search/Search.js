import React from 'react';
import PropTypes from 'prop-types';
import Heading from 'components/UI/Heading/Heading';
import Text from 'components/UI/Text/Text';
import Container from 'components/UI/Container/Container';
import GlideCarousel, {GlideSlide,} from 'components/UI/GlideCarousel/GlideCarousel';
import SearchForm from './SearchForm';
import BannerWrapper, {ComponentWrapper, SearchWrapper} from './Search.style';
import {Button} from "antd";
import SearchIcon from "@mui/icons-material/Search";
import {mapDataHelper} from "../../../components/Map/mapDataHelper";
import {setStateToUrl} from "../../../library/helpers/url_handler";
import {LISTING_POSTS_PAGE} from "../../../settings/constant";

const SearchArea = ({searchTitleStyle, searchDescriptionStyle, history}) => {

    return (
        <BannerWrapper>
            <GlideCarousel
                controls={false}
                options={{gap: 0, autoplay: 5000, animationDuration: 1000}}
                bullets={true}
                numberOfBullets={0}
            >
                <>
                    <GlideSlide>
                        <img src="/images/banner/banner-img.jpg" alt="Banner"/>
                    </GlideSlide>
                    {/*<GlideSlide>*/}
                    {/*    <img src="/images/banner/2.jpg" alt="Banner 2"/>*/}
                    {/*</GlideSlide>*/}
                    {/*<GlideSlide>*/}
                    {/*    <img src="/images/banner/3.jpg" alt="Banner 3"/>*/}
                    {/*</GlideSlide>*/}
                </>
            </GlideCarousel>

            <Container>
                <SearchWrapper>
                    {/*<Heading*/}
                    {/*    {...searchTitleStyle}*/}
                    {/*    content="Latest reviews. Lowest prices."*/}
                    {/*/>*/}
                    {/*<Text*/}
                    {/*    {...searchDescriptionStyle}*/}
                    {/*    content="compares prices from 200+ booking sites to help you find the lowest price on the right hotel for you."*/}
                    {/*/>*/}
                    <SearchForm/>
                    <div className={"flexible"}>
                        <Text
                            {...searchTitleStyle}
                            content="Not sure where to go? Perfect."
                        />
                        <br/>
                        <Button
                            type="primary"
                            htmlType="submit"
                            href={"/listing"}
                            shape={"circle"}
                            size={"large"}
                        >
                            I'm flexible
                        </Button>
                    </div>
                </SearchWrapper>
            </Container>
        </BannerWrapper>
    );
};

SearchArea.propTypes = {
    searchTitleStyle: PropTypes.object,
    searchDescriptionStyle: PropTypes.object,
};

SearchArea.defaultProps = {
    searchTitleStyle: {
        color: '#2C2C2C',
        fontSize: ['20px', '24px', '28px'],
        lineHeight: ['28px', '30px', '30px'],
        mb: '9px',
    },
    searchDescriptionStyle: {
        color: '#2C2C2C',
        fontSize: '15px',
        lineHeight: '24px',
        mb: '30px',
    },
};

export default SearchArea;
