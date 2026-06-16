package com.Senai.Filmes.Service;

import com.Senai.Filmes.DTO.Request.ReservaRequest;
import com.Senai.Filmes.DTO.Response.AssentoResponse;
import com.Senai.Filmes.DTO.Response.ReservaResponse;
import com.Senai.Filmes.Model.*;
import com.Senai.Filmes.Model.Enums.StatusReserava;
import com.Senai.Filmes.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ReservaService {

    @Autowired private IReservaRepository reservaRepository;
    @Autowired private ISessaoRepository sessaoRepository;
    @Autowired private IUsuarioRepository usuarioRepository;
    @Autowired private IAssentoRepository assentoRepository;
    @Autowired private IReservaAssentosRepository reservaAssentoRepository;

    @Transactional
    public ReservaResponse criar(ReservaRequest request, String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Sessao sessao = sessaoRepository.findById(request.sessaoId())
                .orElseThrow(() -> new EntityNotFoundException("Sessão não encontrada"));

        if (!sessao.getInicio().isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Só é possível reservar sessões futuras");
        }

        for (UUID assentoId : request.assentoIds()) {
            if (reservaAssentoRepository.isAssentoOcupado(
                    assentoId, request.sessaoId(), StatusReserava.ATIVA)) {
                throw new IllegalStateException("Assento já reservado para esta sessão");
            }
        }

        // Só chega aqui se TODOS os assentos estiverem livres
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setSessao(sessao);
        reserva.setStatus(StatusReserava.ATIVA);  // ATIVA, não ATIVO
        Reserva reservaSalva = reservaRepository.save(reserva);

        List<AssentoResponse> assentosResponse = new ArrayList<>();
        for (UUID assentoId : request.assentoIds()) {
            Assentos assento = assentoRepository.findById(assentoId).orElseThrow();
            ReservaAssentos ra = new ReservaAssentos();
            ra.setReserva(reservaSalva);
            ra.setAssentos(assento);
            reservaSalva.getAssentos().add(ra);
            assentosResponse.add(new AssentoResponse(
                    assento.getId(), assento.getFileira(), assento.getNumero(), false));
        }

        reservaRepository.save(reservaSalva);
        return new ReservaResponse(reservaSalva.getId(),
                sessaoService.toResponse(sessao),
                assentosResponse,
                reservaSalva.getStatus(),
                reservaSalva.getCriadoEm());
    }
}