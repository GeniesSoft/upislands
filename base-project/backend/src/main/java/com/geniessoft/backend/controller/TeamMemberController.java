package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.GetTeamMemberResponse;
import com.geniessoft.backend.service.TeamMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team-members")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    public TeamMemberController(final TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    @GetMapping
    public ResponseEntity<List<GetTeamMemberResponse>> getAllTeamMembers() {
        return ResponseEntity.ok().body(
                teamMemberService.getAllTeamMembers()
                        .stream()
                        .map(GetTeamMemberResponse::new).collect(Collectors.toList())
        );
    }

}
