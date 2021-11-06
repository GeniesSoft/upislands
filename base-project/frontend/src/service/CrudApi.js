import BaseApi from "./BaseApi";

class CrudApi extends BaseApi {

    async create(createRequest) {
        return await this.axios.post(createRequest);
    }

    async read(id) {
        return this.axios.axios.get(`/${id}`)
    }

    async update(updateRequest) {
        return await this.axios.put(updateRequest)
    }

    async delete(id) {
        return await this.axios.delete(`/${id}`)
    }

}

export default CrudApi;