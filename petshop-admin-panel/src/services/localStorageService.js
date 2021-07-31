import jwtDecoder from 'jwt-decode';

export const writeTokenToLocalStorage = data => {
    if (data) {
        localStorage.setItem("access_token", data.accessToken);
        localStorage.setItem("refresh_token", data.refreshToken);
    }
}

export const getAccessToken = () => {
    if (localStorage.getItem("access_token") !== null && localStorage.getItem("access_token") !== undefined) {
        return localStorage.getItem("access_token");
    }
}

export const getRefreshToken = () => {
    return localStorage.getItem("refresh_token")
}

export const decodeJwt = tokenType => {
    if (checkTokenExist()) {
        return jwtDecoder(localStorage.getItem("access_token"));
    }

}

export const getLoggedInDetails = tokenType => {
    if (checkTokenExist()) {
        const decodedToken = decodeJwt("access_token");
        return decodedToken.details
    }

}

export const getUserRole = tokenType => {
    if (checkTokenExist()) {
        const decodedToken = decodeJwt("access_token");
        return decodedToken.details.roleType;
    }

}

export const getFullName = tokenType => {
    if (checkTokenExist()) {
        const decodedToken = decodeJwt("access_token")
        return decodedToken.details.fullName;
    }

}

export const getUserName = tokenType => {
    if (checkTokenExist()) {
        const decodedToken = decodeJwt("access_token");
        return decodedToken.details.userName;
    }

}

const checkTokenExist = () => {
    return localStorage.getItem("access_token") ? true : false;
}

export const setAccessToken = token => {
    localStorage.setItem("access_token", token)
}

