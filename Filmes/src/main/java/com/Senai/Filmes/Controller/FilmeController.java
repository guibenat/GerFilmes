package com.Senai.Filmes.Controller;

import com.Senai.Filmes.DTO.Request.FilmeRequest;
import com.Senai.Filmes.DTO.Response.FilmeResponse;
import com.Senai.Filmes.Service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    @Operation(summary = "Função que lista todos filmes", description = "Retorna os filmes")

    public ResponseEntity<List<FilmeResponse>> listarTodos(){
        List<FilmeResponse> filmes = filmeService.listarTodos();
        if (filmes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filmes,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Função que busca os filmes por id", description = "Retorna os filmes buscados por id")

    public ResponseEntity<FilmeResponse> buscarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(filmeService.buscaPorFilmeId(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Função que cria filmes", description = "Cria os filmes")

    ResponseEntity<FilmeResponse> criarFilme(@RequestBody FilmeRequest filmeRequest) {
        return new ResponseEntity<>(filmeService.cadatrarFilme(filmeRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Função de atualizar filme", description = "Atualiza filmes")
    public ResponseEntity<FilmeResponse> atualizar(@PathVariable UUID id, @RequestBody FilmeRequest filmeRequest) {
        return new ResponseEntity<>(filmeService.atualizarFilme(id,filmeRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Função de deletar filmes", description = "Deleta filmes")
    public ResponseEntity<FilmeResponse> delete (@PathVariable UUID id) {
        filmeService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
