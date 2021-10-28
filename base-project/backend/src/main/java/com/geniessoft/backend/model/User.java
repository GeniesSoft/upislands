package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "appuser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String emailAddress;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;
    private boolean deleted = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookingList;

    public void addBooking(Booking booking){
        bookingList.add(booking);
    }

    public void removeBooking(Booking booking){
        bookingList.remove(booking);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    private Content userProfileImage;
}
