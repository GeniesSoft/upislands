import CrudApi from "../../CrudApi";
import axios from "axios";
import {API_BASE_URL} from "../../../constants";

class LocationApi extends CrudApi {

}

export default new LocationApi("/locations", axios.create({baseURL: API_BASE_URL}));