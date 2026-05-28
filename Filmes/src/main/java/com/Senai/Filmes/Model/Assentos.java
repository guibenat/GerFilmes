package com.Senai.Filmes.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "assento")

public class Assentos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "A fileira do assento é obrigatório")
    private String fileira;

    @NotNull(message = "O numero do assento é obrigatório")
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

}
