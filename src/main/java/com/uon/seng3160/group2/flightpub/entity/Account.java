package com.uon.seng3160.group2.flightpub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Account {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UserRoles", joinColumns = {
            @JoinColumn(name = "UserId", referencedColumnName = "Id") }, inverseJoinColumns = {
                    @JoinColumn(name = "RoleId", referencedColumnName = "Id") })
    private List<Role> roles = new ArrayList<Role>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    @JoinTable(name = "UserFlights", joinColumns = {
            @JoinColumn(name = "UserId", referencedColumnName = "Id") }, inverseJoinColumns = {
                    @JoinColumn(name = "AirlineCode", referencedColumnName = "AirlineCode", columnDefinition = "CHAR(2)"),
                    @JoinColumn(name = "FlightNumber", referencedColumnName = "FlightNumber", columnDefinition = "VARCHAR(6)"),
                    @JoinColumn(name = "DepartureTime", referencedColumnName = "DepartureTime", columnDefinition = "DATETIME"),
            })
    private List<Flight> flights = new ArrayList<Flight>();

    public void addFlights(List<Flight> flights) {
        this.flights.addAll(flights);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }
}