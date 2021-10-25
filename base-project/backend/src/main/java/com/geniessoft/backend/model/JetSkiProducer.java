package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class JetSkiProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int producerId;
    private String producerName;

    @OneToMany(mappedBy = "jetSkiProducer", orphanRemoval = true)
    private List<JetSkiModel> jetSkiModelList;
}
