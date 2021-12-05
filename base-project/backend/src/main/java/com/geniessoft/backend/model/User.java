package com.geniessoft.backend.model;

import com.geniessoft.backend.oauth2Security.utilforsecurity.AuthProvider;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "appuser")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String emailAddress;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;
    private boolean deleted = false;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider = AuthProvider.none;
    private String providerId;
    private String imageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookingList;

    public User(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.emailAddress = user.getEmailAddress();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.birthDate = user.getBirthDate();
        this.userProfileImage = user.getUserProfileImage();
        this.role = user.role;
    }

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
