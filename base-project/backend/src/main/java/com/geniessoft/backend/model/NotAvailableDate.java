package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class NotAvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notAvailableDateId;
    private Date notAvailableDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jetSki_id")
    private JetSki jetSki;
}
