import React from 'react';
import useWindowSize from './useWindowSize';
import StickyBookingWrapper, {
    ActionBtn,
    HotelAction,
    HotelInfo,
    HotelRating,
    InfoArea,
    Logo,
    Price,
    Title,
} from './StickyBooking.style';

const StickyBooking = ({logo, title, price, rating, action, className}) => {
    const addAllClasses = ['sticky_booking'];
    const windowSize = useWindowSize();
    const windowInnerWidth = process.browser && windowSize.innerWidth;

    if (className) {
        addAllClasses.push(className);
    }

    return (
        <StickyBookingWrapper className={addAllClasses.join(' ')}>
            <HotelInfo className="hotel_info">
                {windowInnerWidth > 767 && (
                    <>{logo && <Logo src={logo} alt={title}/>}</>
                )}

                {title || rating || price ? (
                    <InfoArea>
                        {windowInnerWidth > 767 ? (
                            <>{title && <Title>{title}</Title>}</>
                        ) : (
                            null
                        )}
                        {rating && <HotelRating>{rating}</HotelRating>}
                    </InfoArea>
                ) : (
                    ''
                )}
            </HotelInfo>

            <HotelAction className="hotel_action">
                <ActionBtn>{action}</ActionBtn>
            </HotelAction>
        </StickyBookingWrapper>
    );
};

export default StickyBooking;
