package com.geniessoft.backend.dto;

import com.geniessoft.backend.model.TeamMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTeamMemberResponse {

    private String name;
    private String speciality;
    private String mail;
    private String address;
    private String github;
    private String linkedin;

    public GetTeamMemberResponse(TeamMember teamMember) {
        this(
                teamMember.getName(),
                teamMember.getSpeciality(),
                teamMember.getMail(),
                teamMember.getAddress(),
                teamMember.getGithub(),
                teamMember.getLinkedin()
        );
    }
}
