import React from 'react';
import {IoIosArrowBack, IoIosArrowForward} from 'react-icons/io';
import Loader from 'components/Loader/Loader';
import Container from 'components/UI/Container/Container';
import Heading from 'components/UI/Heading/Heading';
import TextLink from 'components/UI/TextLink/TextLink';
import SectionTitle from 'components/SectionTitle/SectionTitle';
import ImageCard from 'components/ImageCard/ImageCard';
import GlideCarousel, {GlideSlide,} from 'components/UI/GlideCarousel/GlideCarousel';
import {LISTING_POSTS_PAGE} from 'settings/constant';
import LocationWrapper, {CarouselSection} from './Location.style';
import data from '../../../service/data/data.json';

const carouselOptions = {
    type: 'carousel',
    perView: 5,
    gap: 30,
    hoverpause: true,
    breakpoints: {
        1440: {
            perView: 5,
            gap: 20,
        },
        1200: {
            perView: 4,
        },
        991: {
            perView: 3,
            gap: 15,
        },
        667: {
            perView: 2,
            gap: 20,
        },
        480: {
            perView: 1,
            gap: 0,
        },
    },
};

const LocationGrid = () => {
    console.log(data.locations);
    const locationData = data.locations;

    return (
        <LocationWrapper>
            <Container fluid={true}>
                <SectionTitle
                    title={<Heading content="Explore Locations"/>}
                    link={<TextLink link={LISTING_POSTS_PAGE} content="Show all"/>}
                />
                <CarouselSection>
                    {locationData.length !== 0 ? (
                        <GlideCarousel
                            carouselSelector="explore_carousel"
                            prevButton={<IoIosArrowBack/>}
                            nextButton={<IoIosArrowForward/>}
                            options={carouselOptions}
                        >
                            <>
                                {locationData.map((post, index) => (
                                    <GlideSlide key={index}>
                                        <ImageCard
                                            className={
                                                (post.numberOfPost === 0) ?
                                                    "location-card-passive" :
                                                    "location-card-active"
                                            }
                                            link="listing"
                                            imageSrc={`/${post.locationImage.url}`}
                                            title={post.city}
                                            meta={(post.numberOfPost === 0) ?
                                                "coming soon..." :
                                                `${post.numberOfPost} Trips`
                                            }
                                        />
                                    </GlideSlide>
                                ))}
                            </>
                        </GlideCarousel>
                    ) : (
                        <Loader/>
                    )}
                </CarouselSection>
            </Container>
        </LocationWrapper>
    );
};

export default LocationGrid;
