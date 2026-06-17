package com.Senai.Filmes.DTO.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SessaoResponse(
        UUID salaId,
        FilmeResponse filmeResponse,
        SalaResponse sala,
        LocalDateTime inicio,
        LocalDateTime fim,
        BigDecimal preco) {
}
