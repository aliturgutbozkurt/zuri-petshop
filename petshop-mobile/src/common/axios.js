import axios from "axios";



const http = axios.create();

let store

export const injectStore = _store => {
    store = _store
}

// Add a request interceptor
http.interceptors.request.use(
    config => {
        const token = store.getState().entities.login.token;
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