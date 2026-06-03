package com.Senai.Filmes.Service;

import com.Senai.Filmes.DTO.Request.FilmeRequest;
import com.Senai.Filmes.DTO.Response.FilmeResponse;
import com.Senai.Filmes.Model.Filme;
import com.Senai.Filmes.Repository.IFilmeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FilmeService {

    @Autowired
    private IFilmeRepository filmeRepository;

    //CRUD
    public List<FilmeResponse> listarTodos(){
    return filmeRepository.findAll().stream().map(this::toResponse).toList();
    }

    public FilmeResponse buscaPorFilmeId(UUID id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado."));
        return toResponse(filme);
    }

    public FilmeResponse cadatrarFilme(FilmeRequest request){
        Filme filme = new Filme();
        filme.setTitulo(request.titulo());
        filme.setDescricao(request.descricao());
        filme.setUrlPoster(request.urlPoster());
        filme.setGenero(request.genero());
        filme.setDuracaoMinuto(request.duracaoMinutos());

        return toResponse(filmeRepository.save(filme));
    }
    public FilmeResponse atualizarFilme(UUID id, FilmeRequest filmeRequest) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum filme encontrado"));

            filme.setTitulo(filmeRequest.titulo());
            filme.setDescricao(filmeRequest.descricao());
            filme.setUrlPoster(filmeRequest.urlPoster());
            filme.setGenero(filmeRequest.genero());
            filme.setDuracaoMinuto(filmeRequest.duracaoMinutos());
            return toResponse(filmeRepository.save(filme));
    }

    public void deletar(UUID id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum filme encontrado"));
        filmeRepository.delete(filme);
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
