import axios from "axios";
import {API_BASE_URL} from "../../constants";
import CrudApi from "../CrudApi";

class BookingApi extends CrudApi {

}

export default new BookingApi(
    axios.create(
        {
            baseURL: API_BASE_URL + "/booking"
        }
    )
)

