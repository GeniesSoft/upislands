import React, {useContext} from 'react';
import {LuxaryHotelsGrid, TopHotelsGrid} from './Grid';
import SearchArea from './Search/Search';
import LocationGrid from './Location/Location';
import {LayoutContext} from 'context/LayoutProvider';
import {Waypoint} from 'react-waypoint';
import {Button} from "antd";
import UserApi from "../../service/user/api/UserApi";
import user from "../../service/user/object/user.json"
import TestView from "../../service/test/TestView";

const Home = () => {
    const [, dispatch] = useContext(LayoutContext);



    return (
        <>
            <SearchArea/>
            <Waypoint
                onEnter={() => dispatch({type: 'HIDE_TOP_SEARCHBAR'})}
                onLeave={() => dispatch({type: 'SHOW_TOP_SEARCHBAR'})}
            />
            <LocationGrid/>
            <TopHotelsGrid/>
            <LuxaryHotelsGrid/>
        </>
    );
};

export default Home;
