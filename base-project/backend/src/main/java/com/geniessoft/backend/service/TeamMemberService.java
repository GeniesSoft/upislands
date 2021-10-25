package com.geniessoft.backend.service;

import com.geniessoft.backend.model.TeamMember;
import com.geniessoft.backend.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberService(final TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    public List<TeamMember> getAllTeamMembers() {
        return teamMemberRepository.findAll();
    }

}
