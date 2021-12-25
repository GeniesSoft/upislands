import CrudApi from "../CrudApi";
import axios from "axios";
import {API_BASE_URL} from "../../constants";

class BookingApi extends CrudApi {

}

export default new BookingApi(
    axios.create(
        {
            baseURL: API_BASE_URL + "/booking"
        }
    )
);