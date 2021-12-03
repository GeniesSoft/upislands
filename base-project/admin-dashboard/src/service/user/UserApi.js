import CrudApi from "../CrudApi";
import axios from "axios";
import {API_BASE_URL} from "../../constants";

class UserApi extends CrudApi {

}

export default new UserApi(
    axios.create(
        {
            baseURL: API_BASE_URL + "/users"
        }
    )
);