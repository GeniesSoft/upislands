import React from 'react';
import {FiExternalLink} from 'react-icons/fi';
import TextLink from 'components/UI/TextLink/TextLink';
import Rating from 'components/UI/Rating/Rating';
import Favourite from 'components/UI/Favorite/Favorite';
import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import GridCardListing from '../GridCardListing/GridCardListing';

const responsive = {
    desktop: {
        breakpoint: {
            max: 3000,
            min: 1024,
        },
        items: 1,
        paritialVisibilityGutter: 40,
    },
    mobile: {
        breakpoint: {
            max: 464,
            min: 0,
        },
        items: 1,
        paritialVisibilityGutter: 30,
    },
    tablet: {
        breakpoint: {
            max: 1024,
            min: 464,
        },
        items: 1,
        paritialVisibilityGutter: 30,
    },
};

const PostGrid = (props) => {

    const title = props.fields.locationName;
    console.log(`hellfsolfoslof`)
    console.log(props)

    const rating = 4;
    const location = {
        "id": 67404,
        "lat": 40.706877,
        "lng": -74.011265,
        "formattedAddress": "52623 Donnie Roads",
        "zipcode": "68225-1064",
        "city": "Nestorview",
        "state_long": "Washington",
        "state_short": "WY",
        "country_long": "Tanzania",
        "country_short": "VN"
    };
    const price = '221';
    const ratingCount = 300;
    const gallery = [
        {
            "id": 11287,
            "url": "http://s3.amazonaws.com/redqteam.com/tripfinder-images/hotel-2_thumb.jpg"
        },
        {
            "id": 51759,
            "url": "http://s3.amazonaws.com/redqteam.com/tripfinder-images/hotel-3_thumb.jpg"
        },
        {
            "id": 1888,
            "url": "http://s3.amazonaws.com/redqteam.com/tripfinder-images/hotel-4_thumb.jpg"
        }
    ];
    const slug = '';
    const link = '';

    return (
        <GridCardListing
            isCarousel={true}
            favorite={
                <Favourite
                    onClick={event => {
                        console.log(event);
                    }}
                />
            }
            location={location.formattedAddress}
            title={<TextLink link={`${link}/${slug}`} content={title}/>}
            price={`$${price}`}
            rating={<Rating rating={rating} ratingCount={ratingCount} type="bulk"/>}
            viewDetailsBtn={
                <TextLink
                    link={`${link}/${slug}`}
                    icon={<FiExternalLink/>}
                    content="View Details"
                />
            }
        >
            <Carousel
                additionalTransfrom={0}
                arrows
                autoPlaySpeed={3000}
                containerClass="container"
                dotListClass=""
                draggable
                focusOnSelect={false}
                infinite
                itemClass=""
                renderDotsOutside={false}
                responsive={responsive}
                showDots={true}
                sliderClass=""
                slidesToSlide={1}
            >
                {gallery.map(({url, title}, index) => (
                    <img
                        src={url}
                        alt={title}
                        key={index}
                        draggable={false}
                        style={{
                            width: '100%',
                            height: '100%',
                            objectFit: 'cover',
                            position: 'relative',
                        }}
                    />
                ))}
            </Carousel>
        </GridCardListing>
    );
};

export default PostGrid;
