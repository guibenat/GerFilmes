package com.Senai.Filmes.Repository;

import com.Senai.Filmes.Model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ISessaoRepository extends JpaRepository <Sessao, UUID>{

    @Query("SELECT s  FROM Sessao s WHERE s.inicio >= :inicio AND s.inicioSessao < :fimDia")
    List<Sessao> findByData(@Param("inicio")LocalDateTime inicioDia,
                            @Param("fim") LocalDateTime fimDia);

    @Query("SELECT CASE WHEN COUNT(s) > THEN true ELSE false END FROM Sessao s" +
            "WHERE s.sala.id = :salaId AND s.inicio < :fim AND s.fim > :inicio")
    boolean existeConflitoDeSala(@Param("salaId") UUID salaId,
                                 @Param("inicio") LocalDateTime inicio,
                                 @Param("fim") LocalDateTime fim);
    List<Sessao> findByFilmeId(UUID id);

}
