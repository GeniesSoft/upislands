package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class JetSkiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jetSkiModelId;
    private String modelName;
    private Date modelDate;
    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private JetSkiProducer jetSkiProducer;

    @OneToMany(mappedBy = "jetSkiModel", orphanRemoval = true)
    private List<JetSki> jetSkiList;
}
