import Notify from "./notification/Notify";

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

    handleError(error) {
        switch (error.response.status) {
            case 400:
                console.log(error.response.data);
                error.response.data.forEach(Notify.error);
                break;
            case 404:
                Notify.error(error.response.data);
                break;
            case 409:
                Notify.error(error.response.data);
                break;
            default:
                Notify.error(error.response);
                break;
        }
    }

}

export default CrudApi;