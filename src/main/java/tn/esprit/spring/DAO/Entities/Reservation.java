package tn.esprit.spring.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Table(name = "Reservation")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    private String idReservation;
    @Temporal(TemporalType.DATE)
    private LocalDate anneeUniversitaire;
    private boolean estValide;
}
