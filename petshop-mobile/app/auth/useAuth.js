import {useContext} from "react";
import jwtDecode from "jwt-decode";

import AuthContext from "./context";
import authStorage from "./storage";

const useAuth = () => {
    const {user, setUser} = useContext(AuthContext);

    const logIn = (result) => {
        const authToken = result.headers["authorization"];
        const refreshToken = result.headers["refreshtoken"];
        const user = jwtDecode(authToken);
        setUser(user);
        authStorage.storeToken(authToken);
        authStorage.storeRefreshToken(refreshToken);
    };

    const logOut = () => {
        setUser(null);
        authStorage.removeToken();
        authStorage.removeRefreshToken();
    };

    return {user, logIn, logOut};
};
export default useAuth;