package com.Senai.Filmes.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sessoes")
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    @NotNull
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    @NotNull
    private Sala sala;

    @NotNull(message = "O horario do inicio e obtigatorio")
    private LocalDateTime inicio;

    @NotNull(message = "O horario do fim é obrigatorio")
    private LocalDateTime fim;

    @NotNull(message = "O inicio da sessãoprecisa ser informado.")
    private LocalDateTime inicioSessao;

    @NotNull(message = "O preço é obrigatório")
    private BigDecimal preco;
}
