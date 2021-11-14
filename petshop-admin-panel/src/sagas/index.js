import {take, put, all, call, fork} from "redux-saga/effects";
import * as localStorageService from "../services/localStorageService.js"

import {
    REQUEST_LOGIN,
    DoLogin,
    receiveLoginInfo,
    REQUEST_LOGOUT, receiveLogout
} from "../actions/loginActions.js";

export function* startup() {
}

function* watchLogin() {
    while (true) {
        const loginRequest = yield take(REQUEST_LOGIN);
        try {
            const response = yield call(DoLogin, loginRequest.payload.loginData);
            console.log(response.headers['authorization']);
            if (response.status === 200) {
                let loginResponse = {
                    accessToken: response.headers['authorization'],
                    refreshToken: response.headers['refreshtoken']
                };
                localStorageService.writeTokenToLocalStorage(loginResponse);
                yield put(receiveLoginInfo(loginResponse));
            } else {
                throw response;
            }
        } catch (e) {
            console.log(e);
        }
    }
}

function* watchLogout() {
    while (true) {
        const response = yield take(REQUEST_LOGOUT);
        localStorage.clear();
        yield put(receiveLogout());
    }
}


export default function* root() {
    yield fork(startup);
    yield fork(watchLogin);
    yield fork(watchLogout);
}
