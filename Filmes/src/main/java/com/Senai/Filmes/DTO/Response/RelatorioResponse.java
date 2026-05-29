package com.Senai.Filmes.DTO.Response;

import java.math.BigDecimal;
import java.util.List;

public record RelatorioResponse(Long totalReservas, BigDecimal totalReceita, List<FilmesTotais> filmes) {
    public record FilmesTotais(String novoFilme, long totalReservas) {}
}
