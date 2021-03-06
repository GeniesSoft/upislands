import React, {Fragment, useEffect, useState} from 'react';
import {useLocation} from 'library/hooks/useLocation';
import Sticky from 'react-stickynode';
import {Button, Col, Modal, Row} from 'antd';
import Container from 'components/UI/Container/Container';
import Loader from 'components/Loader/Loader';
import useWindowSize from 'library/hooks/useWindowSize';
import Description from './Description/Description';
import Guides from './Guides/Guides';
import Location from './Location/Location';
import Review from './Review/Review';
import Reservation from './Reservation/Reservation';
import BottomReservation from './Reservation/BottomReservation';
import TopBar from './TopBar/TopBar';
import SinglePageWrapper, {PostImageWrapper} from './TripPageView.style';
import PostImageGallery from './ImageGallery/ImageGallery';
import isEmpty from 'lodash/isEmpty';
import PostGallery from "../../components/PostGallery/PostGallery";
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
                    setGuideData(response.data);
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

    const trip = tripData.filter((trip) => trip.slug === parseInt(match.params.slug))[0];

    const {
        reviews,
        rating,
        ratingCount,
        price,
        title,
        videoGallery,
        gallery,
        location,
        content,
        author,
    } = trip;

    const guides = guideData;

    return (
        <SinglePageWrapper>
            <PostImageWrapper>
                <PostGallery gallery={gallery} videoGallery={videoGallery} />

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

            <TopBar title={title} shareURL={href} author={author} media={gallery}/>

            <Container>
                <Row gutter={30} id="reviewSection" style={{marginTop: 30}}>
                    <Col xl={16}>
                        <Description
                            content={content}
                            title={title}
                            location={location}
                            rating={rating}
                            ratingCount={ratingCount}
                        />
                        <Guides guides={guides}/>
                        <Location location={trip}/>
                    </Col>
                    <Col xl={8}>
                        {width > 1200 ? (
                            <Sticky
                                innerZ={999}
                                activeClass="isSticky"
                                top={202}
                                bottomBoundary="#reviewSection"
                            >
                                <Reservation locationId={parseInt(match.params.slug)} guides={guides} />
                            </Sticky>
                        ) : (
                            <BottomReservation
                                locationId={parseInt(match.params.slug)}
                                guides={guides}
                                title={title}
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
