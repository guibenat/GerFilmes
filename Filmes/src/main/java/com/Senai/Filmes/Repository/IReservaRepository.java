package com.Senai.Filmes.Repository;

import com.Senai.Filmes.Model.ReservaAssentos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IReservaRepository extends JpaRepository<ReservaAssentos, UUID> {
}
