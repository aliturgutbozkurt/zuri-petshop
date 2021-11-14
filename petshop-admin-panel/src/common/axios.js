import axios from "axios";
import * as localStorageService from "../services/localStorageService.js";

const http = axios.create();

// Add a request interceptor
http.interceptors.request.use(
    config => {
        const token = localStorageService.getAccessToken();
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