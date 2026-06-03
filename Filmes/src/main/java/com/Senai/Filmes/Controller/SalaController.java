package com.Senai.Filmes.Controller;

import com.Senai.Filmes.DTO.Response.SalaResponse;
import com.Senai.Filmes.Model.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sala")
public class SalaController {
    @Autowired
    private Sala sala;

    @GetMapping
    public ResponseEntity<List<SalaResponse>> listartodos (){
        List<SalaResponse> salas =

    }
}
