class BaseApi {

    constructor(basePath, axios) {
        this.PATH_BASE = basePath;
        this.axios = axios;
    }

}

export default BaseApi;