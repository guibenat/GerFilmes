package com.Senai.Filmes.Service;

import com.Senai.Filmes.Repository.ISessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SessaoService {
    @Autowired
    private ISessaoRepository sessaoRepository;
}
