package com.Senai.Filmes.Controller;

import com.Senai.Filmes.DTO.Response.FilmeResponse;
import com.Senai.Filmes.DTO.Response.SalaResponse;
import com.Senai.Filmes.Model.Sala;
import com.Senai.Filmes.Service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sala")
public class SalaController {
    @Autowired
    private SalaService salaService;

    @GetMapping
    public ResponseEntity<List<SalaResponse>> listartodos (){
        List<SalaResponse> salas = salaService.listarTodos();
        if (salas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(salas,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponse> buscarPorId (@PathVariable UUID id) {
        return new ResponseEntity<>(salaService.bucarPorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SalaResponse> delete(@PathVariable UUID id) {
        salaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
