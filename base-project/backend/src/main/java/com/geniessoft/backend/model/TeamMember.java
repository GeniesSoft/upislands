package com.geniessoft.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "team_member")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamMember extends BaseEntity {

    private String name;
    private String speciality;
    private String mail;
    private String address;
    private String github;
    private String linkedin;

}