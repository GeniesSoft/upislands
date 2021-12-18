import CrudApi from "../CrudApi";
import axios from "axios";
import {API_BASE_URL} from "../../constants";

class CompanyApi extends CrudApi {

    addLocation(companyId, locationId) {
        this.specific("POST",`${API_BASE_URL}/company/${companyId}/${locationId}`, null);
    }

}

export default new CompanyApi(
    axios.create(
        {
            baseURL: API_BASE_URL + "/company"
        }
    )
);