package com.pedidos.lojapedidos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.pedidos.lojapedidos.service.PedidosService;
import com.pedidos.lojapedidos.shared.HamburguerCompletoDto;
import com.pedidos.lojapedidos.shared.HamburguerDto;


@RestController
@RequestMapping("/pedido")
public class PedidosController {
    @Autowired
    private PedidosService servico;

    @GetMapping
    public ResponseEntity<List<HamburguerDto>> obterPedido(){
        return new ResponseEntity<> (servico.obterTodosPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HamburguerCompletoDto> obterPedidoPorId(@PathVariable String id){
        Optional<HamburguerCompletoDto> pedido = servico.obterPorId(id);
    if(pedido.isPresent()){
        return new ResponseEntity<>(pedido.get(), HttpStatus.OK);
    }
    else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    @PostMapping
    public ResponseEntity<HamburguerCompletoDto> cadastrarPedido(@RequestBody HamburguerCompletoDto dto){
        return new ResponseEntity<>(servico.cadastrarPedido(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HamburguerCompletoDto> atualizarPorId(@RequestBody HamburguerCompletoDto dto, @PathVariable String id){
        Optional<HamburguerCompletoDto> pedidoAtualizado = servico.atualizarPorId(id, dto);

        if(pedidoAtualizado.isPresent()){
            return new ResponseEntity<>(pedidoAtualizado.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable String id){
        servico.excluirPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}

