import axios from 'axios';
import {API_BASE_URL} from '../constants';

class TeamMemberApi {

    async getAllTeamMembers() {
        const getResponse = await axios.get(API_BASE_URL + "/team-members");
        return getResponse.data;
    }

}

export default new TeamMemberApi();