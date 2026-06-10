package com.Senai.Filmes.DTO.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SessaoResponse(UUID salaId,FilmeResponse filmeResponse, LocalDateTime inicio, LocalDateTime fim, BigDecimal preco) {
}
