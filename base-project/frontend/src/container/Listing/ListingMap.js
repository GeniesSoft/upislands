import React from 'react';
import isEmpty from 'lodash/isEmpty';
import Map from 'components/Map/Map';
import {FixedMap} from './Listing.style';
import data from '../../service/data/data.json';

const ListingMap = () => {
    const tripData = data.trips;
    if (isEmpty(data)) return <div>Loading</div>;

    return (
        <FixedMap>
            <Map location={tripData} multiple={true}/>
        </FixedMap>
    );
};

export default ListingMap;
