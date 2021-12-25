import React from 'react';
import {InfoWindow} from '@react-google-maps/api';
import Rating from 'components/UI/Rating/Rating';
import GridCardListing from '../GridCardListing/GridCardListing';

const MapInfoWindow = ({data, onCloseClick}) => {
    const position = {lat: data?.lat, lng: data?.lng};

    return (
        <InfoWindow
            position={position}
            options={{pixelOffset: new window.google.maps.Size(0, -85)}}
            id={data?.id}
            onCloseClick={onCloseClick}
        >
            <GridCardListing
                className='info_window_card'
                location={data?.formattedAddress}
                title={data?.title}
                price={`$${data?.price}`}
                rating={
                    <Rating
                        rating={data?.rating}
                        ratingCount={data?.ratingCount}
                        type='bulk'
                    />
                }
            >
                <img src={data?.thumbUrl} alt={data?.title}/>
            </GridCardListing>
        </InfoWindow>
    );
};

export default MapInfoWindow;
