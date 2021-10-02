import {createSlice} from '@reduxjs/toolkit';
import {apiCallBegan} from "./apiactions";

const url = '/v1/auth';

const slice = createSlice({
    name: 'login',
    initialState: {
        loginData: {
            token: null,
            refreshToken: null
        },
        loading: false,
        lastFetch: null,
    },
    reducers: {
        loginRequested: (login, action) => {
            login.loading = true;
        },

        loginReceived: (login, action) => {
            console.log(action.payload);
            login.loginData.token = action.payload.headers["Authorization"];
            login.loginData.refreshToken = action.payload.headers["RefreshToken"]
            login.loading = false;
            login.lastFetch = Date.now();
        },

        loginRequestFailed: (login, action) => {
            console.log(action.payload);
            login.loading = false;
        },
    },
});

export default slice.reducer;
const {loginRequested, loginReceived, loginRequestFailed} = slice.actions;

export const loginUser = (loginRequest) =>
    apiCallBegan({
        url: url + "/login",
        method: 'post',
        data: loginRequest,
        onStart: loginRequested.type,
        onSuccess: loginReceived.type,
        onError: loginRequestFailed.type
    });
