package com.Senai.Filmes.Repository;

import com.Senai.Filmes.Model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFilmeRepository extends JpaRepository<Filme, UUID> {

}
