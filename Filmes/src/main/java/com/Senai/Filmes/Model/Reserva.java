package com.Senai.Filmes.Model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Reserva")

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


}
