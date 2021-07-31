import {
    REQUEST_LOGOUT,
    REQUEST_LOGIN,
    REQUEST_ERROR,
    LOGIN_INFO_RECEIVED,
    LOGOUT_SUCCESS
} from "../actions/loginActions";
import * as localStorageService from "../services/localStorageService"

const initialState = {
    error: false,
    loggedIn: localStorageService.getAccessToken() ? true : false,
    logged_in_user_details: localStorageService.getLoggedInDetails("access_token")

};

export default (state = initialState, action) => {
    switch (action.type) {
        case REQUEST_LOGIN:
            return {
                ...state,
                login_data: action.payload.loginData,
                loggedIn: false
            };
        case LOGIN_INFO_RECEIVED:
            return {...state, login_info: action.payload, loggedIn: true};
        case REQUEST_LOGOUT :{
            return state;
        }
        case LOGOUT_SUCCESS:
            return {
                ...state,
                loggedIn: false
            };
        default:
            return state;
    }
};
