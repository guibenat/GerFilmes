package com.Senai.Filmes.Service;

import com.Senai.Filmes.DTO.Request.SessaoRequest;
import com.Senai.Filmes.DTO.Response.AssentoResponse;
import com.Senai.Filmes.DTO.Response.FilmeResponse;
import com.Senai.Filmes.DTO.Response.SalaResponse;
import com.Senai.Filmes.DTO.Response.SessaoResponse;
import com.Senai.Filmes.Model.Filme;
import com.Senai.Filmes.Model.Sala;
import com.Senai.Filmes.Model.Sessao;
import com.Senai.Filmes.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

// Service/SessaoService.java
@Service
public class SessaoService {

    @Autowired
    private ISessaoRepository sessaoRepository;
    @Autowired private IFilmeRepository filmeRepository;
    @Autowired private ISalaRepository salaRepository;
    @Autowired private IAssentoRepository assentoRepository;
    @Autowired private IReservaAssentosRepository reservaAssentoRepository;

    public List<SessaoResponse> listarPorData(LocalDate data) {
        LocalDateTime inicioDia = data.atStartOfDay();
        LocalDateTime fimDia = data.plusDays(1).atStartOfDay();
        return sessaoRepository.findByData(inicioDia, fimDia)
                .stream().map(this::toResponse).toList();
    }

    public SessaoResponse buscarPorId(UUID id) {
        return toResponse(sessaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sessao nao encontrada")));
    }

    public List<AssentoResponse> listarAssentosDisponiveis(UUID sessaoId) {
        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new EntityNotFoundException("Sessao nao encontrada"));
        // Busca IDs dos assentos ja ocupados nessa sessao
        List<UUID> ocupados = reservaAssentoRepository
                .findAssentosOcupadosBySessaoId(sessaoId, StatusReserva.ATIVA);
        // Retorna todos os assentos com flag disponivel
        return assentoRepository.findBySalaId(sessao.getSala().getId()).stream()
                .map(a -> new AssentoResponse(
                        a.getId(), a.getFileira(), a.getNumero(),
                        !ocupados.contains(a.getId()))) // true = disponivel
                .toList();
    }

    public SessaoResponse criar(SessaoRequest request) {
        Filme filme = filmeRepository.findById(request.filmeId())
                .orElseThrow(() -> new EntityNotFoundException("Filme nao encontrado"));
        Sala sala = salaRepository.findById(request.salaId())
                .orElseThrow(() -> new EntityNotFoundException("Sala nao encontrada"));
        if (sessaoRepository.existeConflitoDeSala(request.salaId(), request.inicio(), request.fim())) {
            throw new IllegalStateException("Ja existe uma sessao nessa sala nesse horario");
        }
        Sessao sessao = new Sessao();
        sessao.setFilme(filme); sessao.setSala(sala);
        sessao.setInicio(request.inicio()); sessao.setFim(request.fim());
        sessao.setPreco(request.preco());
        return toResponse(sessaoRepository.save(sessao));
    }

    public void deletar(UUID id) {
        sessaoRepository.delete(sessaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sessao nao encontrada")));
    }

    // PUBLIC: reutilizado pelo ReservaService para montar o ReservaResponse
    public SessaoResponse toResponse(Sessao sessao) {
        FilmeResponse f = new FilmeResponse(
                sessao.getFilme().getId(), sessao.getFilme().getTitulo(),
                sessao.getFilme().getDescricao(), sessao.getFilme().getUrlPoster(),
                sessao.getFilme().getGenero(), sessao.getFilme().getDuracaoMinutos());
        SalaResponse s = new SalaResponse(
                sessao.getSala().getId(), sessao.getSala().getNome(), sessao.getSala().getTotalAssentos());
        return new SessaoResponse(sessao.getId(), f, s, sessao.getInicio(), sessao.getFim(), sessao.getPreco());
    }
}