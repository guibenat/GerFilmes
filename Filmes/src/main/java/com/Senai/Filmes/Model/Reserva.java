package com.Senai.Filmes.Model;

import com.Senai.Filmes.Model.Enums.StatusReserava;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Reserva")


public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @NotNull(message = "O status não pode estar vazio.")
    @Enumerated(EnumType.STRING)
    private StatusReserava status;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaAssentos> assentos = new ArrayList<>();

}
