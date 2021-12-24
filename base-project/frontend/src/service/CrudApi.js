import Notify from "./notification/Notify";
import axios from "axios";

class CrudApi {

    constructor(axios) {
        this.axios = axios;
    }

    create(createRequest) {
        const pid = Notify.startProcess("Create request is pending...");
        this.axios
            .post("", createRequest)
            .then(response => {
                Notify.updateProcess(pid, "success", response.data);
            })
            .catch(error => {
                Notify.updateProcess(pid, "error", "Create request failed!");
                this.handleError(error);
            })
    }

    async read(id) {
        return this.axios
            .get(`/${id}`)
            .catch(error => {
                this.handleError(error);
            });
    }

    async readAll() {
        return this.axios
            .get("")
            .catch(error => {
                this.handleError(error);
            });
    }

    update(updateRequest) {
        const pid = Notify.startProcess("Update request is pending...");
        this.axios
            .put("", updateRequest)
            .then(response => {
                Notify.updateProcess(pid, "success", response.data);
            })
            .catch(error => {
                Notify.updateProcess(pid, "error", "Update request failed!");
                this.handleError(error);
            });
    }

    delete(id) {
        const pid = Notify.startProcess("Delete request is pending...");
        this.axios
            .delete(`/${id}`)
            .then(response => {
                Notify.updateProcess(pid, "success", response.data);
            })
            .catch(error => {
                Notify.updateProcess(pid, "error", "Delete request failed!");
                this.handleError(error);
            });
    }

    async specific(method, address, request) {
        const pid = Notify.startProcess("Request is pending...");
        switch (method){
            case "POST":
                axios
                    .post(address, request)
                    .then(response => {
                        Notify.updateProcess(pid, "success", response.data);
                    })
                    .catch(error => {
                        Notify.updateProcess(pid, "error", "Request failed!");
                        this.handleError(error);
                    });
                break;
            default:
                Notify.updateProcess(pid, "error", "Request failed!");
                break;
        }
    }

    handleError(error) {
        if (!error.response) {
            Notify.error("Network Error");
        } else {
            switch (error.response.status) {
                case 400:
                    console.log(error.response);
                    error.response.data.forEach(Notify.error);
                    break;
                case 401:
                    console.log(error.response);
                    Notify.error("Unauthorized");
                    break;
                case 404:
                    error.response.data.forEach(Notify.error);
                    break;
                case 405:
                    console.log(error.response);
                    Notify.error("Method Not Allowed");
                    break;
                case 409:
                    error.response.data.forEach(Notify.error);
                    break;
                case 500:
                    console.log(error.response);
                    Notify.error("Server Error");
                    break;
                default:
                    Notify.error(error.response);
                    break;
            }
        }

    }

}

export default CrudApi;