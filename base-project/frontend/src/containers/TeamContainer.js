import {Grid} from "@mui/material";
import TeamMember from "../components/TeamMember";
import {useEffect, useState} from "react";
import TeamMemberApi from "../api/TeamMemberApi";

const TeamContainer = () => {

    const [memberList, updateMemberList] = useState([]);

    const fetchMembers = () => {
        TeamMemberApi.getAllTeamMembers()
            .then(data => updateMemberList(data))
            .catch(error => console.log(error));
    }

    useEffect(() => {
        fetchMembers();
    }, []);

    return (
        <Grid
            container
            direction="row"
            justifyContent="center"
            alignItems="center"
            spacing={1}
        >

            {
                memberList.map(member => {
                    return (
                        <Grid key={member.name} item xs={12} sm={6} md={4} lg={3}>
                            <TeamMember member={member} />
                        </Grid>
                    )
                })
            }

        </Grid>
    );

}

export default TeamContainer;