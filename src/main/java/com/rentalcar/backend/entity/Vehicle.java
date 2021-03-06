package com.rentalcar.backend.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "reservations")
//@JsonIgnoreProperties("reservations")
@Entity
@Table(name = "vehicles")

public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @NotNull
    @Column(name = "plate_number", unique = true)
    private String plateNumber;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @Column(name = "type")
    private String type;

    @OneToMany(
            mappedBy = "vehicle",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Reservation> reservations;
}
