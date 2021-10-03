import axios from "axios";
import {store} from "../../App";

const http = axios.create();

// Add a request interceptor
http.interceptors.request.use(
    config => {
        const token = store.getState().entities.login.loginData.token;
        if (token) {
            config.headers['Authorization'] = token;
        }
         config.headers['Content-Type'] = 'application/json';
        return config;
    },
    error => {
        Promise.reject(error)
    });

export default http;