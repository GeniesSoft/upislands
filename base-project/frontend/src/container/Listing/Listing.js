import React, {Fragment, useEffect, useState} from 'react';
import Sticky from 'react-stickynode';
import Toolbar from 'components/UI/Toolbar/Toolbar';
import {Checkbox} from 'antd';
import CategorySearch from 'components/Search/CategorySearch/CategotySearch';
import {PostPlaceholder} from 'components/UI/ContentLoader/ContentLoader';
import SectionGrid from 'components/SectionGrid/SectionGrid';
import SectionGrid2 from 'components/SectionGrid2/SectionGrid2';
import ListingMap from './ListingMap';
import FilterDrawer from 'components/Search/MobileSearchView';
import useWindowSize from 'library/hooks/useWindowSize';
import {SINGLE_POST_PAGE} from 'settings/constant';
import ListingWrapper, {PostsWrapper, ShowMapCheckbox} from './Listing.style';
import data from '../../service/data/data.json';
import LocationApi from "../../service/location/LocationApi";
import Loader from "../../components/Loader/Loader";

export default function Listing({location, history}) {

    const {width} = useWindowSize();

    let columnWidth = [1 / 1];

    const [tripData, setTripData] = useState(null);

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

    return (
        <ListingWrapper>
            <Sticky top={82} innerZ={999} activeClass="isHeaderSticky">
                <Toolbar
                    left={
                        width > 991 ? (
                            <CategorySearch history={history} location={location}/>
                        ) : (
                            <FilterDrawer history={history} location={location}/>
                        )
                    }
                />
            </Sticky>

            <Fragment>
                <PostsWrapper className={width > 991 ? 'col-12' : 'col-24'}>
                    {width > 481 ?
                        <SectionGrid2
                            link={SINGLE_POST_PAGE}
                            columnWidth={columnWidth}
                            data={tripData}
                            totalItem={6}
                            loading={false}
                            limit={10}
                            handleLoadMore={null}
                            placeholder={<PostPlaceholder/>}
                        />
                        :
                        <SectionGrid
                            link={SINGLE_POST_PAGE}
                            columnWidth={columnWidth}
                            data={tripData}
                            totalItem={6}
                            loading={false}
                            limit={10}
                            handleLoadMore={null}
                            placeholder={<PostPlaceholder/>}
                        />
                    }

                </PostsWrapper>

                {width > 991 && <ListingMap/>}
            </Fragment>
        </ListingWrapper>
    );
}
