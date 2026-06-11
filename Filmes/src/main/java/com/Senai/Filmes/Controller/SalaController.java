package com.Senai.Filmes.Controller;

import com.Senai.Filmes.DTO.Request.SalaRequest;
import com.Senai.Filmes.DTO.Response.SalaResponse;
import com.Senai.Filmes.Model.Sala;
import com.Senai.Filmes.Service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Salas", description = "End point para gerancimento de salas do cinema")
@RestController
@CrossOrigin("*")
@RequestMapping("/api/sala")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping
    @Operation(summary = "Criar sala")
    public ResponseEntity<SalaResponse> criarSala (@RequestBody SalaRequest salaRequest){
        return  new ResponseEntity<>(salaService.cadastrarSala(salaRequest), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar salas")
    public ResponseEntity<List<SalaResponse>> listartodos (){
        List<SalaResponse> salas = salaService.listarTodos();
        if (salas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(salas,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Bucar sala por Id")
    public ResponseEntity<SalaResponse> buscarPorId (@PathVariable UUID id) {
        return new ResponseEntity<>(salaService.bucarPorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar sala")
    public ResponseEntity<SalaResponse> delete(@PathVariable UUID id) {
        salaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
