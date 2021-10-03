import {createSlice} from '@reduxjs/toolkit';
import {apiCallBegan} from "./apiactions";
import {getData, storeData} from "../common/asyncStorageService";

const url = '/v1/auth';

let token = null;
let refreshToken = null;

getData("accesstoken").then(value => {
    token = value;
});
getData("refreshToken").then(value => {
    refreshToken = value;
});

const slice = createSlice({
    name: 'login',
    initialState: {
        loginData: {
            token: token,
            refreshToken: refreshToken
        },
        loggedIn: !!token,
        loading: false,
        lastFetch: null,
    },
    reducers: {
        loginRequested: (login, action) => {
            console.log("login requested");
            login.loading = true;
        },

        loginReceived: (login, action) => {
            console.log("login received");
            const token = action.payload.headers["authorization"];
            login.loginData.token = token;
            storeData("accesstoken", token);
            const refreshToken = action.payload.headers["refreshToken"];
            login.loginData.refreshToken = refreshToken;
            storeData("refreshToken", refreshToken);
            login.loading = false;
            login.lastFetch = Date.now();
            console.log(token);

        },

        loginRequestFailed: (login, action) => {
            console.log("login failed");
            login.loading = false;
        },
    },
});
export const {loginRequested, loginReceived, loginRequestFailed} = slice.actions;


export const loginUser = (loginRequest) =>
    apiCallBegan({
        url: url + "/login",
        method: 'post',
        data: loginRequest,
        onStart: loginRequested.type,
        onSuccess: loginReceived.type,
        onError: loginRequestFailed.type
    });
export default slice.reducer;