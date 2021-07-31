/*import React, { useEffect } from 'react';
import http from "./axios";
import { addData, getData, removeData } from '../localStorage';
import { refreshToken, logOut } from '../apiUtils/index';
const UNAUTHORIZED = 401;
const BAD_REQUEST = 400;

const httpHandler = Wrapped => {
    function checkRequests(props) {
        useEffect(() => {
            http.interceptors.response.use(function (response) {
                // Do something with response data
                return response;
            }, async function (error) {
                const originalRequest = error.config;
                switch (error.response.status) {
                    case UNAUTHORIZED:
                        if (originalRequest.url.includes('login')) {
                            showErrorMessage(error);
                        } else {
                            // try refresh token
                            if (originalRequest.url.includes('refreshToken')) {
                                logOut();
                                return Promise.reject(error);
                            }
                            if (!originalRequest._retry) {
                                originalRequest._retry = true;
                                let refToken = await getData(GLOBALCONSTANTS.LOGIN.REFRESH_TOKEN);
                                return new Promise(function (resolve, reject) {
                                    refreshToken(refToken)
                                        .then(res => {
                                            if (res.status === 200 || res.status === 201) {
                                                addData(GLOBALCONSTANTS.LOGIN.TOKEN, `Bearer ${res.data.data.accessToken}`);
                                                addData(GLOBALCONSTANTS.LOGIN.REFRESH_TOKEN, `Bearer ${res.data.data.refreshToken}`);
                                                originalRequest.headers['Authorization'] = `Bearer ${res.data.data.accessToken}`;
                                                resolve(http(originalRequest));
                                            }
                                        })
                                        .catch((err) => {
                                            return reject(err);
                                        })
                                })
                            }
                        }
                        break;
                    case BAD_REQUEST:
                        showBadRequestMessage(error);
                        break;
                    default:
                        showErrorMessage(error);
                        break;
                }
                return Promise.reject(error);
            });
        });
        return (
            <Wrapped {...props} />
        );
    }
    return checkRequests;
};
const showErrorMessage = error => {
    let message = '';
    if (error.response.data.message) {
        message = error.response.data.message;
    } else {
        message = error.response.data.error;
    }
    
    console.log(`HATA(Hata Kodu: ${error.response.status} ): ${message}`);

};

const showBadRequestMessage = error => {
    const message = 'Hatalı İstek';
    console.log(`HATA(Hata Kodu: ${error.response.status} ): ${message}`);
};
export default httpHandler;*/