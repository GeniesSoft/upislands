package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "JetSkiContent")
@Getter
@Setter
public class JetSkiContent extends Content {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jetSki_id")
    private JetSki jetSki;
}
