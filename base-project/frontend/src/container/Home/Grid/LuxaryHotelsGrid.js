import React from 'react';
import Heading from 'components/UI/Heading/Heading';
import TextLink from 'components/UI/TextLink/TextLink';
import Container from 'components/UI/Container/Container';
import SectionGrid from 'components/SectionGrid/SectionGrid';
import {PostPlaceholder} from 'components/UI/ContentLoader/ContentLoader';
import useWindowSize from 'library/hooks/useWindowSize';
import SectionTitle from 'components/SectionTitle/SectionTitle';
import {LISTING_POSTS_PAGE, SINGLE_POST_PAGE,} from '../../../settings/constant';
import data from '../../../service/data/data.json';

const LuxaryHotelsGrid = () => {

    const tripData = data.trips;
    const {width} = useWindowSize();

    let posts = tripData;
    let limit;

    posts = tripData.slice(0,3);

    return (
        <Container fluid={true}>
            <SectionTitle
                title={<Heading content="Best Rated: Luxary trips"/>}
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

export default LuxaryHotelsGrid;
