package com.loja.loja.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.loja.loja.service.LojaService;
import com.loja.loja.shared.LojaCompletoDto;
import com.loja.loja.shared.LojaDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hamburguer")
public class LojaController {

    @Autowired
    private LojaService servico;

    @GetMapping("/porta")
    private String obterPorta(@Value("${local.server.port}") String porta) {
        return "A instância que respondeu a requisição está rodando na porta " + porta;
    }
    
    @GetMapping
    private ResponseEntity<List<LojaDto>> obterHamburguer() {
        return new ResponseEntity<>(servico.obterTodas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<LojaCompletoDto> obterPizzaPorId(@PathVariable String id) {
        Optional<LojaCompletoDto> pizza = servico.obterPorId(id);

        if (pizza.isPresent()) {
            return new ResponseEntity<>(pizza.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    private ResponseEntity<LojaCompletoDto> cadastrarHamburguer(@RequestBody @Valid LojaCompletoDto hamburguer) {
        return new ResponseEntity<>(servico.cadastrar(hamburguer), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> excluirHamburguerPorId(@PathVariable String id) {
        servico.excluirPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    private ResponseEntity<LojaCompletoDto> atualizarHamburguer(@PathVariable String id, @RequestBody @Valid LojaCompletoDto hamburguer) {
        LojaCompletoDto hamburguerAtualizada = servico.atualizarPorId(id, hamburguer);

        if (hamburguerAtualizada != null) {
            return new ResponseEntity<>(hamburguerAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}