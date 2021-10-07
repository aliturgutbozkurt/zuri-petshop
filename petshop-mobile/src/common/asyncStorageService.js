import AsyncStorage from '@react-native-async-storage/async-storage';

export const storeData = async (key, value) => {
    try {
        AsyncStorage.setItem(key, value)
    } catch (e) {
        console.log(e.message);
    }
}

const storeJsonData = async (key, value) => {
    try {
        const jsonValue = JSON.stringify(value)
        await AsyncStorage.setItem(key, jsonValue)
    } catch (e) {
        console.log(e.message);
    }
}

export const getData = async (key) => {
    try {
        const value = await AsyncStorage.getItem(key);
        return value;
    } catch (e) {
        console.log(e.message);
    }
}

const getJsonData = async (key) => {
    try {
        const jsonValue = await AsyncStorage.getItem(key);
        return jsonValue != null ? JSON.parse(jsonValue) : null;
    } catch (e) {
        console.log(e.message);
    }
}

export const getAccessToken = async () => {
    try {
        const value = await AsyncStorage.getItem("accessToken");
        return value;
    } catch (e) {
        console.log(e.message);
    }
}

export const getRefreshToken = async () => {
    try {
        const value = await AsyncStorage.getItem("refreshToken");
        return value;
    } catch (e) {
        console.log(e.message);
    }
}

export const cleanTokens =async () => {
    try {
        await AsyncStorage.removeItem("accessToken");
        await AsyncStorage.removeItem("refreshToken");
    } catch (e) {
        console.log(e.message);
    }
}