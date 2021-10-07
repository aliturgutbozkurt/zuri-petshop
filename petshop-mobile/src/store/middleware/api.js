import axios from 'axios';
import * as actions from '../apiactions';
import http from "../../common/axios";

const api = ({dispatch}) => (next) => async (action) => {
    if (action.type !== actions.apiCallBegan.type) return next(action);

    const {url, method, data, onStart, onSuccess, onError} = action.payload;

    if (onStart) dispatch({type: onStart});

    next(action);

    try {
        console.log(data);
        const response = await http.request({
            baseURL: 'http://172.31.176.1:8081/api',
            url,
            method,
            data,
        });
        // General
        dispatch(actions.apiCallSuccess(response.data));
        // Specific
        if (onSuccess) dispatch({type: onSuccess, payload: {data: response.data, headers: response.headers}});
    } catch (error) {
        // General
        console.log(error.message);
        dispatch(actions.apiCallFailed(error.message));
        // Specific
        if (onError) dispatch({type: onError, payload: error.message});
    }
};

export default api;
