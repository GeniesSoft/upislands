import CrudApi from "../CrudApi";
import axios from "axios";
import {API_BASE_URL} from "../../constants";

class LocationApi extends CrudApi {

    async readAllFrontend() {
        return this.axios
            .get("/frontend")
            .catch(error => {
                this.handleError(error);
            });
    }

}

export default new LocationApi(
    axios.create(
        {
            baseURL: API_BASE_URL + "/locations"
        }
    )
);