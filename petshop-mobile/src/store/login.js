import {createSlice} from '@reduxjs/toolkit';
import {apiCallBegan} from "./apiactions";
import {cleanTokens, getData, storeData} from "../common/asyncStorageService";

const url = '/v1/auth';


const initialState = {
    token: "",
    refreshToken: "",
    loggedIn: false,
    loading: false,
    lastFetch: "",
}

const slice = createSlice({
    name: 'login',
    initialState: initialState,
    reducers: {
        initToken: (login, action) => {
            login.token = action.payload.data.token;
            login.refreshToken = action.payload.data.refreshToken;
            login.loggedIn = action.payload.data.token ? true : false;
        },
        loginRequested: (login, action) => {
            console.log("login requested");
            login.loading = true;
        },

        loginReceived: (login, action) => {
            console.log("login received");
            const token = action.payload.headers["authorization"];
            const refreshToken = action.payload.headers["refreshtoken"];
            storeData("accessToken", token);
            storeData("refreshToken", refreshToken);
            login.token = token;
            login.refreshToken = refreshToken;
            login.loggedIn = true;
            login.lastFetch = Date.now();
            console.log(token);
            login.loading = false;
        },

        loginRequestFailed: (login, action) => {
            console.log("login failed");
            login.loading = false;
        },
        logout: (login, action) => {
            console.log("logout");
            cleanTokens();
            login.loggedIn = initialState.loggedIn;
            login.token = initialState.token;
            login.refreshToken = initialState.refreshToken;
            login.loading = initialState.loggedIn;
            login.lastFetch = initialState.lastFetch;

        }
    },
});
export const {initToken, loginRequested, loginReceived, loginRequestFailed, logout} = slice.actions;


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