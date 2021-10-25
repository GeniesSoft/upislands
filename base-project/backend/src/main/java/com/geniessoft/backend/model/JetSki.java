package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class JetSki {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jetSkiId;
    private boolean goProAdj;
    private String description;
    private double hourlyPrice;
    private Date startDate;
    private Date availabilityDate;
    private boolean notAvailable = false;

    @OneToMany(mappedBy = "jetSki", orphanRemoval = true)
    private List<NotAvailableDate> notAvailableDateList;

    @OneToMany(mappedBy = "jetSki", orphanRemoval = true)
    private List<JetSkiContent> jetSkiContentList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jetSkimodel_id")
    private JetSkiModel jetSkiModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "jetSki_bookings",
            joinColumns = @JoinColumn(name = "jetSki_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id"))
    private List<Booking> bookingList;
}
