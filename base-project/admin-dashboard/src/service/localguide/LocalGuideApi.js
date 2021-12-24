import CrudApi from "../CrudApi";
import axios from "axios";
import {API_BASE_URL} from "../../constants";

class LocalGuideApi extends CrudApi {

}

export default new LocalGuideApi(
    axios.create(
        {
            baseURL: API_BASE_URL + "/local-guide"
        }
    )
);