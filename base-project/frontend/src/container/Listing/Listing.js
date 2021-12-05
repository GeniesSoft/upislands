import React, {Fragment, useState} from 'react';
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
import useDataApi from 'library/hooks/useDataApi';
import {SINGLE_POST_PAGE} from 'settings/constant';
import ListingWrapper, {PostsWrapper, ShowMapCheckbox} from './Listing.style';

export default function Listing({location, history}) {
    let url = '/data/hotel.json';
    const {width} = useWindowSize();
    const [showMap, setShowMap] = useState(false);
    const {data, loading, loadMoreData, total, limit} = useDataApi('findAllLocations');
    // let columnWidth = [1 / 1, 1 / 2, 1 / 3, 1 / 4, 1 / 5];
    let columnWidth = [1 / 1];
    if (location.search) {
        url += location.search;
    }
    if (showMap) {
        // columnWidth = [1 / 1, 1 / 2, 1 / 2, 1 / 2, 1 / 3];
        columnWidth = [1 / 1];
    }

    const handleMapToggle = () => {
        setShowMap((showMap) => !showMap);
    };

    const get = () => {

    }

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
                    // right={
                    //     <ShowMapCheckbox>
                    //         <Checkbox defaultChecked={true} onChange={handleMapToggle}>
                    //             Show map
                    //         </Checkbox>
                    //     </ShowMapCheckbox>
                    // }
                />
            </Sticky>

            <Fragment>
                <PostsWrapper className={width > 991 ? 'col-12' : 'col-24'}>
                    {width > 481 ?
                        <SectionGrid2
                            link={SINGLE_POST_PAGE}
                            columnWidth={columnWidth}
                            data={data[0]}
                            totalItem={total.length}
                            loading={loading}
                            limit={limit}
                            handleLoadMore={loadMoreData}
                            placeholder={<PostPlaceholder/>}
                        />
                        :
                        <SectionGrid
                            link={SINGLE_POST_PAGE}
                            columnWidth={columnWidth}
                            data={data[0]}
                            totalItem={total.length}
                            loading={loading}
                            limit={limit}
                            handleLoadMore={loadMoreData}
                            placeholder={<PostPlaceholder/>}
                        />
                    }

                </PostsWrapper>

                {width > 991 && <ListingMap/>}
            </Fragment>
        </ListingWrapper>
    );
}
