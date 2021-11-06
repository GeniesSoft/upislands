import BaseApi from "./BaseApi";

class CrudApi extends BaseApi {

    async create(createRequest) {
        return await this.axios.post(this.PATH_BASE, createRequest);
    }

    async read(id) {
        return this.axios.axios.get(this.PATH_BASE + `/${id}`)
    }

    async update(updateRequest) {
        return await this.axios.put(this.PATH_BASE, updateRequest)
    }

    async delete(id) {
        return await this.axios.delete(this.PATH_BASE + `/${id}`)
    }

}

export default CrudApi;