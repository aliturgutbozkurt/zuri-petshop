import * as SecureStore from "expo-secure-store";
import jwtDecode from "jwt-decode";

const authTokenKey = "authToken";
const refreshTokenKey = "refreshToken";

const storeToken = async (authToken) => {
    try {
        await SecureStore.setItemAsync(authTokenKey, authToken);
    } catch (error) {
        console.log("Error storing the auth token", error);
    }
};

const getToken = async () => {
    try {
        return await SecureStore.getItemAsync(authTokenKey);
    } catch (error) {
        console.log("Error getting the auth token", error);
    }
};

const storeRefreshToken = async (refreshToken) => {
    try {
        await SecureStore.setItemAsync(refreshTokenKey, refreshToken);
    } catch (error) {
        console.log("Error storing the refresh token", error);
    }
};

const getRefreshToken = async () => {
    try {
        return await SecureStore.getItemAsync(refreshTokenKey);
    } catch (error) {
        console.log("Error getting the refresh token", error);
    }
};

const getUser = async () => {
    const token = await getToken();
    return token ? jwtDecode(token) : null;
};

const removeToken = async () => {
    try {
        await SecureStore.deleteItemAsync(authTokenKey);
    } catch (error) {
        console.log("Error removing the auth token", error);
    }
};

const removeRefreshToken = async () => {
    try {
        await SecureStore.deleteItemAsync(refreshTokenKey);
    } catch (error) {
        console.log("Error removing the refresh token", error);
    }
};

export default {getToken, getRefreshToken, getUser, removeToken, removeRefreshToken, storeToken, storeRefreshToken};
