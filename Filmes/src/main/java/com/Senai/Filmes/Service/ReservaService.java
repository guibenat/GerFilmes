package com.Senai.Filmes.Service;

import com.Senai.Filmes.DTO.Request.ReservaRequest;
import com.Senai.Filmes.DTO.Response.ReservaResponse;
import com.Senai.Filmes.Model.Enums.StatusReserava;
import com.Senai.Filmes.Model.Reserva;
import com.Senai.Filmes.Model.Sessao;
import com.Senai.Filmes.Model.Usuario;
import com.Senai.Filmes.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired private IReservaRepository reservaRepository;
    @Autowired private ISessaoRepository sessaoRepository;
    @Autowired private IUsuarioRepository usuarioRepository;
    @Autowired private IAssentoRepository assentoRepository;
    @Autowired private IReservaAssentosRepository reservaAssentoRepository;
    @Autowired private SessaoService sessaoService;

    @Transactional
    public ReservaResponse cadastrarReserva(ReservaRequest request, String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        Sessao sessao = sessaoRepository.findById(request.sessaoId())
                .orElseThrow(() -> new EntityNotFoundException("Sessão não encontrada"));

        if(!sessao.getInicioSessao().isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Só é possivel reservar sessões futuras");
        }

        //Este metodo é responsavel por  não permitir que doois usuarios não coprem a mesma cadeira
        for (UUID assentoId : request.assentoIds()) {
            if (reservaAssentoRepository.isAssentoOcupado(
                    assentoId, request.sessaoId(), StatusReserava.ATIVA)){
                throw  new IllegalStateException("Assanto já reservado para esta sessão");
            }
        }

        //Aqui vai salvar a reserva
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setSessao(sessao);
        reserva.setStatus(StatusReserava.ATIVA);
        Reserva reservaSalva = reservaRepository.save(reserva);
    }
}
