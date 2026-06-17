package com.Senai.Filmes.Service;

import com.Senai.Filmes.DTO.Request.ReservaRequest;
import com.Senai.Filmes.DTO.Response.AssentoResponse;
import com.Senai.Filmes.DTO.Response.ReservaResponse;
import com.Senai.Filmes.Model.*;
import com.Senai.Filmes.Model.Enums.StatusReserava;
import com.Senai.Filmes.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    private ISessaoRepository sessaoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IAssentoRepository assentoRepository;

    @Autowired
    private IReservaAssentosRepository reservaAssentoRepository;

    @Autowired
    private SessaoService sessaoService;

    @Transactional
    public ReservaResponse criar(ReservaRequest request, String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Sessao sessao = sessaoRepository.findById(request.sessaoId())
                .orElseThrow(() -> new EntityNotFoundException("Sessão não encontrada"));

        if (!sessao.getInicioDia().isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Só é possível reservar sessões futuras");
        }

        // Verifica disponibilidade de TODOS os assentos antes de criar qualquer registro
        for (UUID assentoId : request.assentoIds()) {
            if (reservaAssentoRepository.isAssentoOcupado(assentoId, request.sessaoId(), StatusReserava.ATIVA)) {
                throw new IllegalStateException("Assento já reservado para esta sessão");
            }
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setSessao(sessao);
        reserva.setStatus(StatusReserava.ATIVA);

        Reserva reservaSalva = reservaRepository.save(reserva);

        List<AssentoResponse> assentosResponse = new ArrayList<>();

        for (UUID assentoId : request.assentoIds()) {
            Assentos assento = assentoRepository.findById(assentoId)
                    .orElseThrow(() -> new EntityNotFoundException("Assento não encontrado"));

            ReservaAssentos ra = new ReservaAssentos();
            ra.setReserva(reservaSalva);
            ra.setAssentos(assento);
            reservaSalva.getAssentos().add(ra);

            assentosResponse.add(new AssentoResponse(assento.getId(), assento.getFileira(), assento.getNumero(), false));
        }

        return new ReservaResponse(
                reservaSalva.getId(),
                sessaoService.toResponse(sessao),
                assentosResponse,
                reservaSalva.getStatus(),
                reservaSalva.getCriadoEm()
        );
    }

    public List<ReservaResponse> listarMinhas(String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return reservaRepository.findByUsuarioId(usuario.getId()).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public void cancelar(UUID id, String emailUsuario) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada"));

        if (!reserva.getUsuario().getEmail().equals(emailUsuario)) {
            throw new IllegalStateException("Você não tem permissão para cancelar esta reserva");
        }

        if (!reserva.getSessao().getInicioDia().isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Não é possível cancelar uma reserva de sessão que já começou");
        }

        reserva.setStatus(StatusReserava.INATIVA);
        reservaRepository.save(reserva);
    }

    public ReservaResponse toResponse(Reserva reserva) {
        List<AssentoResponse> assentos = reserva.getAssentos().stream()
                .map(ra -> new AssentoResponse(
                        ra.getAssentos().getId(),
                        ra.getAssentos().getFileira(),
                        ra.getAssentos().getNumero(),
                        false
                ))
                .toList();

        return new ReservaResponse(
                reserva.getId(),
                sessaoService.toResponse(reserva.getSessao()),
                assentos,
                reserva.getStatus(),
                reserva.getCriadoEm()
        );
    }
}
