import React, {Fragment, useState} from 'react';
import PropTypes from 'prop-types';
import Card from 'components/UI/Card/Card';
import Heading from 'components/UI/Heading/Heading';
import Text from 'components/UI/Text/Text';
import TextLink from 'components/UI/TextLink/TextLink';
import RenderReservationForm from './RenderReservationForm';

export default function Reservation(props) {

    const [price, setPrice] = useState(null);

    const calculatePrice = (start, end) => {
        const sH = start.toString().slice(0,2);
        const eH = end.toString().slice(0,2);

        const sM = start.toString().slice(3,5);
        const eM = end.toString().slice(3,5);

        let rH = parseInt(eH) - parseInt(sH);
        let rM = parseInt(eM) - parseInt(sM);
        let price = 0;

        if (rH === 1 && rM === 0) {
            price = 149;
        }
        else if (rH === 1 && rM === -30) {
            price = 99;
        }
        else if (rH === 0 && rM === 30) {
            price = 99;
        }
        else {
            price = 0;
        }

        return price;
    }

    const handlePriceCheck = (start, end) => {
        setPrice(calculatePrice(start, end));
    }

    const getPrice = () => {
        if (price && price !== 0) {
            return (<Text fontWeight={"100"} as="span" content={`Total Price: $${price}`}/>);
        }
        else {
            return (<Text fontWeight={"100"} as="span" content="Select time interval for price" />);
        }
    }

    return (
        <Card
            className="reservation_sidebar"
            header={
                <Fragment>
                    <Heading
                        content={
                            <div>
                                {getPrice()}
                            </div>
                        }
                    />
                </Fragment>
            }
            content={<RenderReservationForm calculatePrice={calculatePrice} checkPrice={handlePriceCheck} locationId={props.locationId} guides={props.guides} />}
            footer={
                <p>
                    Special offers available. <TextLink to="/#1" content="See details"/>
                </p>
            }
        />
    );
}
