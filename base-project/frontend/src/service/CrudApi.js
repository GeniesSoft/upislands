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
                switch (error.response.status) {
                    case 400:
                        error.response.data.forEach(Notify.error);
                        break;
                    default:
                        Notify.error(error.response);
                        break;
                }
            })
    }

    async read(id) {
        return this.axios
            .get(`/${id}`)
            .catch(error => {
                switch (error.response.status) {
                    case 400:
                        error.response.data.forEach(Notify.error);
                        break;
                    default:
                        Notify.error(error.response);
                        break;
                }
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
                switch (error.response.status) {
                    case 400:
                        error.response.data.forEach(Notify.error);
                        break;
                    default:
                        Notify.error(error.response);
                        break;
                }
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
                switch (error.response.status) {
                    case 400:
                        error.response.data.forEach(Notify.error);
                        break;
                    default:
                        Notify.error(error.response);
                        break;
                }
            });
    }

}

export default CrudApi;