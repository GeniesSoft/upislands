import React from 'react';
import {MarkerClusterer} from '@react-google-maps/api';
import MapWrapper from './MapWrapper';
import HotelMapMarkerCluster from './ListingPageMap';
import HotelMapMarkerSingle from './SinglePageMap';

const Map = (props) => {
    const {multiple, location} = props;
    const handleClustererClick = (data) => {
        const markerClusterer = data.getMarkers();
        console.log(`Current clicked markers length: ${markerClusterer.length}`);
        console.log(markerClusterer);
    };
    return (
        <>
            {multiple ? (
                <MapWrapper
                    id="map-multiple-location"
                    zoom={15}
                    center={{
                        lat: 25.790225890688255,
                        lng: -80.156786273679,
                    }}
                >
                    <MarkerClusterer
                        gridSize={60}
                        averageCenter
                        enableRetinaIcons={true}
                        onClick={handleClustererClick}
                    >
                        {(clusterer) => (
                            <HotelMapMarkerCluster
                                location={location}
                                clusterer={clusterer}
                            />
                        )}
                    </MarkerClusterer>
                </MapWrapper>
            ) : (
                <MapWrapper
                    id="map-single-location"
                    mapContainerStyle={{
                        height: '400px',
                        width: '100%',
                    }}
                    zoom={15}
                    center={{
                        lat: 25.790225890688255,
                        lng: -80.156786273679,
                    }}
                >
                    <HotelMapMarkerSingle location={location.location}/>
                </MapWrapper>
            )}
        </>
    );
};

export default Map;
