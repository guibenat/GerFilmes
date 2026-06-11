package com.Senai.Filmes.Repository;

import com.Senai.Filmes.Model.Reserva;
import com.Senai.Filmes.Model.ReservaAssentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IReservaRepository extends JpaRepository<ReservaAssentos, UUID> {
    List<Reserva> findByEmail(UUID usuarioId);

}
