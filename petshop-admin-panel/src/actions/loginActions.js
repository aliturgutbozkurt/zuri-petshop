import request from "../common/request.js";

export const REQUEST_LOGIN = "REQUEST_LOGIN";
export const LOGIN_INFO_RECEIVED = "LOGIN_INFO_RECEIVED";
export const REQUEST_ERROR = "REQUEST_ERROR";
export const REQUEST_LOGOUT = "REQUEST_LOGOUT";
export const LOGOUT_SUCCESS = "LOGOUT_SUCCESS"

export const receiveLoginInfo = loginReponse => {
    return {
        type: LOGIN_INFO_RECEIVED,
        payload: {
            loginReponse: loginReponse,
            loggedIn: true
        }
    };
}

export const requestLogin = (email, password) => {
    const loginData = {
        email: email,
        password: password
    };
    return {
        type: REQUEST_LOGIN,
        payload: {loginData, loggedIn: false}
    };
};

export const requestLogout = () => {
    return {
        type: REQUEST_LOGOUT,
        loggedIn: true
    }
}
export const receiveLogout = () => {
    return {
        type: LOGOUT_SUCCESS,
        loggedIn: false
    }
}
export const DoLogin = data => {
    const loginRequest = {
        method: "POST",
        path: "/api/v1/auth/login",
        data: data,
        responseType: "text"
    }

    return request(loginRequest);
    /**
     * componentRoute bilgisine göre sorgu atılır. Dönen response kullanılır
     */
    //let access_token = "asdasdqwe123qd1e213qsd123"

    //window.localStorage.setItem("access_token",access_token)
};

