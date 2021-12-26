import axios from "axios";
import {API_BASE_URL, BASE_URL} from "../../constants";
import Notify from "../notification/Notify";
import {useHistory} from "react-router-dom";

export const USER_ID = "userid"
export const USER_NAME = "username"
export const USER_ROLE = "role"
export const USER_TOKEN = "token"

class AuthApi {

    constructor(axios) {
        this.axios = axios;
    }

    async login(email, password) {
        let logedIn = false;

        await this.axios
            .post("/signin", {email: email, password: password})
            .then(response => {
                this.registerSuccessfulLogin(response.data);
                Notify.success("Welcome Back");
                logedIn = true;
            }).catch( error => {
                switch (error.response.status) {
                    default:
                        Notify.error("Error " + error.response.status);
                        break;
                }
                logedIn = false;
            });

        return logedIn;
    }

    logout() {
        sessionStorage.clear();
    }

    registerSuccessfulLogin(data) {
        sessionStorage.setItem(USER_TOKEN, `${data.tokenType} ${data.accessToken}`);
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_TOKEN);
        return user !== null;
    }

    getUserRole() {
        return this.isUserLoggedIn() ? "ADMIN" : null;
    }

    getUserToken() {
        return sessionStorage.getItem(USER_TOKEN);
    }

}

export default new AuthApi(
    axios.create(
        {
            baseURL: BASE_URL + "/auth"
        }
    )
);