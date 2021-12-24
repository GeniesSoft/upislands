import React, {Fragment, useEffect, useState} from 'react';
import {useLocation} from 'library/hooks/useLocation';
import Sticky from 'react-stickynode';
import {Button, Col, Modal, Row} from 'antd';
import Container from 'components/UI/Container/Container';
import Loader from 'components/Loader/Loader';
import useWindowSize from 'library/hooks/useWindowSize';
import Description from './Description/Description';
import Location from './Location/Location';
import Review from './Review/Review';
import Reservation from './Reservation/Reservation';
import BottomReservation from './Reservation/BottomReservation';
import TopBar from './TopBar/TopBar';
import SinglePageWrapper, {PostImageWrapper} from './GuidePageView.style';
import PostImageGallery from './ImageGallery/ImageGallery';
import isEmpty from 'lodash/isEmpty';
import PostGallery from "../../components/PostGallery/PostGallery";
import Amenities from "./Amenities/Amenities";
import LocalGuideApi from "../../service/localguide/LocalGuideApi";
import LocationApi from "../../service/location/LocationApi";

const SinglePage = ({match}) => {

    const {href} = useLocation();
    const [isModalShowing, setIsModalShowing] = useState(false);
    const {width} = useWindowSize();

    const [guideData, setGuideData] = useState(null);
    const [tripData, setTripData] = useState(null);

    function fetchLocalGuides() {
        LocalGuideApi.readAllFrontend()
            .then(
                response => {
                    setGuideData(response.data)
                }
            );
    }

    function fetchLocations() {
        LocationApi.readAllFrontend()
            .then(
                response => {
                    setTripData(response.data)
                }
            );
    }

    useEffect(async () => {
        await fetchLocalGuides();
        await fetchLocations();
    }, []);

    if (guideData == null || tripData == null) return <Loader/>;

    const guide = guideData.filter(guide => guide.id === parseInt(match.params.slug))[0]

    const {
        reviews,
        rating,
        ratingCount,
        price,
        name,
        gallery,
        location,
        content,
        amenities,
        author,
    } = guide;

    return (
        <SinglePageWrapper>
            <PostImageWrapper>
                <PostGallery gallery={gallery} />

                <Button
                    type="primary"
                    onClick={() => setIsModalShowing(true)}
                    className="image_gallery_button"
                >
                    View Photos
                </Button>
                <Modal
                    visible={isModalShowing}
                    onCancel={() => setIsModalShowing(false)}
                    footer={null}
                    width="100%"
                    maskStyle={{
                        backgroundColor: 'rgba(255, 255, 255, 0.95)',
                    }}
                    wrapClassName="image_gallery_modal"
                    closable={false}
                >
                    <Fragment>
                        <PostImageGallery gallery={gallery} />
                        <Button
                            onClick={() => setIsModalShowing(false)}
                            className="image_gallery_close"
                        >
                            <svg width="16.004" height="16" viewBox="0 0 16.004 16">
                                <path
                                    id="_ionicons_svg_ios-close_2_"
                                    d="M170.4,168.55l5.716-5.716a1.339,1.339,0,1,0-1.894-1.894l-5.716,5.716-5.716-5.716a1.339,1.339,0,1,0-1.894,1.894l5.716,5.716-5.716,5.716a1.339,1.339,0,0,0,1.894,1.894l5.716-5.716,5.716,5.716a1.339,1.339,0,0,0,1.894-1.894Z"
                                    transform="translate(-160.5 -160.55)"
                                    fill="#909090"
                                />
                            </svg>
                        </Button>
                    </Fragment>
                </Modal>
            </PostImageWrapper>

            <TopBar title={name} shareURL={href} author={author} media={gallery}/>

            <Container>
                <Row gutter={30} id="reviewSection" style={{marginTop: 30}}>
                    <Col xl={16}>
                        <Description
                            content={content}
                            title={name}
                            location={location}
                            rating={rating}
                            ratingCount={ratingCount}
                        />
                        <Amenities amenities={amenities} />
                        <Location location={guide}/>
                    </Col>
                    <Col xl={8}>
                        {width > 1200 ? (
                            <Sticky
                                innerZ={999}
                                activeClass="isSticky"
                                top={202}
                                bottomBoundary="#reviewSection"
                            >
                                <Reservation guideId={guide.id} trips={tripData} />
                            </Sticky>
                        ) : (
                            <BottomReservation
                                guideId={guide.id}
                                trips={tripData}
                                title={name}
                                price={price}
                                rating={rating}
                                ratingCount={ratingCount}
                            />
                        )}
                    </Col>
                </Row>
                <Row gutter={30}>
                    <Col xl={16}>
                        <Review
                            reviews={reviews}
                            ratingCount={ratingCount}
                            rating={rating}
                        />
                    </Col>
                    <Col xl={8}/>
                </Row>
            </Container>
        </SinglePageWrapper>
    );
};

export default SinglePage;
