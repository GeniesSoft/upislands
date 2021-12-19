import {useEffect, useReducer, useState} from 'react';
import {getCurrentUser} from "../../service/auth/AuthApi";
import LocationApi from "../../service/location/LocationApi";

async function SuperFetch(
    url,
    method = 'GET',
    headers = {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
    },
    body = {}
) {
    let options = {
        method,
        headers,
    };
    if (method === 'POST' || method === 'PUT') options = {...options, body};

    // authentication
    // we will had custom headers here.

    if (url == 'getCurrentUser' && false) {
        let resd = {}
        await getCurrentUser().then(res=>{
            resd = res
        })
        return [resd]
    }
    else if(url == 'findAllLocations' && false){
        let resd = {}
        await LocationApi.readAll().then(res => {
            resd = res.data
            console.log(resd)
        })
        return [resd]
    }
    else {
        return fetch(url, options)
            .then(res => {
                return Promise.resolve(res.json());
            })
            .catch(error => Promise.reject(error));
    }


}

function dataFetchReducer(state, action) {
    switch (action.type) {
        case 'FETCH_INIT':
            return {
                ...state,
                loading: true,
                error: false,
            };
        case 'FETCH_SUCCESS':
            return {
                ...state,
                data: action.payload,
                total: action.payload,
                loading: false,
                error: false,
            };
        case 'FETCH_FAILURE':
            return {
                ...state,
                loading: false,
                error: true,
            };
        case 'LOAD_MORE':
            return {
                ...state,
                data: [
                    ...state.data,
                    ...state.total,
                ],
                loading: false,
                error: false,
            };
        default:
            throw new Error();
    }
}

const useDataApi = (initialUrl, limit = 10, initialData = []) => {
    const [url, setUrl] = useState(initialUrl);

    const [state, dispatch] = useReducer(dataFetchReducer, {
        loading: false,
        error: false,
        data: initialData,
        total: initialData,
        limit: limit,
    });

    useEffect(() => {
        let didCancel = false;

        const fetchData = async () => {
            dispatch({type: 'FETCH_INIT'});

            try {
                const result = await SuperFetch(url);
                if (!didCancel) {
                    dispatch({type: 'FETCH_SUCCESS', payload: result });
                }
            } catch (error) {
                if (!didCancel) {
                    dispatch({type: 'FETCH_FAILURE'});
                }
            }
        };

        fetchData();

        return () => {
            didCancel = true;
        };
    }, [url]);
    const loadMoreData = () => {
        dispatch({type: 'LOAD_MORE'});
    };
    const doFetch = url => {
        setUrl(url);
    };

    return {...state, doFetch, loadMoreData};
};

export default useDataApi;
