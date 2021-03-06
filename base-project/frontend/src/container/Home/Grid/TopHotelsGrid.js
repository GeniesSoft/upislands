import React, {useEffect, useState} from 'react';
import Heading from 'components/UI/Heading/Heading';
import TextLink from 'components/UI/TextLink/TextLink';
import Container from 'components/UI/Container/Container';
import {PostPlaceholder} from 'components/UI/ContentLoader/ContentLoader';
import SectionGrid from 'components/SectionGrid/SectionGrid';
import SectionTitle from 'components/SectionTitle/SectionTitle';
import useWindowSize from 'library/hooks/useWindowSize';
import {LISTING_POSTS_PAGE, SINGLE_POST_PAGE} from 'settings/constant';
import data from '../../../service/data/data.json';
import LocationApi from "../../../service/location/LocationApi";
import Loader from "../../../components/Loader/Loader";

const TopHotelsGrid = () => {

    const [tripData, setTripData] = useState(null);
    const {width} = useWindowSize();

    function fetchLocations() {
        LocationApi.readAllFrontend()
            .then(
                response => {
                    setTripData(response.data)
                }
            );
    }

    useEffect(async () => {
        await fetchLocations();
    }, []);

    if (tripData == null) return <Loader/>;

    let posts = tripData;
    let limit = 10;

    posts = tripData.slice(0,3);

    return (
        <Container fluid={true}>
            <SectionTitle
                title={<Heading content="Travelersâ€™ Choice: Top trips"/>}
                link={<TextLink link={LISTING_POSTS_PAGE} content="Show all"/>}
            />

            <SectionGrid
                link={SINGLE_POST_PAGE}
                columnWidth={[1 / 1, 1 / 2, 1 / 3, 1 / 4, 1 / 5]}
                data={posts}
                loading={false}
                limit={limit}
                placeholder={<PostPlaceholder/>}
            />
        </Container>
    );
};

export default TopHotelsGrid;
