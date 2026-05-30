package com.Senai.Filmes.Service;

import com.Senai.Filmes.DTO.Response.FilmeResponse;
import com.Senai.Filmes.Model.Filme;
import com.Senai.Filmes.Repository.IFilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private IFilmeRepository filmeRepository;

    //CRUD
    public List<FilmeResponse> listarTodos(){
    return filmeRepository.findAll().stream().map(this::toResponse).toList();
    }

    private FilmeResponse toResponse(Filme filme){
        return new FilmeResponse(
                filme.getId(),
                filme.getTitulo(),
                filme.getDescricao(),
                filme.getUrlPoster(),
                filme.getGenero(),
                filme.getDuracaoMinuto()


        );
    }
}
