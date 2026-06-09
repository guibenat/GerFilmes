package com.Senai.Filmes.Repository;

import com.Senai.Filmes.Model.Assentos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAssentoRepository extends JpaRepository<Assentos, UUID> {
}
