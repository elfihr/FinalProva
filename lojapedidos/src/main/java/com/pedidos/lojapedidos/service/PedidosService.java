package com.pedidos.lojapedidos.service;

import java.util.List;
import java.util.Optional;

import com.pedidos.lojapedidos.shared.HamburguerCompletoDto;
import com.pedidos.lojapedidos.shared.HamburguerDto;



public interface PedidosService {
    List<HamburguerDto> obterTodosPedidos();
    Optional<HamburguerCompletoDto> obterPorId(String id);
    HamburguerCompletoDto cadastrarPedido(HamburguerCompletoDto dto);
    Optional<HamburguerCompletoDto> atualizarPorId(String id, HamburguerCompletoDto dto);
    void excluirPorId(String id);
}
