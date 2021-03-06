package com.geniessoft.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;
    private double totalPrice = 0;
    private int jetSkiCount;
    private boolean active = true;

    @Column(name = "is_paid")
    @Nullable
    private boolean paid = false;

    @OneToOne
    @JoinColumn(name = "booking_location_id")
    private Location bookingLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_company_id")
    private Company bookingCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_local_guide_id")
    private LocalGuide localGuide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
