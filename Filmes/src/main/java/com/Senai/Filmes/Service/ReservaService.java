package com.Senai.Filmes.Service;

import com.Senai.Filmes.Repository.IAssentoRepository;
import com.Senai.Filmes.Repository.IReservaRepository;
import com.Senai.Filmes.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired private IReservaRepository reservaRepository;
    @Autowired private ISessaoRepository sessaoRepository;
    @Autowired private IUsuarioRepository usuarioRepository;
    @Autowired private IAssentoRepository assentoRepository;
    @Autowired private IReservaRepository reservaAssentoRepository;
    @Autowired private SessaoService sessaoService;
}
