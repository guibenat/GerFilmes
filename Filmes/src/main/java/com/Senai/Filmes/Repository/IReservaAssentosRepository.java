package com.Senai.Filmes.Repository;

import com.Senai.Filmes.Model.Enums.StatusReserava;
import com.Senai.Filmes.Model.Sessao;
import com.Senai.Filmes.Model.ReservaAssentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IReservaAssentosRepository extends JpaRepository<ReservaAssentos, UUID> {

    @Query("SELECT CASE WHEN COUNT(ra) > 0 THEN true ELSE false END"+
    "FROM ReservaAssentos ra" +
    "WHERE ra.assentos.id = assentoId AND ra.reserva.sessao.id = :sessaoId AND ra.reserva.status = :status")
    boolean isAssentoOcupado(@Param("assentoId")UUID assentoId,
                             @Param("sessaoId") UUID sessaoId,
                             @Param("status")StatusReserava statusReserava);

    @Query("SELECT ra.assentos.id FROM ReservaAssentos ra" +
    "WHERE ra.reserva.id = :sessaoId AND ra.reserva.status = :status")
    List<UUID> findAssentosOcupadoBySessaoId (@Param("sessaoId") UUID sessaoId,
                                              @Param("status") StatusReserava statusReserava);
}
