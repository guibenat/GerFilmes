package com.Senai.Filmes.DTO.Response;

import com.Senai.Filmes.Model.Enums.StatusReserava;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ReservaResponse(UUID id, SessaoResponse sessao, List<AssentoResponse> assentos, StatusReserava status, LocalDateTime criadoEm) {
}
