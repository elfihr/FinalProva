package com.pedidos.lojapedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pedidos.lojapedidos.httpPedidos.httpPedidos;
import com.pedidos.lojapedidos.model.HamburguerLoja;
import com.pedidos.lojapedidos.model.Pedidos;
import com.pedidos.lojapedidos.repository.HamburguerRepository;
import com.pedidos.lojapedidos.shared.HamburguerCompletoDto;
import com.pedidos.lojapedidos.shared.HamburguerDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PedidosServiceImpl implements PedidosService {
    @Autowired
    private HamburguerRepository repositorio;

    @Autowired
    private httpPedidos pdtClient;

    HamburguerLoja produto;

    @Override
    public List<HamburguerDto> obterTodosPedidos() {
        return repositorio.findAll().stream().map(p -> new HamburguerDto(p.getIdPedidos(), p.getCliente())).toList();
    }

    @CircuitBreaker(name = "obterPorId", fallbackMethod = "fallbackobterPorId")
    @Override
    public Optional<HamburguerCompletoDto> obterPorId(String id) {
        Optional<Pedidos> pedido = repositorio.findById(id);
        if(pedido.isPresent()){
            HamburguerLoja produto = pdtClient.obterProduto(pedido.get().getIdProduto());
            HamburguerCompletoDto pedidoCompleto = new HamburguerCompletoDto(pedido.get().getIdPedidos(), pedido.get().getCliente(), pedido.get().getIdProduto(), produto);
            return Optional.of(pedidoCompleto);
        }
        else{
            return Optional.empty();
        }
    }

    public Optional<HamburguerCompletoDto> fallbackobterPorId(String codigo) {
        Optional<Pedidos> pedido = repositorio.findById(codigo);
        if(pedido.isPresent()){

            HamburguerCompletoDto pedidoCompleto = new HamburguerCompletoDto(pedido.get().getIdPedidos(), pedido.get().getCliente(), pedido.get().getIdProduto(), null);
            return Optional.of(pedidoCompleto);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public HamburguerCompletoDto cadastrarPedido(HamburguerCompletoDto dto) {
        Pedidos pedido = new Pedidos(dto);
        repositorio.save(pedido);

        return new HamburguerCompletoDto(pedido.getIdPedidos(), pedido.getCliente(),pedido.getIdProduto(), pdtClient.obterProduto(pedido.getIdProduto()));
    }

    @Override
    public Optional<HamburguerCompletoDto> atualizarPorId(String id, HamburguerCompletoDto dto) {
        Optional<Pedidos> pedido = repositorio.findById(id);

        if(pedido.isPresent()){
            Pedidos pedidoAtualizado = new Pedidos(dto);
            pedidoAtualizado.setIdPedidos(id);
            repositorio.save(pedidoAtualizado);
            return Optional.of(new HamburguerCompletoDto(pedidoAtualizado.getIdPedidos(), pedidoAtualizado.getCliente(), pedidoAtualizado.getIdProduto(), pdtClient.obterProduto(pedido.get().getIdProduto())));
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public void excluirPorId(String id) {
        repositorio.deleteById(id);
    }
    
}

