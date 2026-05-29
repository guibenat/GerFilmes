package com.Senai.Filmes.Service;

import com.Senai.Filmes.Repository.IFilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    @Autowired
    private IFilmeRepository filmeRepository;
}
