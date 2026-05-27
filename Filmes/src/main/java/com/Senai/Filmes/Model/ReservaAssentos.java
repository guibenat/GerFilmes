package com.Senai.Filmes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reserva_assento")

public class ReservaAssentos {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

}
