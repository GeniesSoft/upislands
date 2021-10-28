package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private Date startDate;
    private Date finishDate;
    private double totalPrice;
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "booking_location_id")
    private Location bookingLocation;

    @OneToMany(mappedBy = "booking", orphanRemoval = true)
    private List<Review> reviewList;

    @ManyToMany(mappedBy = "bookingList")
    private List<JetSki> jetSkiList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
