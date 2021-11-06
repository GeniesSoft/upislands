import CrudApi from "../CrudApi";
import axios from "axios";
import {API_BASE_URL} from "../../constants";

class LocationApi extends CrudApi {

}

export default new LocationApi(
    axios.create(
        {
            baseURL: API_BASE_URL,
            url: "/locations"
        }
    ));