package com.Senai.Filmes.Service;

import com.Senai.Filmes.DTO.Response.FilmeResponse;
import com.Senai.Filmes.DTO.Response.SessaoResponse;
import com.Senai.Filmes.Model.Sessao;
import com.Senai.Filmes.Repository.IFilmeRepository;
import com.Senai.Filmes.Repository.ISalaRepository;
import com.Senai.Filmes.Repository.ISessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessaoService {
    @Autowired
    private ISessaoRepository sessaoRepository;

    @Autowired
    private IFilmeRepository filmeRepository;

    @Autowired
    private ISalaRepository salaRepository;

    private SessaoResponse toResponse(Sessao sessao) {
        FilmeResponse filme = new FilmeResponse(
                sessao.getFilme().getId(),
                sessao.getFilme().getTitulo(),
                sessao.getFilme().getDescricao(),
                sessao.getFilme().getUrlPoster(),
                sessao.getFilme().getGenero(),
                sessao.getFilme().getDuracaoMinuto()
        );

        return new SessaoResponse(
                sessao.getSala().getId(),
                filme,
                sessao.getInicio(),
                sessao.getFim(),
                sessao.getPreco()
        );
    }



    }

