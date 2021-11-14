import http from "../common/axios.js";
import {BASE_URL} from "../env.json";
import {trackPromise} from "react-promise-tracker";
import { store } from '../index';
//reduxdaki state değişkenleri import ettikten sonra const state = store.getState(); kullanılabilinir.


export const request = (options) => {
    let url= options.params ?  `${BASE_URL}${options.path}?`: `${BASE_URL}${options.path}`
    if (options.params && options.params.length) {
        options.params.map((par, index) => {
            return (url += par + "=" + options.values[index] + "&");
        });
    }
    return trackPromise(http({
        method: options.method,
        url: url,
        data: options.data,
        responseType: options.responseType
    }))
        .then(response => response)
        .catch(error => {
            console.log(error.response);
        });
};

export const requestAsync = async (options) => {
    let url= options.params ?  `${BASE_URL}${options.path}?`: `${BASE_URL}${options.path}`
    if (options.params && options.params.length) {
        options.params.map((par, index) => {
            return (url += par + "=" + options.values[index] + "&");
        });
    }
    return await http({
        method: options.method,
        url: url,
        data: options.data,
        responseType: options.responseType
    })
        .then(response => response.data)
        .catch(error => {
            console.log(error.response);
        });
};

export async function requestAll(requestOptionArray) {
    let optionsArray = [];
    if (requestOptionArray.length) {
        optionsArray = requestOptionArray.map((param, index) => {
            return request(requestOptionArray[index]);
        });
        return await  trackPromise(Promise.all(optionsArray));
    }
}

export default request;