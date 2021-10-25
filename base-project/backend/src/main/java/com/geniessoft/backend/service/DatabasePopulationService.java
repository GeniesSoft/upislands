package com.geniessoft.backend.service;

import com.geniessoft.backend.model.TeamMember;
import com.geniessoft.backend.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabasePopulationService {

    private final TeamMemberRepository teamMemberRepository;

    public DatabasePopulationService(final TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    public void populateDatabase() {
        populateTeamMembers();
    }

    private void populateTeamMembers() {

        teamMemberRepository.save(new TeamMember(
                "Batın Özkurt",
                "DBMS Specialist",
                "batin@mail.com",
                "Tepebaşı/Eskişehir, Turkey",
                "https://github.com/batinozkurt",
                "https://www.linkedin.com/in/bat%C4%B1n-%C3%B6zkurt-90a568207/"
        ));

        teamMemberRepository.save(new TeamMember(
                "Mert Yüksek",
                "System Engineer",
                "pert@mail.com",
                "Tepebaşı/Eskişehir, Turkey",
                "https://github.com/MertYuksek",
                "https://www.linkedin.com/in/mert-y%C3%BCksek-84b62b18a/"
        ));

        teamMemberRepository.save(new TeamMember(
                "Mustafa Barış Ege",
                "Android Developer",
                "boris@mail.com",
                "Tepebaşı/Eskişehir, Turkey",
                "https://github.com/ML-std",
                "https://www.linkedin.com/in/mustafa-baris-ege/"
        ));

        teamMemberRepository.save(new TeamMember(
                "Muzaffer Verdil",
                "Frontend Developer",
                "verdil@mail.com",
                "Tepebaşı/Eskişehir, Turkey",
                "https://github.com/muzafferverdil",
                "https://www.linkedin.com/"
        ));

        teamMemberRepository.save(new TeamMember(
                "Taha Talha PINARLI",
                "Backend Developer",
                "ttp@mail.com",
                "Odunpazarı/Eskişehir, Turkey",
                "https://github.com/tt-p",
                "https://www.linkedin.com/in/taha-talha-p%C4%B1narl%C4%B1/"
        ));

    }

}
