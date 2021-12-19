import React, {useState} from 'react';
import {Button, DatePicker, Select} from 'antd';
import HtmlLabel from 'components/UI/HtmlLabel/HtmlLabel';
import DatePickerRange from 'components/UI/DatePicker/ReactDates';
import ViewWithPopup from 'components/UI/ViewWithPopup/ViewWithPopup';
import InputIncDec from 'components/UI/InputIncDec/InputIncDec';
import { TimePicker } from 'antd';
import ReservationFormWrapper, {
    FieldWrapper,
    FormActionArea,
    ItemWrapper,
    JetSkiesWrapper,
} from './Reservation.style.js';
import {Option} from "antd/es/mentions";
import {value} from "lodash/seq";

const RenderReservationForm = (props) => {
    const [formState, setFormState] = useState({
        date: null,
        startTime: null,
        endTime: null,
        trip: null,
        jetSkies: 0,
    });

    const handleIncrement = (type) => {
        setFormState({
            ...formState,
            [type]: formState[type] + 1,
        });
    };
    const handleDecrement = (type) => {
        if (formState[type] <= 0) {
            return false;
        }
        setFormState({
            ...formState,
            [type]: formState[type] - 1,
        });
    };
    const handleIncDecOnChnage = (e, type) => {
        let currentValue = e.target.value;
        setFormState({
            ...formState,
            [type]: currentValue,
        });
    };
    const updateSearchDateFunc = (value) => {
        setFormState({
            ...formState,
            date: value
        });
    };
    const updateSearchTimeFunc = (value) => {
        setFormState({
            ...formState,
            startTime: value[0],
            endTime: value[1]
        });
    };
    const handleTripOnChange = (value) => {
        setFormState({
            ...formState,
            trip: value,
        });
    }
    const handleSubmit = (e) => {
        e.preventDefault();
        alert(
            `Date: ${formState.date}\nStart Time: ${formState.startTime}\nEnd Time: ${formState.endTime}\nTrip: ${formState.trip}\nJetSkies: ${formState.jetSkies}`
        );
    };

    return (
        <ReservationFormWrapper className="form-container" onSubmit={handleSubmit}>
            <FieldWrapper>
                <HtmlLabel htmlFor="date" content="Date"/>
                <DatePicker
                    id={"date"}
                    size={"large"}
                    style={
                        {
                            padding: "8px",
                            height: "54px",
                            width: "100%",
                            border: "none",
                            background: "whitesmoke"
                        }
                    }
                    onChange={(date, dateString) => updateSearchDateFunc(dateString)}
                />
            </FieldWrapper>
            <FieldWrapper>
                <HtmlLabel htmlFor="time" content="Time"/>
                <TimePicker.RangePicker
                    id={"time"}
                    format={'HH:mm'}
                    size={"large"}
                    minuteStep={30}
                    disabledHours={() => [0, 1, 2, 3, 4, 5, 6, 7].concat([21, 22, 23])}
                    hideDisabledOptions
                    style={
                        {
                            padding: "8px",
                            height: "54px",
                            width: "100%",
                            border: "none",
                            background: "whitesmoke"
                        }
                    }
                    onChange={(time,timeString ) => updateSearchTimeFunc(timeString)}
                />
            </FieldWrapper>
            <FieldWrapper>
                <HtmlLabel htmlFor="jetSkies" content="Jet Skies"/>
                <ViewWithPopup
                    key={200}
                    noView={true}
                    className={formState.jetSkies || formState.guest ? 'activated' : ''}
                    view={
                        <Button type="default">
                            <span>Jet Skies {formState.jetSkies > 0 && `: ${formState.jetSkies}`}</span>
                            {/*<span>-</span>*/}
                            {/*<span>Guest{formState.guest > 0 && `: ${formState.guest}`}</span>*/}
                        </Button>
                    }
                    popup={
                        <JetSkiesWrapper>
                            <ItemWrapper>
                                <strong>Jet Skies</strong>
                                <InputIncDec
                                    id="jetSkies"
                                    increment={() => handleIncrement('jetSkies')}
                                    decrement={() => handleDecrement('jetSkies')}
                                    onChange={(e) => handleIncDecOnChnage(e, 'jetSkies')}
                                    value={formState.jetSkies}
                                />
                            </ItemWrapper>
                        </JetSkiesWrapper>
                    }
                />
            </FieldWrapper>

            <FieldWrapper>
                <HtmlLabel htmlFor="trip" content="Trip"/>
                <Select
                    size={"large"}
                    bordered={false}
                    style={
                        {
                            padding: "8px",
                            height: "54px",
                            width: "100%",
                            background: "whitesmoke"
                        }
                    }
                    defaultValue="Select Trip"
                    onChange={(value) => handleTripOnChange(value)}
                >
                    {
                        props.trips.map(trip => {
                            return (
                                <Option style={{width: "100%"}} value={trip.id}>
                                    {trip.title}
                                </Option>
                            );
                        })
                    }
                </Select>
            </FieldWrapper>

            <FormActionArea>
                <Button htmlType="submit" type="primary">
                    Book
                </Button>
            </FormActionArea>
        </ReservationFormWrapper>
    );
};

export default RenderReservationForm;
