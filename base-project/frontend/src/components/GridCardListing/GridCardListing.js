import React from 'react';
import PropTypes from 'prop-types';
import GridCardWrapper, {
    ButtonGroup,
    ContentWrapper,
    FavoriteIcon,
    ImageWrapper,
    LocationArea,
    MetaWrapper,
    PriceArea,
    RatingArea,
    TitleArea,
} from './GridCardListing.style';

const GridCardListing = ({
                      className,
                      favorite,
                      location,
                      title,
                      price,
                      rating,
                      editBtn,
                      viewDetailsBtn,
                      children,
                  }) => {
    let classes = viewDetailsBtn || editBtn ? `has_btn ${className}` : className;
    return (
        <GridCardWrapper className={`grid_card ${classes}`.trim()}>
            <div className={"wrapper"}>
                <div className={"wrapper-img"}>
                    <ImageWrapper className="media_wrapper">{children}</ImageWrapper>
                </div>
                <div className={"wrapper-content"}>
                    <ContentWrapper className="content_wrapper">
                        {location && <LocationArea>{location}</LocationArea>}
                        {title && <TitleArea>{title}</TitleArea>}
                        <MetaWrapper className="meta_wrapper">
                            {rating && <RatingArea className="rating">{rating}</RatingArea>}
                            {viewDetailsBtn || editBtn ? (
                                <ButtonGroup className="button_group">
                                    {viewDetailsBtn}
                                    {editBtn}
                                </ButtonGroup>
                            ) : null}
                            <div className="rating">
                                {price && <PriceArea className="price"><b>{price}</b>/night</PriceArea>}
                            </div>

                        </MetaWrapper>
                    </ContentWrapper>
                </div>


            </div>
            {favorite && <FavoriteIcon>{favorite}</FavoriteIcon>}
        </GridCardWrapper>
    );
};

GridCardListing.propTypes = {
    className: PropTypes.string,
    title: PropTypes.oneOfType([PropTypes.string, PropTypes.element]),
    price: PropTypes.string,
    rating: PropTypes.oneOfType([PropTypes.string, PropTypes.element]),
    location: PropTypes.oneOfType([PropTypes.string, PropTypes.element]),
    editBtn: PropTypes.element,
    viewDetailsBtn: PropTypes.element,
};

export default GridCardListing;
